package com.pj.satoken;

/**
 * @version 1.0
 * @description 临时token
 * @date 2024/3/8 14:42:31
 */

import cn.dev33.satoken.temp.SaTempInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 临时认证模块 自定义实现
 */
@Component
@Slf4j
public class MySaTemp implements SaTempInterface {

    //    @Override
    public String createToken(Object value, long timeout) {
       log.info("------- 自定义一些逻辑 createToken ");
        return SaTempInterface.super.createToken(value + "app", timeout);
    }

    @Override
    public Object parseToken(String token) {
       log.info("------- 自定义一些逻辑 parseToken ");
        return SaTempInterface.super.parseToken(token);
    }

}
