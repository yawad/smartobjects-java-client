package com.mnubo.java.sdk.client.mapper;

import com.mnubo.java.sdk.client.models.result.Result;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.mnubo.java.sdk.client.mapper.ObjectMapperConfig.genericObjectMapper;

public class ResultDeserializeTest extends AbstractSerializerTest  {

    @Test
    public void testDeserialize() throws Exception {
        String json = "{\"id\":\"MyId\",\"Result\":\"success\",\"Message\":\"MyMessage\",\"unkonwn\":\"field\"}";

        Result result = genericObjectMapper.readValue(json, Result.class);
    }

    @Test
    public void testListDeserialize() throws Exception {
        String json = "[{\"id\":\"MyId\",\"Result\":\"success\",\"unkonwn\":\"field\"}," +
                "{\"id\":\"MyId\",\"Result\":\"success\",\"Message\":\"MyMessage\",\"unkonwn\":\"field\"}]";

        List<Result> result = Arrays.asList(genericObjectMapper.readValue(json, Result[].class));
    }
}
