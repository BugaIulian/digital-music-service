package com.dms.demo.exceptions.storage;

public class GoogleCloudConnectionException extends RuntimeException{

    public GoogleCloudConnectionException(String errorMessage) {
        super(errorMessage);
    }
}