package com.mnubo.java.sdk.client.mapper;

import static com.mnubo.java.sdk.client.mapper.MapperUtils.*;
import static com.mnubo.java.sdk.client.models.Owner.USERNAME;
import static com.mnubo.java.sdk.client.models.SmartObject.DEVICE_ID;
import static com.mnubo.java.sdk.client.models.SmartObject.EVENT_ID;
import static com.mnubo.java.sdk.client.models.SmartObject.OBJECT_TYPE;
import static com.mnubo.java.sdk.client.models.SmartObject.OWNER;
import static com.mnubo.java.sdk.client.models.SmartObject.REGISTRATION_DATE;
import static java.lang.String.format;

import java.io.IOException;
import java.util.Map;

import com.mnubo.java.sdk.client.models.Owner;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.mnubo.java.sdk.client.models.SmartObject;

public class SmartObjectDeserializer extends StdDeserializer<SmartObject> {

    public SmartObjectDeserializer() { super(SmartObject.class); }

    @Override
    public SmartObject deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        SmartObject.SmartObjectBuilder builder = SmartObject.builder();
        Map<String, Object> items = MapperUtils.readValuesAsMap(jsonParser, String.class, Object.class);

        for (Map.Entry<String, Object> entry : items.entrySet()) {

            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();

            if (entry.getKey().equals(DEVICE_ID)) {

                throwIfNotProperType(DEVICE_ID, fieldValue, TEXT_TYPE);
                builder.withDeviceId(entry.getValue().toString());
            }
            else if (entry.getKey().equals(OBJECT_TYPE)) {

                throwIfNotProperType(OBJECT_TYPE, fieldValue, TEXT_TYPE);
                builder.withObjectType(entry.getValue().toString());
            }
            else if (entry.getKey().equals(OWNER)) {

                throwIfNotProperType(OWNER, fieldValue, OBJECT_INSTANCE_TYPE);
                if (fieldValue instanceof Map) {

                    Map objectMap = (Map) fieldValue;
                    if (objectMap.containsKey(USERNAME)) {

                        Object username = objectMap.get(USERNAME);

                        throwIfNotProperType(format("%s.%s", OWNER, USERNAME), username, TEXT_TYPE);
                        builder.withOwner(convertToString(username));
                    }
                }
            }
            else if (entry.getKey().equals(REGISTRATION_DATE)) {

                throwIfNotProperType(Owner.REGISTRATION_DATE, fieldValue, DATETIME_TYPE);
                builder.withRegistrationDate(MapperUtils.convertToDatetime(fieldValue.toString()));
            }
            else if (entry.getKey().equals(EVENT_ID)) {

                throwIfNotProperType(Owner.EVENT_ID, fieldValue, UUID_TYPE);
                builder.withEventId(convertToUUID(fieldValue.toString()));
            }
            else {

                if (fieldName.toLowerCase().equals(DEVICE_ID) || fieldName.toLowerCase().equals(OBJECT_TYPE) ||
                        fieldName.toLowerCase().equals(OWNER) || fieldName.toLowerCase().equals(REGISTRATION_DATE) ||
                        fieldName.toLowerCase().equals(EVENT_ID)) {
                    throw new IllegalArgumentException(format("Reserved field %s must be lowercase.",
                            fieldName.toLowerCase()));
                }
                builder.withAddedAttribute(fieldName, fieldValue);
            }
        }

        return builder.build();
    }

}
