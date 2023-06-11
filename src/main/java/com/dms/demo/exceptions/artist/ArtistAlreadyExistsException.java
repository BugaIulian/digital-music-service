package com.dms.demo.exceptions.artist;

public class ArtistAlreadyExistsException extends RuntimeException {

    public ArtistAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}