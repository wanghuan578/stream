package com.spirit.stream.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class TranslateTaskPoolConfig {

    @Value("${spring.threadpool.translate.corepoolsize}")
    private int corePoolSize;

    @Value("${spring.threadpool.translate.maxpoolsize}")
    private int maxPoolSize;

    @Value("${spring.threadpool.translate.keepaliveseconds}")
    private int keepAliveSeconds;

    @Value("${spring.threadpool.translate.queuecapacity}")
    private int queueCapacity;

    @Bean(name = "translateTaskExecutor")
    public TaskExecutor translateTaskExecutor() {
        ThreadPoolTaskExecutor executer = new ThreadPoolTaskExecutor();
        executer.setCorePoolSize(corePoolSize);
        executer.setMaxPoolSize(maxPoolSize);
        executer.setKeepAliveSeconds(keepAliveSeconds);
        executer.setQueueCapacity(queueCapacity);
        executer.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executer.setWaitForTasksToCompleteOnShutdown(true);
        return executer;
    }
}
