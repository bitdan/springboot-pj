package com.pj.system.controller;

/**
 * @version 1.0
 * @description 消息controller
 * @date 2024/3/28 17:13:20
 */


import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@EnableBinding(Source.class)
@RequiredArgsConstructor
public class MessageController {

    private final StreamBridge streamBridge;

    @GetMapping("/api/send")
    public void send() {
        streamBridge.send("output", UUID.randomUUID().toString());
    }
}
