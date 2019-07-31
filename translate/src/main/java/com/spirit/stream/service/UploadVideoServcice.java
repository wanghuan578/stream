package com.spirit.stream.service;


import com.spirit.common.Exception.MainStageException;
import com.spirit.stream.dao.entity.TranslateBizInfo;

public interface UploadVideoServcice {
    int upload(String title, String filePath, String parentId, TranslateBizInfo info) throws MainStageException;
}
