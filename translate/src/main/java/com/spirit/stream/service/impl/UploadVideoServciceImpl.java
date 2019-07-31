package com.spirit.stream.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.spirit.common.Exception.MainStageException;
import com.spirit.common.constant.ResultType;
import com.spirit.common.constant.TranslateBizStatus;
import com.spirit.stream.dao.entity.TranslateBizInfo;
import com.spirit.stream.dao.repository.TranslateRepository;
import com.spirit.stream.service.UploadVideoServcice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Slf4j
@Component
public class UploadVideoServciceImpl implements UploadVideoServcice {

    @Value("${aliyun.accesseyid}")
    private String accessKeyId;

    @Value("${aliyun.accesskeysecret}")
    private String accessKeySecret;

    @Resource(name = "uploadTaskExecutor")
    private TaskExecutor uploadTaskExecutor;

    @Resource
    private TranslateRepository translateRepository;

    public int upload(final String title, final String filePath, final String parentResourceId, final TranslateBizInfo info) throws MainStageException {

        try {
            uploadTaskExecutor.execute(new Runnable() {
                public void run() {
                    info.setStatus(uploadVideo(accessKeyId, accessKeySecret, title, filePath, parentResourceId) == true ? TranslateBizStatus.UPLOAD_SUCCEED : TranslateBizStatus.UPLOAD_FAILED);
                    translateRepository.save(info);
                }
            });
        }
        catch (TaskRejectedException e) {
            log.error(e.getLocalizedMessage(), e);
            throw new MainStageException(ResultType.UPLOAD_THREAD_POOL_FULL_EXCEPTION);
        }

        return 0;
    }

    private boolean uploadVideo(String accessKeyId, String accessKeySecret, String title, String filePath, String parentResourceId) {

        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, filePath);
        request.setPartSize(1 * 1024 * 1024L);
        request.setTaskNum(1);
        request.setEnableCheckpoint(false);

        JSONObject userData = new JSONObject();
        JSONObject extend = new JSONObject();
        extend.put("ParentResourceId", parentResourceId);
        userData.put("Extend", extend.toJSONString());
        request.setUserData(userData.toJSONString());

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID

        return response.isSuccess();
    }
}
