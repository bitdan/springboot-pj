package com.pj.service;

import com.pj.converter.BaseDTOConverter;
import com.pj.model.PortPool;
import com.pj.model.PortPoolDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 描述: 服务映射端口配置管理 PortPoolDTODTO和PortPool转换器
 * <p>
 *
 * @version 1.0
 * @created Mon Apr 17 17:42:16 CST 2023
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PortPoolDTOConverter extends BaseDTOConverter<PortPoolDTO, PortPool> {

}
