package com.pj.system.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * @version 1.0
 * @description MsgProducer
 * @date 2024/4/2 16:58:50
 */
@Slf4j
@Configuration
public class MsgProducer {

    @Bean
    public Function<String, Message<String>> source() {
        return message -> {
            log.info("{} 发送1条消息： {}", LocalDateTime.now(), message);
            return MessageBuilder.withPayload(message)
                    .build();
        };
    }
}
