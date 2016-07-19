package com.mnubo.java.sdk.client.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.BooleanNode;

import java.io.IOException;
import java.util.LinkedHashMap;

public class ExistsResultDeserializer extends StdDeserializer<LinkedHashMap<String, Boolean>> {
    public static final Class<LinkedHashMap<String, Boolean>> targetClass = (Class<LinkedHashMap<String, Boolean>>)new LinkedHashMap<String, Boolean>().getClass();

    public ExistsResultDeserializer() {
        super(LinkedHashMap.class);
    }

    @Override
    public LinkedHashMap<String, Boolean> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        final LinkedHashMap<String, Boolean> resVal = new LinkedHashMap<>();
        final TreeNode array = p.getCodec().readTree(p);

        for(int i=0; i<array.size(); i++) {
            final TreeNode item = array.get(i);
            final String itemKey = item.fieldNames().next();
            final boolean exists = ((BooleanNode)item.get(itemKey)).asBoolean();
            resVal.put(itemKey, exists);
        }

        return resVal;
    }
}
