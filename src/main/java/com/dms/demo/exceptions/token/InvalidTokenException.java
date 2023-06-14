package com.dms.demo.exceptions.token;

public class InvalidTokenException extends RuntimeException {

    public InvalidTokenException(String errorMessage) {
        super(errorMessage);
    }
}