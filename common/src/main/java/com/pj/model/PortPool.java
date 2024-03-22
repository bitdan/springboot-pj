package com.pj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 描述: 服务映射端口配置管理PO类
 * <p>
 *
 * @version 1.0
 * @created Mon Apr 17 17:42:16 CST 2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PortPool {

    /**
     * 机构编码
     */

    private String orgCode;
    /**
     * 端口
     */

    private Integer port;

    /**
     * 端口应用类别，0-VSM代理端口；1-密码服务管理端代理端口；2-密码服务业务端口
     */

    private Integer portType;

    /**
     * 应用状态, 状态：0: 未用  1：启用
     */

    private Integer status;
}
