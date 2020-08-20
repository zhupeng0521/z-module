package com.module.zhupeng.date.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.module.zhupeng.date.DateConstants;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * LocalDateTimeSerializer
 * <p>date : 2019-11-07</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class LocalDateTimeSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(((LocalDateTime) value).format(DateConstants.DATETIME_FORMATTER));
    }
}
