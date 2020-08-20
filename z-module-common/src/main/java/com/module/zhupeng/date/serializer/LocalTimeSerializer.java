package com.module.zhupeng.date.serializer;

import com.digiwin.bpm.module.common.date.DateConstants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalTime;

/**
 * LocalTimeSerializer
 * <p>date : 2019-11-07</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class LocalTimeSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(((LocalTime) value).format(DateConstants.TIME_FORMATTER));
    }
}
