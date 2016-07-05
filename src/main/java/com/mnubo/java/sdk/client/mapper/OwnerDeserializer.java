package com.mnubo.java.sdk.client.mapper;

import static com.mnubo.java.sdk.client.mapper.MapperUtils.*;
import static com.mnubo.java.sdk.client.models.Owner.PASSWORD;
import static com.mnubo.java.sdk.client.models.Owner.REGISTRATION_DATE;
import static com.mnubo.java.sdk.client.models.Owner.USERNAME;
import static java.lang.String.format;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.mnubo.java.sdk.client.models.Owner;

public class OwnerDeserializer extends StdDeserializer<Owner> {

    public OwnerDeserializer() { super(Owner.class); }

    @Override
    public Owner deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        Owner.OwnerBuilder builder = Owner.builder();
        Map<String, Object> items = MapperUtils.readValuesAsMap(jsonParser, String.class, Object.class);

        for (Map.Entry<String, Object> entry : items.entrySet()) {

            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();

            if (fieldName.equals(USERNAME)) {

                throwIfNotProperType(USERNAME, fieldValue, TEXT_TYPE);
                builder.withUsername(convertToString(fieldValue.toString()));
            }
            else if (fieldName.equals(PASSWORD)) {

                throwIfNotProperType(PASSWORD, fieldValue, TEXT_TYPE);
                builder.withPassword(convertToString(fieldValue.toString()));
            }
            else if (fieldName.equals(REGISTRATION_DATE)) {

                throwIfNotProperType(REGISTRATION_DATE, fieldValue, DATETIME_TYPE);
                builder.withRegistrationDate(MapperUtils.convertToDatetime(fieldValue.toString()));
            }
            else {

                if (fieldName.toLowerCase().equals(USERNAME) || fieldName.toLowerCase().equals(PASSWORD) ||
                        fieldName.toLowerCase().equals(REGISTRATION_DATE)) {
                    throw new IllegalArgumentException(format("Reserved field %s must be lowercase.",
                            fieldName.toLowerCase()));
                }
                builder.withAddedAttribute(fieldName, fieldValue);
            }
        }

        return builder.build();
    }

}
