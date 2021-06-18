package com.ros.accounting.cashup.exceptions;

public class cashUpNotFoundException extends Exception {

    public cashUpNotFoundException() {
        super();
    }

    public cashUpNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public cashUpNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public cashUpNotFoundException(String message) {
        super(message);
    }

    public cashUpNotFoundException(Throwable cause) {
        super(cause);
    }

}
