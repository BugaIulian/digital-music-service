package com.dms.demo.exceptions.email;

public class SendgridRequestException extends RuntimeException {

    public SendgridRequestException(String errorMessage) {
        super(errorMessage);
    }
}