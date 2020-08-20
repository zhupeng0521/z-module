package com.module.zhupeng.date.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.module.zhupeng.date.DateFormatStyle;
import com.module.zhupeng.date.DateUtils;

import java.io.IOException;
import java.util.Date;

/**
 * LocalDateDeserializer
 * <p>date : 2019-11-07</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class TimeDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return DateUtils.formatToDate(p.getValueAsString(), DateFormatStyle.HOUR_MINUTE_SECOND);
    }
}
