package com.pj;

import cn.hutool.core.convert.Convert;
import com.pj.domain.GenTable;
import com.pj.service.IGenTableService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

/**
 * @version 1.0
 * @description test
 * @date 2024/3/11 16:21:23
 */


@SpringBootTest
@Slf4j
public class Gen_App {
    @Autowired
    IGenTableService genTableService;

    @Test
    public void test1() {

        String tables = "t_global_app_aksk";
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);

        GenTable genTable = genTableService.selectGenTableByName(tables);
        if (null == genTable) {
            genTableService.importGenTable(tableList);
        }
        genTableService.generatorCode(tables);

    }

    @Test
    public void test2(){
        String currentDirectory = System.getProperty("user.dir");
        File file = new File(currentDirectory);
        String parentDirectory = file.getParent();
        log.info("当前目录的父目录是：" + parentDirectory);
        log.info("当前工作目录是：" + currentDirectory);
    }
}
