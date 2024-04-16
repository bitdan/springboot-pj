package com.pj.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述: 服务映射端口配置管理数据返回DTO
 * <p>
 *
 * @version 1.0
 * @created Mon Apr 17 17:42:16 CST 2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortPoolDTO {

    private String code;
    private Integer port;
    private Integer portType;
    private Integer status;
    private boolean once;
    private String secondOne;
}
