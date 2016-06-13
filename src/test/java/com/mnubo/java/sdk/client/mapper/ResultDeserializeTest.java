package com.mnubo.java.sdk.client.mapper;

import com.mnubo.java.sdk.client.models.result.Result;
import org.junit.Test;

public class ResultDeserializeTest extends AbstractSerializerTest  {

    @Test
    public void testDeserialize() throws Exception {
        String json = "{\"id\":\"MyId\",\"Result\":\"success\",\"Message\":\"MyMessage\",\"unkonwn\":\"field\"}";

        Result result = mapper.readValue(json, Result.class);
    }
}
