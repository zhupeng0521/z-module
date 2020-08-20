package com.module.zhupeng.date;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DateFormatStyle
 * <p>date : 2019-09-18</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/

public enum DateFormatStyle {

    HOUR_MINUTE_SECOND("HH:mm:ss"),
    HOUR_MINUTE("HH:mm"),
    BAR_END_MS("yyyy-MM-dd HH:mm:ss.SSS"),
    BAR_END_SECOND("yyyy-MM-dd HH:mm:ss"),
    BAR_END_MINUTE("yyyy-MM-dd HH:mm"),
    BAR_END_HOUR("yyyy-MM-dd HH"),
    BAR_END_DAY("yyyy-MM-dd"),
    BAR_END_MONTH("yyyy-MM"),
    SLASH_END_MS("yyyy/MM/dd HH:mm:ss.SSS"),
    SLASH_END_SECOND("yyyy/MM/dd HH:mm:ss"),
    SLASH_END_MINUTE("yyyy/MM/dd HH:mm"),
    SLASH_END_HOUR("yyyy/MM/dd HH:mm"),
    SLASH_END_DAY("yyyy/MM/dd"),
    SLASH_END_MONTH("yyyy/MM"),
    STRING_END_MS("yyyyMMddHHmmssSSS"),
    STRING_END_SECOND("yyyyMMddHHmmss"),
    STRING_END_MINUTE("yyyyMMddHHmm"),
    STRING_END_HOUR("yyyyMMddHH"),
    STRING_END_DAY("yyyyMMdd"),
    STRING_END_MONTH("yyyyMM"),
    ISO_DATETIME_TIME_ZONE_FORMAT("yyyy-MM-dd'T'HH:mm:ssZZ"),
    ISO_DATETIME_FORMAT("yyyy-MM-dd'T'HH:mm:ss"),
    ISO_DATE_TIME_ZONE_FORMAT("yyyy-MM-ddZZ"),
    ISO_TIME_TIME_ZONE_FORMAT("'T'HH:mm:ssZZ"),
    ISO_TIME_FORMAT("'T'HH:mm:ss"),
    ISO_TIME_NO_T_TIME_ZONE_FORMAT("HH:mm:ssZZ"),
    ISO_TIME_NO_T_FORMAT("HH:mm:ss");

    private String value;

    DateFormatStyle(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * @return 按照默认声明顺序
     */
    public static List<String> getDateFormatStyles() {
        return Arrays.stream(DateFormatStyle.values())
                .map(DateFormatStyle::getValue)
                .collect(Collectors.toList());
    }


}
