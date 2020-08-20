package com.module.zhupeng.web.api;

/**
 * ApiResultBuilder
 * <p>date : 2019-11-19</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class RBuilder {

    /**
     * 成功
     *
     * @return 返回RestResult
     */
    public static <T> R<T> success() {
        return new R<T>();
    }

    /**
     * 成功
     *
     * @param data 结果
     * @return 返回RestResult
     */
    public static <T> R<T> success(T data) {
        return (new R<T>()).setData(data);
    }

    /**
     * 成功
     *
     * @param code 自定义code
     * @return 返回RestResult
     */
    public static <T> R<T> success(long code) {
        return (new R<T>()).setCode(code);
    }

    /**
     * 成功
     *
     * @param data 结果数据
     * @param code 自定义code
     * @return 返回RestResult
     */
    public static <T> R<T> success(T data, long code) {
        return (new R<T>()).setData(data).setCode(code);
    }

    /**
     * 成功
     *
     * @param data    结果数据
     * @param message 提示信息
     * @return 返回RestResult
     */
    public static <T> R<T> success(T data, String message) {
        return (new R<T>()).setData(data).setMessage(message);
    }

    /**
     * 成功
     *
     * @param data    结果数据
     * @param message 提示信息
     * @param code    自定义code
     * @return 返回RestResult
     */
    public static <T> R<T> success(T data, String message, long code) {
        return (new R<T>()).setData(data).setMessage(message).setCode(code);
    }

    /**
     * 失败
     *
     * @return 返回RestResult
     */
    public static <T> R<T> failed() {
        return (new R()).setSuccess(false);
    }

    /**
     * 失败
     *
     * @param message 提示消息
     * @return 返回RestResult
     */
    public static <T> R<T> failed(String message) {
        return (new R()).setSuccess(false).setMessage(message);
    }


    /**
     * 失败
     *
     * @param message 提示消息
     * @param data    其他信息
     * @return 返回RestResult
     */
    public static <T> R<T> failed(String message, T data) {
        return (new R()).setSuccess(false).setMessage(message).setData(data);
    }

    /**
     * 失败
     *
     * @param message 提示消息
     * @param code    自定义code
     * @return 返回RestResult
     */
    public static <T> R<T> failed(String message, long code) {
        return (new R()).setSuccess(false).setMessage(message).setCode(code);
    }
}
