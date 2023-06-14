package com.dms.demo.exceptions.token;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException(String errorMessage) {
        super(errorMessage);
    }
}