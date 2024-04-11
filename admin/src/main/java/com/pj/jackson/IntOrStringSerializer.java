package com.pj.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.kubernetes.client.custom.IntOrString;

import java.io.IOException;

/**
 * @version 1.0
 * @description IntOrStringSerializer
 * @date 2024/4/9 11:17:06
 */
public class IntOrStringSerializer extends JsonSerializer<IntOrString> {
    @Override
    public void serialize(
            IntOrString value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {

        if (value.isInteger()) {
            jgen.writeNumber(value.getIntValue());
        } else {
            if (value.getStrValue() instanceof String) {
                jgen.writeString(value.getStrValue());
            } else {
                jgen.writeString(value.getStrValue().toString());
            }
        }
    }
}
