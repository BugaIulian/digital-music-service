package com.dms.demo.models.datamapping.gender;

import com.dms.demo.util.enums.Gender;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class GenderSerializer extends StdSerializer<Gender> {

    public GenderSerializer() {
        super(Gender.class);
    }

    @Override
    public void serialize(Gender gender, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(gender.getDbValue());
    }
}