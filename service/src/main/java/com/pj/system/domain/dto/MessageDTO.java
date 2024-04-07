package com.pj.system.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @description MessageDTO
 * @date 2024/4/7 16:36:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    public String bindingName;
    public String message;
}
