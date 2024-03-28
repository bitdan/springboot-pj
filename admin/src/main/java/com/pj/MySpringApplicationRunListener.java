package com.pj;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @description test
 * @date 2024/3/27 13:44:28
 */
@Slf4j
public class MySpringApplicationRunListener implements SpringApplicationRunListener {
    // 这个构造函数不能少，否则反射生成实例会报错
    public MySpringApplicationRunListener(SpringApplication sa, String[] args) {
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("My-contextPrepared {}", LocalDateTime.now());
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("My-contextLoaded {}", LocalDateTime.now());
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        log.info("My-started {}", LocalDateTime.now());
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        log.info("My-running {}", LocalDateTime.now());
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("My-failed {}", LocalDateTime.now());
    }
}
