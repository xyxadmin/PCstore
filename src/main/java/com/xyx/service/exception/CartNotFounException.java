package com.xyx.service.exception;

public class CartNotFounException extends ServiceException{
    public CartNotFounException() {
        super();
    }

    public CartNotFounException(String message) {
        super(message);
    }

    public CartNotFounException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotFounException(Throwable cause) {
        super(cause);
    }

    protected CartNotFounException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
