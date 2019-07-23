package com.spirit.stream.service;

import com.spirit.stream.dao.entity.TranslateInfo;

public interface TranslateService {
    TranslateInfo save(TranslateInfo info);
    void delete(Long id);
    Iterable<TranslateInfo> getAll();
    TranslateInfo getById(Long id);
    void update(TranslateInfo info);
}
