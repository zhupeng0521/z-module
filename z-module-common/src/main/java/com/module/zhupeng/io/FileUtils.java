package com.module.zhupeng.io;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FileUtils 文件操作类
 * <p>date : 2019-12-16</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class FileUtils {

    public static final String FOLDER_SEPARATOR = "/";
    public static final char EXTENSION_SEPARATOR = '.';

    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";

    public static String joinPath(String... strings) {
        return joinPathWith(FOLDER_SEPARATOR, Arrays.asList(strings));
    }

    public static String joinPath(List<String> strings) {
        return joinPathWith(FOLDER_SEPARATOR, strings);
    }

    public static String joinPathWith(String separator, List<String> strings) {
        return strings.stream().collect(Collectors.joining(separator));
    }

    /**
     * 文件名称验证
     *
     * @param filename 文件名称
     * @return true 正常 false 非法
     */
    public static boolean isValidFilename(String filename) {
        return filename.matches(FILENAME_PATTERN);
    }

    /**
     * 下载文件名重新编码
     *
     * @param request  请求对象
     * @param fileName 文件名
     * @return 编码后的文件名
     */
    public static String setFileDownloadHeader(HttpServletRequest request, String fileName)
            throws UnsupportedEncodingException {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        } else if (agent.contains("Chrome")) {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }

    /**
     * 输出指定文件的byte数组
     *
     * @param filePath 文件路径
     * @param os       输出流
     * @return
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException {
        FileInputStream fis = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * @param filePath 指定的文件路径
     * @return 存在返回TRUE，不存在返回FALSE
     */
    public static boolean isExist(String filePath) {
        return isExist(filePath, false);
    }

    /**
     * @param filePath 指定的文件路径
     * @param isNew    true：新建、false：不新建
     * @return 存在返回TRUE，不存在返回FALSE
     */
    public static boolean isExist(String filePath, boolean isNew) {
        File file = new File(filePath);
        boolean result = file.exists();
        if (!result && isNew) {
            return file.mkdirs();    //新建文件路径
        }
        return result;
    }

    /**
     * 追加写入文件
     *
     * @param fileName 文件路径
     * @param content  内容
     * @return
     */
    public static boolean append(String fileName, String... content) {
        if (content.length > 0) {
            return write(fileName, Arrays.asList(content).stream().collect(Collectors.joining("\n")));
        }
        return false;

    }

    /**
     * 追加写入文件
     *
     * @param fileName 文件路径
     * @param lines    内容
     * @return
     */
    public static boolean append(String fileName, List<String> lines) {
        return write(fileName, lines.stream().collect(Collectors.joining("\n")));
    }

    /**
     * 追加写入文件
     *
     * @param fileName 文件路径
     * @param content  内容
     * @return
     */
    public static boolean write(String fileName, String content) {
        boolean result = Boolean.FALSE;
        FileWriter fileWriter = null;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file, true);
            fileWriter.write(content);
            result = true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileWriter) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static String readContent(String fileName) {
        return read(fileName).stream().collect(Collectors.joining(""));
    }

    /**
     * 读取一个文件，返回所有行，默认UTF-8编码
     *
     * @param fileName
     * @return List
     */
    public static List<String> read(String fileName) {
        return read(fileName, StandardCharsets.UTF_8);
    }

    /**
     * 读取一个文件，返回所有行
     *
     * @param fileName 文件路径
     * @param charset  字符集
     * @return List
     */
    public static List<String> read(String fileName, Charset charset) {
        List<String> result = new ArrayList<>();
        if (StringUtils.isNotBlank(fileName)) {
            File file = new File(fileName);
            if (file.exists()) {
                ReadByLine readByLine = new ReadByLine(fileName);
                result = readByLine.toArray();
            }
        }
        return result;
    }

    public static File getAbsoluteFile(String absolutionDir, String fileName) {
        File desc = new File(absolutionDir + File.separator + fileName);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        return desc;
    }

    /**
     * 删除目录或文件
     *
     * @param path 目录或文件路径
     */
    public static void delete(String path) {
        boolean result = Boolean.FALSE;
        if (StringUtils.isNotBlank(path)) {
            File file = new File(path);
            if (!file.exists()) {
                return;
            }

            if (file.isDirectory()) {
                String[] fileList = file.list();
                for (String f : fileList) {
                    String tmpPath = path + File.separator + f;
                    File tmpFile = new File(tmpPath);
                    if (tmpFile.isDirectory()) {
                        delete(tmpPath);
                    } else {
                        tmpFile.delete();
                    }
                }
            } else {
                file.delete();
            }
        }
    }

    /**
     * 文件重命名
     *
     * @param oldFileName 老文件
     * @param newFileName 新文件
     */
    public boolean rename(String oldFileName, String newFileName) {
        File oldFile = new File(oldFileName);// 文件或目录
        File newFile = new File(newFileName);// 文件或目录
        return oldFile.renameTo(newFile);// 重命名
    }

    /**
     * 复制文件或者文件夹
     *
     * @param fromFile    源文件
     * @param toFile      目的文件
     * @param isOverWrite 是否覆盖文件
     * @throws IOException
     */
    public static void copy(File fromFile, File toFile, boolean isOverWrite)
            throws IOException {
        if (!fromFile.exists()) {
            throw new RuntimeException(fromFile.getPath() + "源目录不存在!");
        }
        doCopy(fromFile, toFile, isOverWrite);
    }

    /**
     * 复制文件或者文件夹
     *
     * @param fromFile    源文件
     * @param toFile      目的文件
     * @param isOverWrite 是否覆盖文件
     * @throws IOException
     */
    private static void doCopy(File fromFile, File toFile, boolean isOverWrite) throws IOException {
        if (fromFile.isFile()) {        //文件
            copySimpleFile(fromFile, toFile, isOverWrite);
        } else {
            if (!toFile.exists()) {        //文件夹
                toFile.mkdirs();
            }
            // 循环子文件夹
            for (File child : fromFile.listFiles()) {
                copy(child, new File(toFile.getPath() + "/" + child.getName()), isOverWrite);
            }
        }
    }

    /**
     * 复制单个文件
     *
     * @param inputFile   源文件
     * @param outputFile  目的文件
     * @param isOverWrite 是否覆盖
     * @throws IOException
     */
    private static void copySimpleFile(File inputFile, File outputFile,
                                       boolean isOverWrite) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            if (outputFile.exists()) {
                if (isOverWrite) {        //可以覆盖
                    if (!outputFile.delete()) {
                        throw new RuntimeException(outputFile.getPath() + "无法覆盖！");
                    }
                } else {
                    // 不允许覆盖
                    return;
                }
            }
            in = new FileInputStream(inputFile);
            out = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            closeInputStream(in);
            closeOutputStream(out);
        }

    }

    private static void closeInputStream(InputStream stream) {
        try {
            if (null != stream) {
                stream.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void closeOutputStream(OutputStream stream) {
        try {
            if (null != stream) {
                stream.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 创建目录，如果目录已存在，则什么也不做
     *
     * @param path 目录
     * @return boolean 是否成功
     */
    public static boolean mkdir(String path) {
        boolean result = Boolean.FALSE;
        if (StringUtils.isNotBlank(path)) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
            result = Boolean.TRUE;
        }
        return result;
    }

    /**
     * 创建目录，如果目录已存在，则什么也不做
     *
     * @param path 目录
     * @return boolean 是否成功
     */
    public static boolean mkdirs(String path) {
        boolean result = Boolean.FALSE;
        if (StringUtils.isNotBlank(path)) {
            File file = new File(path);
            file.mkdirs();
            result = Boolean.TRUE;
        }
        return result;
    }

    /**
     * 获取文件的后缀
     *
     * @param fileName 文件
     * @return file type
     */
    public static String getFileSuffix(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        int extIndex = fileName.lastIndexOf(EXTENSION_SEPARATOR);
        if (extIndex == -1) {
            return null;
        }
        int folderIndex = fileName.lastIndexOf(FOLDER_SEPARATOR);
        if (folderIndex > extIndex) {
            return null;
        }
        return fileName.substring(extIndex + 1);
    }

    /**
     * 获取文件的MD5
     *
     * @param file 文件
     * @return MD5 String
     */
    public static String getFileMD5(File file) {
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeInputStream(in);
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    public static File getFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(filePath + "File Not Found !");
        }
        return file;
    }

    public static InputStream getInputStream(String filePath) throws FileNotFoundException {
        return new FileInputStream(filePath);
    }

    public static Reader getFileReader(String file) throws FileNotFoundException {
        return new FileReader(file);
    }

}
