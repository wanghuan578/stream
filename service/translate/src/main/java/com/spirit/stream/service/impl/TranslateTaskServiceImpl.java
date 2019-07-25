package com.spirit.stream.service.impl;

import com.spirit.stream.dao.entity.Event;
import com.spirit.stream.dao.entity.TranslateBizInfo;
import com.spirit.stream.dao.repository.EventRepository;
import com.spirit.stream.dao.repository.TranslateRepository;
import com.spirit.stream.service.TranslateTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class TranslateTaskServiceImpl implements TranslateTaskService {

    @Resource
    private TranslateRepository translateRepository;
    @Resource
    private EventRepository eventRepository;

    @Override
    public void addTask(Event event) {
        eventRepository.save(event);
        List<TranslateBizInfo> list = event.getTranslateBizInfoList();
        for (TranslateBizInfo info : list) {
            info.setEvent(event);
            translateRepository.save(info);
        }
    }

    @Override
    public int runTask() {
        return 0;
    }
}
