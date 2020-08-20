/**
 * FileName: Charsets
 * Author:   DONGSK
 * Datetime: 2020/3/24 16:31
 * Description: Charsets
 * History:
 * 作者姓名 --修改时间 --版本号--描述
 */
package com.module.zhupeng.util;

import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

/**
 * Charsets
 * Charsets
 *
 * @author DONGSK
 * @date 2020/3/24
 * @since 1.0.0
 */

public class Charsets {
    public static final Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;

    public static final String ISO_8859_1_NAME = ISO_8859_1.name();

    public static final Charset GBK = Charset.forName("GBK");

    public static final String GBK_NAME = GBK.name();

    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static final String UTF_8_NAME = UTF_8.name();

    public static Charset charset(String charsetName) throws UnsupportedCharsetException {
        return StringUtils.isEmpty(charsetName) ? Charset.defaultCharset() : Charset.forName(charsetName);
    }
}
