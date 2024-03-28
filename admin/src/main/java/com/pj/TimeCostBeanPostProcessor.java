package com.pj;


import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @description test
 * @date 2024/3/27 14:00:17
 */
@Component
@Slf4j
public class TimeCostBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Long> costMap = Maps.newConcurrentMap();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        costMap.put(beanName, System.nanoTime());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (costMap.containsKey(beanName)) {
            Long start = costMap.get(beanName);
            long cost = System.nanoTime() - start;
            if (cost > 0) {
                costMap.put(beanName, cost);
                // 将纳秒值转换为毫秒值
                long costInMs = TimeUnit.NANOSECONDS.toMillis(cost);
                log.info("bean: " + beanName + "\ttime: " + costInMs);
            }
        }
        return bean;
    }
}
