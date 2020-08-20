package com.module.zhupeng.web.layui;

import java.util.HashMap;
import java.util.Map;

/**
 * ApiResult
 * <p>date : 2020-02-26</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class LayuiR<T> {
    /**
     * 是否成功
     */
    boolean success = true;
    /**
     * 消息
     */
    String msg = "";

    /**
     * 数据
     */
    T data;

    /**
     * 自定义执行结果代号；默认：200=成功，-200：操作失败，其他自定义
     */
    long code = 0;

    long count = 0;

    Map<String, Object> totalRow = new HashMap<>(8);

    public boolean isSuccess() {
        return success;
    }

    public LayuiR<T> setSuccess(boolean success) {
        this.success = success;
        this.code = success ? 0 : -200;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public LayuiR<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public LayuiR<T> setData(T data) {
        this.data = data;
        return this;
    }

    public long getCode() {
        return code;
    }

    public long getCount() {
        return count;
    }

    public LayuiR<T> setCount(long count) {
        this.count = count;
        return this;
    }

    public LayuiR<T> setCode(long code) {
        this.code = code;
        return this;
    }

    public Map<String, Object> getTotalRow() {
        return totalRow;
    }

    public LayuiR<T> setTotalRow(Map<String, Object> totalRow) {
        this.totalRow = totalRow;
        return this;
    }

    public LayuiR<T> putTotalRow(Map<String, Object> payload) {
        this.totalRow.putAll(payload);
        return this;
    }

    public LayuiR<T> addTotalRow(String key, Object value) {
        this.totalRow.put(key, value);
        return this;
    }
}
