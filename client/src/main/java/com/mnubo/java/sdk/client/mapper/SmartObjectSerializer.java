package com.mnubo.java.sdk.client.mapper;

import static com.mnubo.java.sdk.client.models.Owner.USERNAME;
import static com.mnubo.java.sdk.client.models.SmartObject.DEVICE_ID;
import static com.mnubo.java.sdk.client.models.SmartObject.EVENT_ID;
import static com.mnubo.java.sdk.client.models.SmartObject.OBJECT_TYPE;
import static com.mnubo.java.sdk.client.models.SmartObject.OWNER;
import static com.mnubo.java.sdk.client.models.SmartObject.REGISTRATION_DATE;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.mnubo.java.sdk.client.models.SmartObject;

public class SmartObjectSerializer extends StdSerializer<SmartObject> {

    public SmartObjectSerializer() { super(SmartObject.class); }

    @Override
    public void serialize(SmartObject value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        if (value.getRegistrationDate() != null) {
            jgen.writeStringField(REGISTRATION_DATE, value.getRegistrationDate().toString());
        }
        if (value.getDeviceId() != null) {
            jgen.writeStringField(DEVICE_ID, value.getDeviceId());
        }
        if (value.getObjectType() != null) {
            jgen.writeStringField(OBJECT_TYPE, value.getObjectType());
        }
        if (value.getOwner() != null && isNotBlank(value.getOwnerUserName())) {
            jgen.writeObjectFieldStart(OWNER);
            jgen.writeStringField(USERNAME, value.getOwnerUserName());
            jgen.writeEndObject();
        }
        if (value.getEventId() != null) {
            jgen.writeObjectField(EVENT_ID, value.getEventId());
        }
        if (value.getAttributes() != null) {
            for (Map.Entry<String, Object> entry : value.getAttributes().entrySet()) {
                jgen.writeObjectField(entry.getKey(), entry.getValue());
            }
        }
        jgen.writeEndObject();
    }

}
