package com.dms.demo.exceptions.user;

public class UserPasswordException extends RuntimeException {

    public UserPasswordException(String errorMessage) {
        super(errorMessage);
    }
}