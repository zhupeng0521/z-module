package com.module.zhupeng.exception;

import javax.security.sasl.AuthenticationException;
/**
 * ModuleAuthenticationException
 * <p>date : 2019-11-28</p>
 *
 * @author DONGSKAuthenticationException
 * @since Ver1.0.0.1
 **/
public class ModuleAuthenticationException extends AuthenticationException {
    private static final long serialVersionUID = 6022575393500654458L;

    public ModuleAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public ModuleAuthenticationException(String msg) {
        super(msg);
    }
}
