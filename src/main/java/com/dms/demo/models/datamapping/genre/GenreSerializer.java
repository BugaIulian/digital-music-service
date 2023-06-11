package com.dms.demo.models.datamapping.genre;

import com.dms.demo.util.enums.MusicGenre;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class GenreSerializer extends StdSerializer<MusicGenre> {

    public GenreSerializer() {
        super(MusicGenre.class);
    }

    @Override
    public void serialize(MusicGenre musicGenre, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(musicGenre.getDbValue());
    }
}