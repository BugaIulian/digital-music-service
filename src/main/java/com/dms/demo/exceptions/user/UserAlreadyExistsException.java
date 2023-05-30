package com.dms.demo.exceptions.user;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}