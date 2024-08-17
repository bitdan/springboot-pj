package com.pj.system.controller;

/**
 * @version 1.0
 * @description 消息controller
 * @date 2024/3/28 17:13:20
 */


import cn.dev33.satoken.util.SaResult;
import com.pj.annotation.PrintlnLog;
import com.pj.system.domain.dto.MessageDTO;
import com.pj.utils.SpringUtils;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final StreamBridge streamBridge;

    @PrintlnLog(description = "MessageController.send", executionTime = true)
    @GetMapping("/api/send")
    public SaResult send() {
        String message = UUID.randomUUID().toString();
        streamBridge.send("source-in-0", message);
        SpringUtils.context().publishEvent(new MessageDTO("source-in-0", message));
        return SaResult.ok();
    }

    private final String produce = "addBounsChannel-out-0";

    @GetMapping("/rocketmq/send")
    public SaResult rocketmqSend() {
        MessageBuilder builder = MessageBuilder.withPayload(UUID.randomUUID() + "==" + LocalDateTime.now())
                .setHeader(RocketMQHeaders.TAGS, "binder")
                .setHeader(RocketMQHeaders.KEYS, "my-key");
        Message message = builder.build();
        streamBridge.send(produce, message);
        SpringUtils.context().publishEvent(new MessageDTO(produce,message.toString()));
        return SaResult.ok();
    }
}
