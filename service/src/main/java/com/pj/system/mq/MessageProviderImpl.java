package com.pj.system.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @version 1.0
 * @description 消息
 * @date 2024/4/2 14:53:42
 */
@Component
@Slf4j
public class MessageProviderImpl implements IMessage {


    @Autowired
    StreamBridge streamBridge;

    @Override
    public void sendMethod() {
        String message = UUID.randomUUID().toString();
        //这里说明一下这个 streamBridge.send 方法的参数 第一个参数是exchange或者topic 就是主题名称
        //默认的主题名称是通过
        //输入:    <方法名> + -in- + <index>
        //输出:    <方法名> + -out- + <index>
        //这里我们接收的时候就要用send方法 参数是consumer<String>接收  详情看8802的controller
        //consumer的参数类型是这里message的类型
        streamBridge.send("send-in-0", message);
        log.info("************发送了message：" + message);
    }
}
