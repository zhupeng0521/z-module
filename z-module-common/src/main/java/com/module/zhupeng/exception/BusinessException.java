package com.module.zhupeng.exception;

/**
 * 业务服务 BusinessException
 * <p>date : 2019-11-16</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class BusinessException extends RuntimeException {

    /**
     * 异常代号
     */
    private int code = 0;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message,
                                Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
