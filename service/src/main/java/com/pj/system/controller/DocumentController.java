package com.pj.system.controller;

import cn.dev33.satoken.util.SaResult;
import com.pj.system.esmapper.Document;
import com.pj.system.esmapper.DocumentMapper;
import com.pj.system.service.SysLoginService;
import lombok.RequiredArgsConstructor;
import org.dromara.easyes.core.conditions.update.LambdaEsUpdateWrapper;
import org.dromara.easyes.core.core.EsWrappers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description ceshi
 * @date 2024/3/15 17:22:58
 */

@RequiredArgsConstructor
@RestController
public class DocumentController {

    private final DocumentMapper documentMapper;

    @PostMapping("/testCreateIndex")
    public SaResult testCreateIndex() {
        // 测试创建索引,框架会根据实体类及字段上加的自定义注解一键帮您生成索引 需确保索引托管模式处于manual手动挡(默认处于此模式),若为自动挡则会冲突
        boolean success = documentMapper.createIndex();
        return SaResult.ok();
    }


    @PostMapping("/testInsert")
    public SaResult testInsert() {
        // 测试插入数据
        Document document = new Document();
        document.setTitle("老汉");
        document.setContent("推*技术过硬");
        int successCount = documentMapper.insert(document);
        return SaResult.data(successCount);
    }


    @PostMapping("/testSelect")
    public SaResult testSelect() {
        // 测试查询 写法和MP一样 可以用链式,也可以非链式 根据使用习惯灵活选择即可
        String title = "老汉";
        Document document = EsWrappers.lambdaChainQuery(documentMapper)
                .eq(Document::getTitle, title)
                .one();
        return SaResult.data(document);
//        Assert.assertEquals(title,document.getTitle());
    }

    @PostMapping("/testUpdate")
    public SaResult testUpdate() {
        // 测试更新 更新有两种情况 分别演示如下:
        // case1: 已知id, 根据id更新 (为了演示方便,此id是从上一步查询中复制过来的,实际业务可以自行查询)
//        String id = "krkvN30BUP1SGucenZQ9";
        String title1 = "隔壁老王";
//        Document document1 = new Document();
//        document1.setId(id);
//        document1.setTitle(title1);
//        documentMapper.updateById(document1);

        // case2: id未知, 根据条件更新
        LambdaEsUpdateWrapper<Document> wrapper = new LambdaEsUpdateWrapper<>();
        wrapper.eq(Document::getTitle, title1);
        Document document2 = new Document();
        document2.setTitle("隔壁老李");
        document2.setContent("推*技术过软");
        documentMapper.update(document2, wrapper);
        return SaResult.ok();
        // 关于case2 还有另一种省略实体的简单写法,这里不演示,后面章节有介绍,语法与MP一致
    }

}
