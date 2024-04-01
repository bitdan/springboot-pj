package com.pj.system.controller;

/**
 * @version 1.0
 * @description 接受消息
 * @date 2024/3/30 14:25:50
 */

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/message")
@Slf4j
public class MonitorController {

    @SaIgnore
    @PostMapping(value = "/runMessage")
    public SaResult receiveAndSaveMessage(@RequestBody String receivedMessage) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        String fileName = dtf.format(now) + "_runMessage.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("C:\\Users\\tass_\\Desktop\\" + fileName)))) {
            writer.write(receivedMessage);
            writer.flush();
            log.info("runMessage succes at  : {}", now);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("runMessage error at  : {}", now);
            return SaResult.error("error");
        }
        return SaResult.ok();
    }

    @SaIgnore
    @PostMapping(value = "/eventMessage")
    public SaResult eventMessage(@RequestBody String receivedMessage) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
        LocalDateTime now = LocalDateTime.now();
        String fileName = dtf.format(now) + "_eventMessage.txt";

        try (OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(new File("C:\\Users\\tass_\\Desktop\\" + fileName).toPath()))) {
            writer.write(receivedMessage);
            writer.flush();
            log.info("eventMessage succes at  : {}", now);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("eventMessage error at  : {}", now);
            return SaResult.error("error");
        }
        return SaResult.ok();
    }
}
