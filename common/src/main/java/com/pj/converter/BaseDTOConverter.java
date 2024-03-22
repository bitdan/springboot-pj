package com.pj.converter;

import java.util.List;

/**
 * @Description: 实体类和DTO类转换接口，使用mapstruct进行实现
 * @Date: 2021/6/19 16:41
 */
public interface BaseDTOConverter<DTO, PO> {
    /**
     * DTO转PO
     *
     * @param dto /
     * @return /
     */
    PO toPO(DTO dto);

    /**
     * PO转DTO
     *
     * @param po /
     * @return /
     */
    DTO toDto(PO po);

    /**
     * DTO集合转PO集合
     *
     * @param dtoList /
     * @return /
     */
    List<PO> toPO(List<DTO> dtoList);

    /**
     * PO集合转DTO集合
     *
     * @param poList /
     * @return /
     */
    List<DTO> toDto(List<PO> poList);
}
