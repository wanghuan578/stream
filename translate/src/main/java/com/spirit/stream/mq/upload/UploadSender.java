package com.spirit.stream.mq.upload;

import com.spirit.common.constant.RabbitMQ;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UploadSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String msg) {
        this.rabbitTemplate.convertAndSend(RabbitMQ.TOPIC_UPLOAD_EXCHANGE, RabbitMQ.ROUTINE_KEY_UPLOAD, msg);
    }

}
