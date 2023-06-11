package com.dms.demo.exceptions.musicgenre;

public class IllegalMusicGenreException extends RuntimeException{

    public IllegalMusicGenreException(String errorMessage) {
        super(errorMessage);
    }
}