package com.pj.system.controller;

/**
 * @version 1.0
 * @description 消息controller
 * @date 2024/3/28 17:13:20
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Source.class)
public class MessageController {

    @Autowired
    private Source source;

    @GetMapping("/api/send")
    public String send(String message) {
        MessageBuilder<String> messageBuilder = MessageBuilder.withPayload(message);
        source.output().send(messageBuilder.build());
        return "success";
    }
}
