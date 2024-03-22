package com.pj.runner;

import com.pj.service.MapstructSample;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @description runner
 * @date 2024/3/8 14:45:48
 */
@Slf4j
@Component
@AllArgsConstructor
public class SystemApplicationRunner implements ApplicationRunner {

    private final MapstructSample mapstructSample;

    @Override
    public void run(ApplicationArguments args) {

        mapstructSample.samp();
    }

}
