package com.pj.system.event;

import com.pj.system.domain.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @version 1.0
 * @description EventListener
 * @date 2024/4/7 16:34:18
 */
@Slf4j
@Component
public class MyEventListener {

    @EventListener
    public void listener1(MessageDTO event) {
        CompletableFuture.runAsync(() -> {
            log.info("MyEventListener.listener1 is {}", event.toString());
        });
    }

}
