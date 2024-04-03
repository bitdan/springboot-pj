package com.pj.system.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

/**
 * @version 1.0
 * @description MsgConsumer
 * @date 2024/4/2 17:01:36
 */
@Slf4j
@Configuration
public class MsgConsumer {

    @Bean
    public Consumer<Message<String>> sink() {
        log.info("MsgConsumer.sink");
        return msg -> log.info("sink接到消息：{}", msg.getPayload());
    }
}
