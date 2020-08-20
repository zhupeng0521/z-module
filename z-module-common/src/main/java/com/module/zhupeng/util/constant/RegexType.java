package com.module.zhupeng.util.constant;

import java.io.Serializable;

/**
 * RegexType
 * <p>date : 2019-11-13</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public final class RegexType implements Serializable {

    /**
     * 数字
     */
    public static final String NUMBER = "^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$";
    /**
     * 根号里的数
     * <p>根号里面不能为负数(大于等于0的数)</p>
     */
    public static final String SQUARE_ROOT_IN_NUMBER = "(^\\d+(.[0-9]*)?[0-9]$)|(^\\d+(.[0-9]*)?[1-9]$)|^\\d$";
    /**
     * 年龄正则表达式 ^(?:[1-9][0-9]?|1[01][0-9]|120)$ 匹配0-120岁
     */
    public static final String AGE = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
    /**
     * 邮箱
     **/
    public static final String EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    /**
     * 中文
     **/
    public static final String CHINESE = "^[\u4e00-\u9fa5],{0,}$";
    /**
     * 手机终端
     **/
    public static final String PHONE_TERMINAL_REGEX = "\\b(ip(hone|od)|android|opera m(ob|in)i"
            + "|windows (phone|ce)|blackberry"
            + "|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"
            + "|laystation portable)|nokia|fennec|htc[-_]"
            + "|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
    /**
     * 平板终端
     **/
    public static final String PAD_TERMINAL_REGEX = "\\b(ipad|tablet|(Nexus 7)|up.browser" + "|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
    /**
     * 2020-01-01 15:59:45
     */
    private static final String DATE_YYYY_MM_DD_HH_MM_SS = "(((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9])";

    /**
     * 2020-01-01 15:59
     */
    private static final String DATE_YYYY_MM_DD_HH_MM = "(((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9])";

    /**
     * 2020-01-01
     */
    private static final String DATE_YYYY_MM_DD = "(((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]))";
    /**
     * 2020/12/19 15:59:45
     */
    private static final String DATE_YYYY_MM_DD_HH_MM_SS_2 = "(((19|20)[0-9]{2})/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9])";

    /**
     * 2020/12/19 15:59
     */
    private static final String DATE_YYYY_MM_DD_HH_MM_2 = "(((19|20)[0-9]{2})/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9])";


    /**
     * 20200101155945
     */
    private static final String DATE_YYYY_MM_DD_HH_MM_SS_3 = "(((19|20)[0-9]{2})(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])([01]?[0-9]|2[0-3])[0-5][0-9][0-5][0-9])";

    /**
     * 202001011559
     */
    private static final String DATE_YYYY_MM_DD_HH_MM_3 = "(((19|20)[0-9]{2})(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])([01]?[0-9]|2[0-3])[0-5][0-9])";


    /**
     * 2020/12/19
     */
    private static final String DATE_YYYY_MM_2 = "(((19|20)[0-9]{2})/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01]))";
    /**
     * 20200101
     */
    private static final String DATE_YYYY_MM_3 = "(((19|20)[0-9]{2})(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01]))";
    /**
     * 基本日期
     * <p>yyyy-MM-dd</p>
     * <p>yyyy-MM-dd hh:mm:ss</p>
     * <p>yyyy/MM/dd</p>
     * <p>yyyy/MM/dd hh:mm:ss</p>
     * <p>yyyyMMdd</p>
     * <p>yyyyMMddhh:mm:ss</p>
     */
    public static final String DATE_BASIC = DATE_YYYY_MM_DD_HH_MM_SS + "|" +
            DATE_YYYY_MM_DD_HH_MM + "|" +
            DATE_YYYY_MM_DD + "|" +
            DATE_YYYY_MM_DD_HH_MM_SS_2 + "|" +
            DATE_YYYY_MM_DD_HH_MM_2 + "|" +
            DATE_YYYY_MM_2 + "|" +
            DATE_YYYY_MM_DD_HH_MM_SS_3 + "|" +
            DATE_YYYY_MM_DD_HH_MM_3 + "|" +
            DATE_YYYY_MM_3;


}






















