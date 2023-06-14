package com.dms.demo.models.datamapping.genre;

import com.dms.demo.util.enums.MusicGenre;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;

public class GenreDeserializer extends StdDeserializer<MusicGenre> {
    public GenreDeserializer() {
        super(MusicGenre.class);
    }

    @Override
    public MusicGenre deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getText();
        return MusicGenre.fromDbValue(value);
    }
}