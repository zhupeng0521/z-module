package com.module.zhupeng.date;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DateRegex
 * <p>date : 2020-01-16</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public final class DateRegex {

    /**
     * 2016-12-19 15:59:45
     */
    private static final String BAR_END_SECOND = "(((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9])";
    public static final Pattern BAR_END_SECOND_PATTERN = Pattern.compile(BAR_END_SECOND);

    /**
     * 2016-12-19 15:59
     */
    private static final String BAR_END_MINUTE = "(((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9])";
    public static final Pattern BAR_END_MINUTE_PATTERN = Pattern.compile(BAR_END_MINUTE);

    /**
     * 2016-12-19
     */
    private static final String BAR_END_DAY = "(((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]))";
    public static final Pattern BAR_END_DAY_PATTERN = Pattern.compile(BAR_END_DAY);
    /**
     * 2016/12/19 15:59:45
     */
    private static final String SLASH_END_SECOND = "(((19|20)[0-9]{2})/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9])";
    public static final Pattern SLASH_END_SECOND_PATTERN = Pattern.compile(SLASH_END_SECOND);
    /**
     * 2016/12/19 15:59
     */
    private static final String SLASH_END_MINUTE = "(((19|20)[0-9]{2})/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01]) ([01]?[0-9]|2[0-3]):[0-5][0-9])";
    public static final Pattern SLASH_END_MINUTE_PATTERN = Pattern.compile(SLASH_END_MINUTE);
    /**
     * 2016/12/19
     */
    private static final String SLASH_END_DAY = "(((19|20)[0-9]{2})/(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01]))";
    public static final Pattern SLASH_END_DAY_PATTERN = Pattern.compile(SLASH_END_DAY);
    /**
     * 20161219155945
     */
    private static final String STRING_END_SECOND = "(((19|20)[0-9]{2})(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])([01]?[0-9]|2[0-3])[0-5][0-9][0-5][0-9])";
    public static final Pattern STRING_END_SECOND_PATTERN = Pattern.compile(STRING_END_SECOND);
    /**
     * 201612191559
     */
    private static final String STRING_END_MINUTE = "(((19|20)[0-9]{2})(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01])([01]?[0-9]|2[0-3])[0-5][0-9])";
    public static final Pattern STRING_END_MINUTE_PATTERN = Pattern.compile(STRING_END_MINUTE);
    /**
     * 20161219
     */
    private static final String STRING_END_DAY = "(((19|20)[0-9]{2})(0?[1-9]|1[012])(0?[1-9]|[12][0-9]|3[01]))";
    public static final Pattern STRING_END_DAY_PATTERN = Pattern.compile(STRING_END_DAY);

    public static boolean isOK(final String inputTime, final Pattern pattern) {
        Matcher matcher = pattern.matcher(inputTime);
        return matcher.matches();
    }


}
