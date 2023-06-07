package com.dms.demo.models.datamapping.gender;

import com.dms.demo.util.enums.Gender;
import jakarta.persistence.AttributeConverter;

public class GenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.getDbValue();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Gender.fromDbValue(dbData);
    }
}