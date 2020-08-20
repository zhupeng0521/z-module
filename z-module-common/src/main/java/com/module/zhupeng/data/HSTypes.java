/**
 * FileName: HSTypes
 * Author:   DONGSK
 * Datetime: 2020/4/8 20:28
 * Description: 数据类型
 * History:
 * 作者姓名 --修改时间 --版本号--描述
 */
package com.module.zhupeng.data;

import org.hibernate.type.*;

import java.util.HashMap;
import java.util.Map;

/**
 * HSTypes
 * 数据类型
 *
 * @author DONGSK
 * @date 2020/4/8
 * @since 1.0.0
 */
public final class HSTypes {

    private HSTypes() {
    }

    private static Map<String, Type> _HSTypes = new HashMap<>();

    public static Type get(String key) {
        return _HSTypes.get(key);
    }

    public static final String BOOLEAN = "BOOLEAN";
    public static final String BYTE = "BYTE";
    public static final String INTEGER = "INTEGER";
    public static final String LONG = "LONG";
    public static final String FLOAT = "FLOAT";
    public static final String DOUBLE = "DOUBLE";
    public static final String STRING = "STRING";
    public static final String TIME = "TIME";
    public static final String DATE = "DATE";
    public static final String TIMESTAMP = "TIMESTAMP";
    public static final String TEXT = "TEXT";
    public static final String N_TEXT = "N_TEXT";
    public static final String N_STRING = "N_STRING";

    static {
        _HSTypes.put(N_STRING, StringNVarcharType.INSTANCE);
        _HSTypes.put(N_TEXT, NTextType.INSTANCE);
        _HSTypes.put(TEXT, TextType.INSTANCE);
        _HSTypes.put(TIMESTAMP, TimestampType.INSTANCE);
        _HSTypes.put(DATE, DateType.INSTANCE);
        _HSTypes.put(TIME, TimeType.INSTANCE);
        _HSTypes.put(STRING, StringType.INSTANCE);
        _HSTypes.put(DOUBLE, DoubleType.INSTANCE);
        _HSTypes.put(FLOAT, FloatType.INSTANCE);
        _HSTypes.put(LONG, LongType.INSTANCE);
        _HSTypes.put(INTEGER, IntegerType.INSTANCE);
        _HSTypes.put(BYTE, ByteType.INSTANCE);
        _HSTypes.put(BOOLEAN, BooleanType.INSTANCE);
    }

}
