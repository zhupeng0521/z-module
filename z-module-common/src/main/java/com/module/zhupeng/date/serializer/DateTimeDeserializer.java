package com.module.zhupeng.date.serializer;

import com.digiwin.bpm.module.common.date.DateFormatStyle;
import com.digiwin.bpm.module.common.date.DateUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Date;

/**
 * LocalDateDeserializer
 * <p>date : 2019-11-07</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class DateTimeDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return DateUtils.formatToDate(p.getValueAsString(), DateFormatStyle.BAR_END_SECOND);
    }
}
