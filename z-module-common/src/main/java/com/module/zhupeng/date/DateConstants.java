package com.module.zhupeng.date;

import java.time.format.DateTimeFormatter;

/**
 * DateConstants
 * <p>date : 2019-11-07</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public interface DateConstants {
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
}
