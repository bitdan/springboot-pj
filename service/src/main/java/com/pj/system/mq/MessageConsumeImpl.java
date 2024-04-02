package com.pj.system.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @version 1.0
 * @description 消息
 * @date 2024/4/2 14:53:42
 */
@Component
@Slf4j
public class MessageConsumeImpl {


    //这里接收rabbitmq的条件是参数为Consumer 并且 方法名和supplier方法名相同
    //这里的返回值是一个匿名函数 返回类型是consumer 类型和提供者的类型一致
    //supplier发送的exchange是 send-in-0 这里只需要用send方法名即可
    @Bean
    Consumer<String> send() {
        return str -> {
            System.out.println("我收到了消息：" + str);
        };
    }

}
