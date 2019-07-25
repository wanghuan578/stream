package com.spirit.stream.service;

import com.spirit.stream.dao.entity.Event;



public interface TranslateTaskService {
    void addTask(Event event);
    int runTask();

}
