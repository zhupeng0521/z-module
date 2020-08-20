package com.module.zhupeng.exception;

/**
 * 仓储 RepositoryException
 * <p>date : 2019-11-16</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class RepositoryException extends RuntimeException {

    public RepositoryException() {
        super();
    }

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }

    protected RepositoryException(String message,
                                  Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
