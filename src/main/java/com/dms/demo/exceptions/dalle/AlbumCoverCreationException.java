package com.dms.demo.exceptions.dalle;

public class AlbumCoverCreationException extends RuntimeException {

    public AlbumCoverCreationException(String errorMessage) {
        super(errorMessage);
    }
}