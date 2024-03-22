package com.pj.system.esmapper;

import lombok.Data;
import org.dromara.easyes.annotation.IndexName;

/**
 * @version 1.0
 * @description 文件
 * @date 2024/3/15 17:05:34
 */
@Data
@IndexName
public class Document {
    /**
     * es中的唯一id
     */
    private String id;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档内容
     */
    private String content;
}
