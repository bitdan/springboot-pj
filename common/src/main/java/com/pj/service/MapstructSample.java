package com.pj.service;

import com.pj.model.PortPool;
import com.pj.model.PortPoolDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @version 1.0
 * @description mapstruct
 * @date 2024/3/8 15:58:11
 */

@Component
@RequiredArgsConstructor
@Slf4j
public class MapstructSample {

    // 实体转换器
    private final PortPoolDTOConverter dtoConverter;

    public void samp() {
        PortPool pre001 = new PortPool("pre001", 8080, 1, 1, "pre002");
        PortPoolDTO dto = dtoConverter.toDto(pre001);
        log.info("dto is : {}", dto);
    }
}
