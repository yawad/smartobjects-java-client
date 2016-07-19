package com.mnubo.java.sdk.client.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.BooleanNode;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.UUID;

public class UUIDExistsResultDeserializer extends StdDeserializer<LinkedHashMap<UUID, Boolean>> {
    public static final Class<LinkedHashMap<UUID, Boolean>> targetClass = (Class<LinkedHashMap<UUID, Boolean>>)new LinkedHashMap<UUID, Boolean>().getClass();

    public UUIDExistsResultDeserializer() {
        super(LinkedHashMap.class);
    }

    @Override
    public LinkedHashMap<UUID, Boolean> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final LinkedHashMap<UUID, Boolean> resVal = new LinkedHashMap<>();
        final TreeNode array = p.getCodec().readTree(p);

        for(int i=0; i<array.size(); i++) {
            final TreeNode item = array.get(i);
            final String itemKey = item.fieldNames().next();
            final boolean exists = ((BooleanNode)item.get(itemKey)).asBoolean();
            resVal.put(UUID.fromString(itemKey), exists);
        }

        return resVal;
    }
}
