package com.spirit.stream.service;

import com.spirit.common.Exception.MainStageException;
import com.spirit.stream.dao.entity.Event;


public interface TranslateTaskService {
    void addTask(Event ev) throws MainStageException;
}
