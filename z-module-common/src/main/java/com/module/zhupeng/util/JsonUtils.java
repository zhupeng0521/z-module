package com.module.zhupeng.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.module.zhupeng.date.DateFormatStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

/**
 * json工具类
 * <p>date : 2019-09-18</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public final class JsonUtils {

    /**
     * <p>jackson</p>
     * <a href="https://github.com/FasterXML/jackson-docs">document</a>
     */
    private static final ObjectMapper BASIC = new ObjectMapper();
    private static final ObjectMapper CUSTOMIZATION = new CustomizationObjectMapper();

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    static {
        BASIC.setDateFormat(new SimpleDateFormat(DateFormatStyle.BAR_END_SECOND.getValue()));
        BASIC.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 转换为Json
     * <p>默认使用Jackson进行转换,{@link #BASIC}</p>
     *
     * @param input 待转换对象
     * @return 如果转换失败返回 <code>null</code> ,否则返回转换后的json
     */
    public static String toJson(Object input) {
        return toJson(BASIC, input);
    }

    /**
     * 转换为Json
     * <p>默认使用Jackson进行转换,{@link #BASIC}</p>
     *
     * @param input 待转换对象
     * @return 如果转换失败返回 <code>null</code> ,否则返回转换后的json
     */
    public static String toCustomizationJson(Object input) {
        return toJson(CUSTOMIZATION, input);
    }

    /**
     * json转换为指定类型
     * <p>默认使用Jackson进行转换,{@link #BASIC}</p>
     * 注意 : 指定类型是内部类会报错 jackson can only instantiate non-static inner class by using default, no-arg
     *
     * @param inputJson  : json
     * @param targetType : 目标类型
     * @param <T>        目标类型
     * @return 如果解析失败返回 <code>null</code> ,否则返回解析后的json
     */
    public static <T> T jsonToType(String inputJson, Class<T> targetType) {

        return jsonToType(BASIC, inputJson, targetType);
    }

    /**
     * json转换为指定类型
     * <p>默认使用Jackson进行转换,{@link #BASIC}</p>
     * 注意 : 指定类型是内部类会报错 jackson can only instantiate non-static inner class by using default, no-arg
     *
     * @param inputJson  : json
     * @param targetType : 目标类型
     * @param <T>        目标类型
     * @return 如果解析失败返回 <code>null</code> ,否则返回解析后的json
     */
    public static <T> List<T> jsonToListType(String inputJson, Class<T> targetType) {
        return jsonToListType(BASIC, inputJson, targetType);
    }

    /**
     * json转换为指定类型(支持泛型)
     * <pre class="code">
     * 示例 :
     * ResponseEntity< User > responseEntity = JsonUtils.jsonToType( jsonValue ,new TypeReference< ResponseEntity< User > >() {} );
     * </pre>
     *
     * @param inputJson  : json
     * @param targetType : 目标类型
     * @param <T>        目标类型
     * @return
     */
    public static <T> T jsonToType(String inputJson, TypeReference targetType) {
        return jsonToType(BASIC, inputJson, targetType);
    }

    public static ObjectMapper getCustomizationMapper() {
        return CUSTOMIZATION;
    }

    public static ObjectMapper buildCustomizationMapper() {
        return new CustomizationObjectMapper();
    }

    public static ObjectMapper getBasicMapper() {
        return BASIC;
    }


    private static <T> T jsonToType(ObjectMapper objectMapper, String inputJson, TypeReference targetType) {
        try {
            return (T) objectMapper.readValue(inputJson, targetType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <T> T jsonToType(ObjectMapper objectMapper, String inputJson, Class<T> targetType) {

        try {
            return objectMapper.readValue(inputJson, targetType);
        } catch (Exception e) {
            LOGGER.error("jsonToType", e);
        }
        return null;
    }

    private static <T> List<T> jsonToListType(ObjectMapper objectMapper,
                                              String inputJson,
                                              Class<T> targetType) {

        try {
            return objectMapper.readValue(
                    inputJson,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, targetType)
            );
        } catch (Exception e) {
            LOGGER.error("jsonToListType", e);
        }
        return null;
    }

    private static String toJson(ObjectMapper objectMapper, Object input) {
        try {
            return objectMapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            LOGGER.error("toJson", e);
        }
        return null;
    }

    private static class CustomizationObjectMapper extends ObjectMapper {

        private CustomizationObjectMapper(DateFormat dateFormat) {
            super();
            // 设置格式化
            setDateFormat(dateFormat);
            //解决时区差8小时问题
            setTimeZone(TimeZone.getTimeZone("GMT+8"));
            // <code>null<code> 不序列化
            setSerializationInclusion(JsonInclude.Include.NON_NULL);
            // 遇到未知属性,略过
            configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        private CustomizationObjectMapper() {
            // 默认只支持 yyyy-MM-dd HH:mm:ss
            this(new SimpleDateFormat(DateFormatStyle.BAR_END_SECOND.getValue()));

            // 生成json时，将所有Long转换成String
            SimpleModule simpleModule = new SimpleModule();
            simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
            simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
            registerModule(simpleModule);

        }


    }

}
