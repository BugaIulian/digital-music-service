package com.dms.demo.util.enums;

public enum MusicGenre {

    POP("Pop"),
    ROCK("Rock"),
    COUNTRY("Country"),
    RAP("Rap"),
    JAZZ("Jazz"),
    CLASSICAL("Classical"),
    BLUES("Blue"),
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
        throw new IllegalArgumentException("Unknown database value: " + dbValue);
    }
}