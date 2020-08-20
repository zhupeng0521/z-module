package com.module.zhupeng.web.api;

import java.util.HashMap;
import java.util.Map;

/**
 * ApiResult
 * <p>date : 2019-11-19</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class R<T> {
    /**
     * 是否成功
     */
    boolean success = true;
    /**
     * 消息
     */
    String message = "";

    /**
     * 数据
     */
    T data;

    /**
     * 自定义执行结果代号；默认：200=成功，-200：操作失败，其他自定义
     */
    long code = 200;

    /**
     * 接口执行的时间
     */
    long duration;

    public long getDuration() {
        return duration;
    }

    public R<T> setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    Map<String, Object> payload = new HashMap<>(8);

    public boolean isSuccess() {
        return success;
    }

    public R<T> setSuccess(boolean success) {
        this.success = success;
        this.code = success ? 200 : -200;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public R<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public R<T> setData(T data) {
        this.data = data;
        return this;
    }

    public long getCode() {
        return code;
    }

    public R<T> setCode(long code) {
        this.code = code;
        return this;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public R<T> setPayload(Map<String, Object> payload) {
        this.payload = payload;
        return this;
    }

    public R<T> putPayload(Map<String, Object> payload) {
        this.payload.putAll(payload);
        return this;
    }

    public R<T> addPayload(String key, Object value) {
        this.payload.put(key, value);
        return this;
    }
}
