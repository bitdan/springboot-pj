package com.pj.system.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 全局应用AKSK视图对象 t_global_app_aksk
 *
 * @author linger
 * @date 2024-03-12
 */
@Data
@ExcelIgnoreUnannotated
public class TGlobalAppAkskVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 应用AKSK的ID
     */
    @ExcelProperty(value = "应用AKSK的ID")
    private Long id;

    /**
     * 应用编码
     */
    @ExcelProperty(value = "应用编码")
    private String appCode;

    /**
     * 应用访问密钥AK
     */
    @ExcelProperty(value = "应用访问密钥AK")
    private String ak;

    /**
     * 应用私有访问密钥SK
     */
    @ExcelProperty(value = "应用私有访问密钥SK")
    private String sk;

    /**
     * 应用私有访问密钥SK MAC
     */
    @ExcelProperty(value = "应用私有访问密钥SK MAC")
    private String skMac;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private Long status;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createDate;

    /**
     * 过期时间
     */
    @ExcelProperty(value = "过期时间")
    private Date expiryDate;

    /**
     * 扩展字段1
     */
    @ExcelProperty(value = "扩展字段1")
    private String extend1;

    /**
     * 扩展字段2
     */
    @ExcelProperty(value = "扩展字段2")
    private String extend2;

    /**
     * 扩展字段3
     */
    @ExcelProperty(value = "扩展字段3")
    private String extend3;


}
