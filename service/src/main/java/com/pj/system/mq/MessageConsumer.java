package com.pj.system.mq;

/**
 * @version 1.0
 * @description mq
 * @date 2024/3/28 17:14:02
 */

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)
public class MessageConsumer {

    @StreamListener(Sink.INPUT)
    public void process(Object message) {
        System.out.println("received message: " + message);
    }

}
