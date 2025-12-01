package com.mybank.exception;

public class CreditValidationException extends RuntimeException {
    public CreditValidationException(String message) {
        super(message);
    }
}

