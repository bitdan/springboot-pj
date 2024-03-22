package com.pj.system.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.pj.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/**
 * 全局应用AKSK对象 t_global_app_aksk
 *
 * @author linger
 * @date 2024-03-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_global_app_aksk")
public class TGlobalAppAksk extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 应用AKSK的ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 应用编码
     */
    private String appCode;
    /**
     * 应用访问密钥AK
     */
    private String ak;
    /**
     * 应用私有访问密钥SK
     */
    private String sk;
    /**
     * 应用私有访问密钥SK MAC
     */
    private String skMac;
    /**
     * 状态
     */
    private Long status;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 过期时间
     */
    private Date expiryDate;
    /**
     *
     */
    @Version
    private Long version;
    /**
     * 扩展字段1
     */
    private String extend1;
    /**
     * 扩展字段2
     */
    private String extend2;
    /**
     * 扩展字段3
     */
    private String extend3;

}
