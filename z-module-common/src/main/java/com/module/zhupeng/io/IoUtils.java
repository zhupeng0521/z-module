/**
 * FileName: IOUtil
 * Author:   DONGSK
 * Datetime: 2020/3/24 16:26
 * Description: IO 帮助类
 * History:
 * 作者姓名 --修改时间 --版本号--描述
 */
package com.module.zhupeng.io;

import com.digiwin.bpm.module.common.util.Charsets;
import com.digiwin.bpm.module.common.util.LogUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.StreamUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * IOUtil
 * IO 帮助类
 *
 * @author DONGSK
 * @date 2020/3/24
 * @since 1.0.0
 */
public class IoUtils extends StreamUtils {
    public static void closeQuietly(@Nullable Closeable closeable) {
        try {
            if (closeable != null)
                closeable.close();
        } catch (IOException iOException) {
            LogUtils.getLogger().error(iOException.getMessage());
        }
    }

    public static String toString(InputStream input) throws IOException {
        return toString(input, Charsets.UTF_8);
    }

    public static String toString(@Nullable InputStream input, Charset charset) throws IOException {
        try {
            return copyToString(input, charset);
        } catch (IOException e) {
            throw e;
        } finally {
            closeQuietly(input);
        }
    }

    public static byte[] toByteArray(@Nullable InputStream input) throws IOException {
        try {
            return copyToByteArray(input);
        } catch (IOException e) {
            throw e;
        } finally {
            closeQuietly(input);
        }
    }

    public static void write(@Nullable String data, OutputStream output, Charset encoding) throws IOException {
        if (data != null)
            output.write(data.getBytes(encoding));
    }
}
