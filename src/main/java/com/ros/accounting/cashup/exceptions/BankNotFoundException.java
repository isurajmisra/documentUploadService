package com.ros.accounting.cashup.exceptions;

public class BankNotFoundException extends Exception {


    private static final long serialVersionUID = 1L;

    public BankNotFoundException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BankNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

    public BankNotFoundException(String message, Throwable cause) {
        super(message, cause);

    }

    public BankNotFoundException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public BankNotFoundException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }


}
