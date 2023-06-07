package com.dms.demo.models.datamapping.gender;

import com.dms.demo.util.enums.Gender;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class GenderDeserializer extends StdDeserializer<Gender> {

    public GenderDeserializer() {
        super(Gender.class);
    }

    @Override
    public Gender deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getText();
        return Gender.fromDbValue(value);
    }
}