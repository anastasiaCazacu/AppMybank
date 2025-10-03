package com.mybank.exception;

public class ResourceNotFoundException extends Exception {
    private String message;
    public ResourceNotFoundException(String userNotFound) {
        message = userNotFound;
    }
}
