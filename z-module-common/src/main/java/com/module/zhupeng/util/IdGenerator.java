package com.module.zhupeng.util;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * ID生成器
 *
 * @author DONGSK
 */
public class IdGenerator {

    private static SecureRandom random = new SecureRandom();


    /**
     * 封装JDK自带的UUID, 通过Random生成
     *
     * @return 返回uuid
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }


    /**
     * 封装JDK自带的UUID, 通过Random生成,自定义分隔符
     *
     * @param delimiter 分隔符
     * @return 返回uuid
     */
    public static String uuid(String delimiter) {
        return uuid().replaceAll("-", delimiter);
    }

    /**
     * 封装JDK自带的UUID, 通过Random生成, 中间无-分割.
     *
     * @return 返回uuid
     */
    public static String uuidNoDelimiter() {
        return uuid().replaceAll("-", "");
    }


    /**
     * 使用SecureRandom随机生成Long.
     *
     * @return randomLong
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * Twitter的Snowflake算法生成主键Id
     * 53bitID由32bit秒级时间戳+16bit自增+5bit机器标识组成，累积32台机器，每秒可以生成6.5万个序列号
     *
     * @return snowflakeId
     */
    public static long snowflakeId() {
        return SnowflakeIdUtil.nextId();
    }

}
