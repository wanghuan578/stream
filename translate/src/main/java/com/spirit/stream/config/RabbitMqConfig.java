package com.spirit.stream.config;

import com.spirit.common.constant.RabbitMQ;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue topicUpload() {
        return new Queue(RabbitMQ.TOPIC_UPLOAD_QUEUE);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMQ.TOPIC_UPLOAD_EXCHANGE);
    }

    @Bean
    public Binding topicUploadBinding() {
        return BindingBuilder.bind(topicUpload()).to(topicExchange()).with(RabbitMQ.ROUTINE_KEY_UPLOAD);
    }
}
