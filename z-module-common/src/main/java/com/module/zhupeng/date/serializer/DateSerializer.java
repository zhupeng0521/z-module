package com.module.zhupeng.date.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.module.zhupeng.date.DateFormatStyle;
import com.module.zhupeng.date.DateUtils;

import java.io.IOException;
import java.util.Date;

/**
 * LocalDateSerializer
 * <p>date : 2019-11-07</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class DateSerializer extends JsonSerializer<Object> {
    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(DateUtils.formatToString((Date) value, DateFormatStyle.BAR_END_DAY));
    }
}

