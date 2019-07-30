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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    @Resource
    private UploadSender uploadSender;

    public void addTask(Event event) throws MainStageException {

        eventRepository.save(event);

        List<TranslateBizInfo> list = event.getTranslateBizInfoList();
        for (TranslateBizInfo info : list) {
            info.setEvent(event);
            translateRepository.save(info);
            ffmpegAdaptor.translate(info);
        }
        com.spirit.common.entity.Event ev = new com.spirit.common.entity.Event();
        BeanUtils.copyProperties(event, ev, new String[] {"translateBizInfoList"});
        List<TranslateInfo> l = new ArrayList<TranslateInfo>();
        for(TranslateBizInfo translate : event.getTranslateBizInfoList()) {
            TranslateInfo t = new TranslateInfo();
            BeanUtils.copyProperties(translate, t);
            l.add(t);
        }
        ev.setTranslateBizInfoList(l);
        uploadSender.send(JSON.toJSONString(ev, true));
    }

    public int runTask() {
        return 0;
    }
}
