package com.dms.demo.exceptions.artist;

public class ArtistNotFoundException extends RuntimeException {

    public ArtistNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}