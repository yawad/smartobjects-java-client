package com.mnubo.java.sdk.client.mapper;

import static com.mnubo.java.sdk.client.models.Owner.PASSWORD;
import static com.mnubo.java.sdk.client.models.Owner.REGISTRATION_DATE;
import static com.mnubo.java.sdk.client.models.Owner.USERNAME;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.mnubo.java.sdk.client.models.Owner;

public class OwnerSerializer extends StdSerializer<Owner> {

    public OwnerSerializer() { super(Owner.class); }

    @Override
    public void serialize(Owner value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        if (value.getRegistrationDate() != null) {
            jgen.writeStringField(REGISTRATION_DATE, value.getRegistrationDate().toString());
        }
        if (value.getPassword() != null) {
            jgen.writeStringField(PASSWORD, value.getPassword());
        }
        if (value.getUsername() != null) {
            jgen.writeObjectField(USERNAME, value.getUsername());
        }
        if (value.getAttributes() != null) {
            for (Map.Entry<String, Object> entry : value.getAttributes().entrySet()) {
                jgen.writeObjectField(entry.getKey(), entry.getValue());
            }
        }
        jgen.writeEndObject();
    }

}
