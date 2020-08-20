package com.module.zhupeng.web.layui;


/**
 * LayuiResultBuilder
 * <p>date : 2019-11-19</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class LayuiRBuilder {

    /**
     * 成功
     *
     * @return 返回RestResult
     */
    public static <T> LayuiR<T> success() {
        return new LayuiR<T>();
    }

    /**
     * 成功
     *
     * @param data 结果
     * @return 返回RestResult
     */
    public static <T> LayuiR<T> success(T data) {
        return (new LayuiR<T>()).setData(data);
    }

    /**
     * 成功
     *
     * @param code 自定义code
     * @return 返回RestResult
     */
    public static <T> LayuiR<T> success(long code) {
        return (new LayuiR<T>()).setCode(code);
    }

    /**
     * 成功
     *
     * @param data 结果数据
     * @param code 自定义code
     * @return 返回RestResult
     */
    public static <T> LayuiR<T> success(T data, long code) {
        return (new LayuiR<T>()).setData(data).setCode(code);
    }

    /**
     * 成功
     *
     * @param data    结果数据
     * @param message 提示信息
     * @return 返回RestResult
     */
    public static <T> LayuiR<T> success(T data, String message) {
        return (new LayuiR<T>()).setData(data).setMsg(message);
    }

    /**
     * 成功
     *
     * @param data    结果数据
     * @param message 提示信息
     * @param code    自定义code
     * @return 返回RestResult
     */
    public static <T> LayuiR<T> success(T data, String message, long code) {
        return (new LayuiR<T>()).setData(data).setMsg(message).setCode(code);
    }

    /**
     * 失败
     *
     * @return 返回RestResult
     */
    public static <T> LayuiR<T> failed() {
        return (new LayuiR()).setSuccess(false);
    }

    /**
     * 失败
     *
     * @param message 提示消息
     * @return 返回RestResult
     */
    public static <T> LayuiR<T> failed(String message) {
        return (new LayuiR()).setSuccess(false).setMsg(message);
    }


    /**
     * 失败
     *
     * @param message 提示消息
     * @param data    其他信息
     * @return 返回RestResult
     */
    public static <T> LayuiR<T> failed(String message, T data) {
        return (new LayuiR()).setSuccess(false).setMsg(message).setData(data);
    }

    /**
     * 失败
     *
     * @param message 提示消息
     * @param code    自定义code
     * @return 返回RestResult
     */
    public static <T> LayuiR<T> failed(String message, long code) {
        return (new LayuiR()).setSuccess(false).setMsg(message).setCode(code);
    }
}
