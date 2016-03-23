package com.mnubo.java.sdk.client.mapper;

import static com.mnubo.java.sdk.client.mapper.MapperUtils.*;
import static com.mnubo.java.sdk.client.models.Event.EVENT_ID;
import static com.mnubo.java.sdk.client.models.Event.EVENT_TYPE;
import static com.mnubo.java.sdk.client.models.Event.OBJECT;
import static com.mnubo.java.sdk.client.models.Event.TIMESTAMP;
import static com.mnubo.java.sdk.client.models.SmartObject.DEVICE_ID;
import static java.lang.String.format;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.mnubo.java.sdk.client.models.Event;

public class EventDeserializer extends StdDeserializer<Event> {

    public EventDeserializer() { super(Event.class); }

    @Override
    public Event deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {

        Event.EventBuilder builder = Event.builder();
        Map<String, Object> items = MapperUtils.readValuesAsMap(jsonParser, String.class, Object.class);

        for (Map.Entry<String, Object> entry : items.entrySet()) {

            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();

            if (fieldName.equals(EVENT_ID)) {

                throwIfNotProperType(EVENT_ID, fieldValue, UUID_TYPE);
                builder.withEventID(convertToUUID(fieldValue));
            } else if (fieldName.equals(EVENT_TYPE)) {

                throwIfNotProperType(EVENT_TYPE, fieldValue, TEXT_TYPE);
                builder.withEventType(convertToString(fieldValue));
            } else if (fieldName.equals(OBJECT)) {

                throwIfNotProperType(OBJECT, fieldValue, OBJECT_INSTANCE_TYPE);
                if (fieldValue instanceof Map) {

                    Map objectMap = (Map) fieldValue;
                    if (objectMap.containsKey(DEVICE_ID)) {

                        Object deviceId = objectMap.get(DEVICE_ID);

                        throwIfNotProperType(format("%s.%s", OBJECT, DEVICE_ID), deviceId, TEXT_TYPE);
                        builder.withSmartObject(convertToString(deviceId));
                    }
                }
            } else if (fieldName.equals(TIMESTAMP)) {

                throwIfNotProperType(TIMESTAMP, fieldValue, DATETIME_TYPE);
                builder.withTimestamp(convertToDatetime(fieldValue));
            } else {

                if (fieldName.toLowerCase().equals(OBJECT) || fieldName.toLowerCase().equals(EVENT_TYPE) ||
                        fieldName.toLowerCase().equals(TIMESTAMP) || fieldName.toLowerCase().equals(EVENT_ID)) {
                    throw new IllegalArgumentException(format("Reserved field %s must be lowercase.",
                            fieldName.toLowerCase()));
                }
                builder.withAddedTimeseries(fieldName, fieldValue);
            }
        }

        return builder.build();
    }
}
