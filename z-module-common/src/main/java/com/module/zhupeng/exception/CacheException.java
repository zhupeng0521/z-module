package com.module.zhupeng.exception;

/**
 * 缓存  CacheException
 * <p>date : 2019-11-16</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class CacheException extends RuntimeException {

    public CacheException() {
        super();
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }

    protected CacheException(String message,
                             Throwable cause,
                             boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
