package com.mnubo.java.sdk.client.mapper;

import static com.mnubo.java.sdk.client.models.Event.EVENT_ID;
import static com.mnubo.java.sdk.client.models.Event.EVENT_TYPE;
import static com.mnubo.java.sdk.client.models.Event.OBJECT;
import static com.mnubo.java.sdk.client.models.Event.TIMESTAMP;
import static com.mnubo.java.sdk.client.models.SmartObject.DEVICE_ID;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.mnubo.java.sdk.client.models.Event;

public class EventSerializer extends StdSerializer<Event> {

    public EventSerializer() { super(Event.class); }

    @Override
    public void serialize(Event value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        if (value.getTimestamp() != null) {
            jgen.writeStringField(TIMESTAMP, value.getTimestamp().toString());
        }
        if (value.getObject() != null && isNotBlank(value.getDeviceId())) {
            jgen.writeObjectFieldStart(OBJECT);
            jgen.writeStringField(DEVICE_ID, value.getDeviceId());
            jgen.writeEndObject();
        }
        if (value.getEventId() != null) {
            jgen.writeObjectField(EVENT_ID, value.getEventId());
        }
        if (value.getEventType() != null) {
            jgen.writeStringField(EVENT_TYPE, value.getEventType());
        }
        if (value.getTimeseries() != null) {
            for (Map.Entry<String, Object> entry : value.getTimeseries().entrySet()) {
                jgen.writeObjectField(entry.getKey(), entry.getValue());
            }
        }
        jgen.writeEndObject();
    }

}
