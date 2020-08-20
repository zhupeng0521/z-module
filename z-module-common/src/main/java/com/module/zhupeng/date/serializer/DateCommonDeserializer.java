package com.module.zhupeng.date.serializer;

import com.digiwin.bpm.module.common.date.DateFormatStyle;
import com.digiwin.bpm.module.common.date.DateRegex;
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
public class DateCommonDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // 判断分隔符是"/"还是"-"
        String valueString = p.getValueAsString();

        if (DateRegex.isOK(valueString, DateRegex.BAR_END_SECOND_PATTERN)) {
            return DateUtils.formatToDate(p.getValueAsString(), DateFormatStyle.BAR_END_SECOND);
        }

        if (DateRegex.isOK(valueString, DateRegex.SLASH_END_SECOND_PATTERN)) {
            return DateUtils.formatToDate(p.getValueAsString(), DateFormatStyle.SLASH_END_SECOND);
        }

        if (DateRegex.isOK(valueString, DateRegex.BAR_END_MINUTE_PATTERN)) {
            return DateUtils.formatToDate(p.getValueAsString(), DateFormatStyle.BAR_END_MINUTE);
        }

        if (DateRegex.isOK(valueString, DateRegex.SLASH_END_MINUTE_PATTERN)) {
            return DateUtils.formatToDate(p.getValueAsString(), DateFormatStyle.SLASH_END_MINUTE);
        }


        if (DateRegex.isOK(valueString, DateRegex.STRING_END_SECOND_PATTERN)) {
            return DateUtils.formatToDate(p.getValueAsString(), DateFormatStyle.STRING_END_SECOND);
        }

        if (DateRegex.isOK(valueString, DateRegex.STRING_END_MINUTE_PATTERN)) {
            return DateUtils.formatToDate(p.getValueAsString(), DateFormatStyle.STRING_END_MINUTE);
        }


        if (DateRegex.isOK(valueString, DateRegex.BAR_END_DAY_PATTERN)) {
            return DateUtils.formatToDate(p.getValueAsString(), DateFormatStyle.BAR_END_DAY);
        }

        if (DateRegex.isOK(valueString, DateRegex.SLASH_END_DAY_PATTERN)) {
            return DateUtils.formatToDate(p.getValueAsString(), DateFormatStyle.SLASH_END_DAY);
        }

        if (DateRegex.isOK(valueString, DateRegex.STRING_END_DAY_PATTERN)) {
            return DateUtils.formatToDate(p.getValueAsString(), DateFormatStyle.STRING_END_DAY);
        }

        return null;
    }
}
