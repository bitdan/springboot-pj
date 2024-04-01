package com.pj.system.mq;

/**
 * @version 1.0
 * @description mq
 * @date 2024/3/28 17:12:47
 */

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Source.class)
public class MessageProducer {

}
