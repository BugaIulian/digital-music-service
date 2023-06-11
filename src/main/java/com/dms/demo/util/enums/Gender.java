package com.dms.demo.util.enums;

import com.dms.demo.exceptions.gender.IllegalGenderException;
import com.dms.demo.models.datamapping.gender.GenderDeserializer;
import com.dms.demo.models.datamapping.gender.GenderSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = GenderDeserializer.class)
@JsonSerialize(using = GenderSerializer.class)
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
            if (gender.getDbValue().equalsIgnoreCase(dbValue)) {
                return gender;
            }
        }
        throw new IllegalGenderException("Invalid gender. Allowed values are: Male and Female");
    }
}