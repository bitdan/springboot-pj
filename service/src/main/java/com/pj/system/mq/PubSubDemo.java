package com.pj.system.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @version 1.0
 * @description demo
 * @date 2024/4/2 16:11:37
 */
@Configuration
@Slf4j
public class PubSubDemo {

    @Bean
    public Supplier<String> source() {
        return () -> {
            String message = "from source";
            log.info("======from source ");
            return message;
        };
    }

    @Bean
    public Function<String, String> transfer() {
        return message -> {

            log.info("======transfer {}====== ", message);
            return "transfer" + message;
        };
    }

    @Bean
    public Consumer<String> sink() {
        return message -> {
            log.info("======at sink {} ", message);
        };
    }
}
