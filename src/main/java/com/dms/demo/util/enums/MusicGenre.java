package com.dms.demo.util.enums;

import com.dms.demo.exceptions.musicgenre.IllegalMusicGenreException;
import com.dms.demo.models.datamapping.genre.GenreDeserializer;
import com.dms.demo.models.datamapping.genre.GenreSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = GenreDeserializer.class)
@JsonSerialize(using = GenreSerializer.class)
public enum MusicGenre {

    POP("Pop"),
    ROCK("Rock"),
    COUNTRY("Country"),
    RAP("Rap"),
    JAZZ("Jazz"),
    CLASSICAL("Classical"),
    BLUES("Blues"),
    ELECTRONIC("Electronic"),
    REGGAE("Reggae"),
    METAL("Metal");

    private final String dbValue;

    MusicGenre(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return this.dbValue;
    }

    public static MusicGenre fromDbValue(String dbValue) {
        for (MusicGenre musicGenre : MusicGenre.values()) {
            if (musicGenre.getDbValue().equalsIgnoreCase(dbValue)) {
                return musicGenre;
            }
        }
        throw new IllegalMusicGenreException("Invalid genre. Allowed values are: Pop, Rock, Country, Rap, Jazz, Classical, Blues, Electronic, Reggae, Metal");
    }
}