package com.module.zhupeng.util;

import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

/**
 * ResponseUtil
 * <p>date : 2019-12-07</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
@Deprecated
public class ResponseUtil {
    public static void out(HttpServletResponse response, Object data) {
        response.setHeader("Content-type", MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
    }
}
