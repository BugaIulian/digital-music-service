package com.dms.demo.util.enums;

public enum Gender {

    MALE("Male"),
    FEMALE("Female");

    private final String dbValue;

    Gender(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static Gender fromDbValue(String dbValue) {
        for (Gender gender : Gender.values()) {
            if (gender.getDbValue().equals(dbValue)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbValue);
    }
}