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
public class UploadTaskPoolConfig {

    @Value("${spring.threadpool.upload.corepoolsize}")
    private int corePoolSize;

    @Value("${spring.threadpool.upload.maxpoolsize}")
    private int maxPoolSize;

    @Value("${spring.threadpool.upload.keepaliveseconds}")
    private int keepAliveSeconds;

    @Value("${spring.threadpool.upload.queuecapacity}")
    private int queueCapacity;

    @Bean(name = "uploadTaskExecutor")
    public TaskExecutor uploadTaskExecutor() {
        ThreadPoolTaskExecutor taskPoolExecutor = new ThreadPoolTaskExecutor();
        taskPoolExecutor.setCorePoolSize(corePoolSize);
        taskPoolExecutor.setMaxPoolSize(maxPoolSize);
        taskPoolExecutor.setKeepAliveSeconds(keepAliveSeconds);
        taskPoolExecutor.setQueueCapacity(queueCapacity);
        taskPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        taskPoolExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return taskPoolExecutor;
    }
}
