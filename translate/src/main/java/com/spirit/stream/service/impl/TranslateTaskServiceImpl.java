package com.spirit.stream.service.impl;

import com.alibaba.fastjson.JSON;
import com.spirit.common.Exception.MainStageException;
import com.spirit.common.entity.TranslateInfo;
import com.spirit.stream.adaptor.FFmpegAdaptor;
import com.spirit.stream.dao.entity.Event;
import com.spirit.stream.dao.entity.TranslateBizInfo;
import com.spirit.stream.dao.repository.EventRepository;
import com.spirit.stream.dao.repository.TranslateRepository;
import com.spirit.stream.mq.upload.UploadSender;
import com.spirit.stream.service.TranslateTaskService;
import com.spirit.stream.service.UploadVideoServcice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TranslateTaskServiceImpl implements TranslateTaskService {

    @Resource
    private TranslateRepository translateRepository;

    @Resource
    private EventRepository eventRepository;

    @Resource
    private FFmpegAdaptor ffmpegAdaptor;

//    @Resource
//    private UploadSender uploadSender;

    @Resource
    private UploadVideoServcice uploadService;

    public void addTask(Event event)  {

        eventRepository.save(event);

        for (TranslateBizInfo info : event.getTranslateBizInfoList()) {
            info.setEvent(event);
            info.setStatus(1);
            translateRepository.save(info);
        }

        for (TranslateBizInfo info : event.getTranslateBizInfoList()) {
            try {
                ffmpegAdaptor.translate(info);
                info.setStatus(2);
                translateRepository.save(info);
            }
            catch (MainStageException e) {
                log.error(e.getText());
                info.setStatus(3);
                translateRepository.save(info);
            }

            try {
                uploadService.upload(info.getTitle(), info.getOutFileName(), event.getResourceId(), info);
            }
            catch (MainStageException e) {
                log.error(e.getText());
                info.setStatus(4);
                translateRepository.save(info);
            }
        }
    }

    public int runTask() {
        return 0;
    }
}
