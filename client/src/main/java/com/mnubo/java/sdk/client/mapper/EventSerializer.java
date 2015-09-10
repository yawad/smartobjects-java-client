/*
 * ---------------------------------------------------------------------------
 *
 * COPYRIGHT (c) 2015 Mnubo Inc. All Rights Reserved.
 *
 * The copyright to the computer program(s) herein is the property of
 * Mnubo Inc. The program(s) may be used and/or copied
 * only with the written permission from Mnubo Inc.
 * or in accordance with the terms and conditions stipulated in the
 * agreement/contract under which the program(s) have been supplied.
 *
 * Author: marias
 * Date  : Sep 9, 2015
 *
 * ---------------------------------------------------------------------------
 */
package com.mnubo.java.sdk.client.mapper;

import static com.mnubo.java.sdk.client.models.Event.EVENT_ID;
import static com.mnubo.java.sdk.client.models.Event.EVENT_TYPE;
import static com.mnubo.java.sdk.client.models.Event.OBJECT;
import static com.mnubo.java.sdk.client.models.Event.TIMESTAMP;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.mnubo.java.sdk.client.models.Event;

public class EventSerializer extends StdSerializer<Event> {
    public EventSerializer() {
        super(Event.class);
    }

    @Override
    public void serialize(Event value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
            JsonGenerationException {
        jgen.writeStartObject();
        if (value.getTimestamp() != null) {
            jgen.writeStringField(TIMESTAMP, value.getTimestamp().toString());
        }
        if (value.getObject() != null) {
            jgen.writeObjectField(OBJECT, value.getObject());
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
