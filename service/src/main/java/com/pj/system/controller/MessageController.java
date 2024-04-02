package com.pj.system.controller;

/**
 * @version 1.0
 * @description 消息controller
 * @date 2024/3/28 17:13:20
 */


import com.pj.system.mq.MessageProviderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Source.class)
@RequiredArgsConstructor
public class MessageController {


    private final MessageProviderImpl messageProvider;
    @GetMapping("/api/send")
    public void send() {
        messageProvider.sendMethod();
    }
}
