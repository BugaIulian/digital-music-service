package com.dms.demo.exceptions.gender;

public class IllegalGenderException extends RuntimeException {

    public IllegalGenderException(String errorMessage) {
        super(errorMessage);
    }
}