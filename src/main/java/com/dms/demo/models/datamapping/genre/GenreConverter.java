package com.dms.demo.models.datamapping.genre;

import com.dms.demo.util.enums.MusicGenre;
import jakarta.persistence.AttributeConverter;

public class GenreConverter implements AttributeConverter<MusicGenre, String> {
    @Override
    public String convertToDatabaseColumn(MusicGenre musicGenre) {
        if (musicGenre == null) {
            return null;
        }
        return musicGenre.getDbValue();
    }

    @Override
    public MusicGenre convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return MusicGenre.fromDbValue(dbData);
    }
}