package com.spirit.stream.service.impl;

import com.spirit.common.Exception.MainStageException;
import com.spirit.common.constant.ResultType;
import com.spirit.common.constant.TranslateBizStatus;
import com.spirit.stream.adaptor.FFmpegAdaptor;
import com.spirit.stream.dao.entity.Event;
import com.spirit.stream.dao.entity.TranslateBizInfo;
import com.spirit.stream.dao.repository.EventRepository;
import com.spirit.stream.dao.repository.TranslateRepository;
import com.spirit.stream.service.TranslateTaskService;
import com.spirit.stream.service.UploadVideoServcice;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.File;

import static com.spirit.common.constant.ResultType.*;

@Slf4j
@Service
public class TranslateTaskServiceImpl implements TranslateTaskService {

    @Value("${translate.workdir}")
    private String workingDir;

    @Resource
    private TranslateRepository translateRepository;

    @Resource
    private EventRepository eventRepository;

    @Resource
    private FFmpegAdaptor ffmpegAdaptor;

    @Resource(name = "translateTaskExecutor")
    private TaskExecutor translateTaskExecutor;
//    @Resource
//    private UploadSender uploadSender;

    @Resource
    private UploadVideoServcice uploadService;

    public void addTask(Event ev) throws MainStageException {

        try {
            eventRepository.save(ev);
            for (TranslateBizInfo info : ev.getTranslateBizInfoList()) {
                info.setEvent(ev);
                info.setStatus(TranslateBizStatus.PRE_TRANSLATING);
                translateRepository.save(info);
            }
        }
        catch (Exception e) {
            throw new MainStageException(DATASOURCE_RUN_EXCEPTION);
        }

        runTask(ev);
    }

    private int runTask(final Event ev) throws MainStageException {

        log.info("translate work dir: " + workingDir);

        File file = new File(workingDir);
        if (!file.exists()) {
            file.mkdirs();
        }

        boolean busy = false;

        for (final TranslateBizInfo info : ev.getTranslateBizInfoList()) {
            try {
                translateTaskExecutor.execute(new Runnable() {

                    public void run() {
                        try {
                            info.setTargetFilePath(StringUtils.join(new String [] {workingDir, "/", info.getTargetFileName()}));
                            ffmpegAdaptor.translate(info);
                            info.setStatus(TranslateBizStatus.TRANSLATE_COMPLETE);
                            translateRepository.save(info);
                        }
                        catch (MainStageException e) {
                            info.setStatus(TranslateBizStatus.TRANSLATE_FALIED);
                            translateRepository.save(info);
                            log.error("MainStageException", e);
                        }

                        try {
                            uploadService.upload(info.getTitle(), info.getTargetFilePath(), ev.getResourceId(), info);
                        }
                        catch (MainStageException e) {
                            info.setStatus(TranslateBizStatus.UPLOAD_FAILED);
                            translateRepository.save(info);
                            log.error("MainStageException", e);
                        }
                    }
                });
            }
            catch (TaskRejectedException e) {
                log.error(e.getLocalizedMessage(), e);
                info.setStatus(TranslateBizStatus.RESOURCES_INSUFFICIENT);
                translateRepository.save(info);
                busy = true;
            }
        }

        if (busy) {
            throw new MainStageException(ResultType.TRANSLATE_THREAD_POOL_FULL_EXCEPTION);
        }

        return 0;
    }
}
