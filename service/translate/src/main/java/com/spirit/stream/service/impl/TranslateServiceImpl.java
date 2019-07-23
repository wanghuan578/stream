package com.spirit.stream.service.impl;

import com.spirit.stream.dao.entity.TranslateInfo;
import com.spirit.stream.dao.repository.TranslateRepository;
import com.spirit.stream.service.TranslateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class TranslateServiceImpl implements TranslateService {

    @Resource
    private TranslateRepository translateRepository;

    @Override
    public TranslateInfo save(TranslateInfo info) {
        return translateRepository.save(info);
    }

    @Override
    public void delete(Long id) {
        translateRepository.deleteById(id);
    }

    @Override
    public Iterable<TranslateInfo> getAll() {
        return translateRepository.findAll();
    }

    @Override
    public TranslateInfo getById(Long id) {
        Optional<TranslateInfo> op = translateRepository.findById(id);
        return op.get();
    }

    @Override
    public void update(TranslateInfo info) {
        info.setStatus(3);
    }
}
