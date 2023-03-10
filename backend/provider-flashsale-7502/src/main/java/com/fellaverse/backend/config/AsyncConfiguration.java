package com.fellaverse.backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@Slf4j
public class AsyncConfiguration implements AsyncConfigurer {
    @Bean(name = "asyncPoolTaskExecutor")
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // core threads number
        taskExecutor.setCorePoolSize(10);
        // max threads number, use only after cache queue is full
        taskExecutor.setMaxPoolSize(100);
        // cache queue
        taskExecutor.setQueueCapacity(50);
        // alive time for threads other than core threads
        taskExecutor.setKeepAliveSeconds(200);
        // add async to thread names
        taskExecutor.setThreadNamePrefix("async-");
        /**
         * when queue is full and max thread number is reached, activate reject execution strategy
         * There are four strategies：
         * ThreadPoolExecutor.AbortPolicy: discard quest and throw RejectedExecutionException
         * ThreadPoolExecutor.DiscardPolicy：discard quest only
         * ThreadPoolExecutor.DiscardOldestPolicy：discard the oldest quest and retry current quest repeatedly
         * ThreadPoolExecutor.CallerRunsPolicy：add current quest repeatedly until success
         */
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return executor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}