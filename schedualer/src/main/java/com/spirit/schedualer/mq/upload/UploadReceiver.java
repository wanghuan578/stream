package com.spirit.schedualer.mq.upload;

import com.alibaba.fastjson.JSON;
import com.spirit.common.constant.RabbitMQ;
import com.spirit.common.entity.Event;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UploadReceiver {

    @RabbitListener(queues = RabbitMQ.TOPIC_UPLOAD_QUEUE)
    public void onUpload(String msg) {

        Event ev = JSON.parseObject(msg, Event.class);
        System.out.println("onUpload: " + ev);
    }

}
