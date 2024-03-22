package com.pj.system.domain.bo;


import com.pj.core.domain.BaseEntity;
import com.pj.core.validate.AddGroup;
import com.pj.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * 全局应用AKSK业务对象 t_global_app_aksk
 *
 * @author linger
 * @date 2024-03-12
 */

@Data
@EqualsAndHashCode(callSuper = true)

public class TGlobalAppAkskBo extends BaseEntity {

    /**
     * 应用AKSK的ID
     */
    @NotNull(message = "应用AKSK的ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 应用编码
     */
    @NotBlank(message = "应用编码不能为空", groups = {AddGroup.class, EditGroup.class})
    private String appCode;

    /**
     * 应用访问密钥AK
     */
    @NotBlank(message = "应用访问密钥AK不能为空", groups = {AddGroup.class, EditGroup.class})
    private String ak;

    /**
     * 应用私有访问密钥SK
     */
    @NotBlank(message = "应用私有访问密钥SK不能为空", groups = {AddGroup.class, EditGroup.class})
    private String sk;

    /**
     * 应用私有访问密钥SK MAC
     */
    @NotBlank(message = "应用私有访问密钥SK MAC不能为空", groups = {AddGroup.class, EditGroup.class})
    private String skMac;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long status;

    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date createDate;

    /**
     * 过期时间
     */
    @NotNull(message = "过期时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date expiryDate;

    /**
     * 扩展字段1
     */
    @NotBlank(message = "扩展字段1不能为空", groups = {AddGroup.class, EditGroup.class})
    private String extend1;

    /**
     * 扩展字段2
     */
    @NotBlank(message = "扩展字段2不能为空", groups = {AddGroup.class, EditGroup.class})
    private String extend2;

    /**
     * 扩展字段3
     */
    @NotBlank(message = "扩展字段3不能为空", groups = {AddGroup.class, EditGroup.class})
    private String extend3;


}
