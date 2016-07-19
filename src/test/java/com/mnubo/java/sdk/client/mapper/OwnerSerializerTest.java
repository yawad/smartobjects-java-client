package com.mnubo.java.sdk.client.mapper;

import com.mnubo.java.sdk.client.models.Owner;
import org.joda.time.DateTime;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.mnubo.java.sdk.client.mapper.ObjectMapperConfig.genericObjectMapper;
import static java.lang.String.format;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class OwnerSerializerTest extends AbstractSerializerTest {
    @Test
    public void testSerialize() throws Exception {

        DateTime now = DateTime.now();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("string", "stringValue");
        attributes.put("double", 10d);
        attributes.put("float", 10f);
        attributes.put("boolean", false);

        Owner owner = Owner
                .builder()
                .withUsername("username")
                .withAttributes(attributes)
                .withPassword("password")
                .withRegistrationDate(now)
                .build();

        String json = genericObjectMapper.writeValueAsString(owner);
        JSONAssert.assertEquals(format(
                "{\"x_registration_date\":\"%s\",\"x_password\":\"password\",\"username\":\"username\",\"boolean\":false,\"string\":\"stringValue\",\"float\":10.0,\"double\":10.0}",
                formatDate(now)), json, true);
    }

    @Test
    public void testSerializeAttributeList() throws Exception {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("list", Arrays.asList("1", "2"));

        Owner event = Owner
                .builder()
                .withAttributes(attributes)
                .build();

        String json = genericObjectMapper.writeValueAsString(event);
        JSONAssert.assertEquals("{\"list\":[\"1\",\"2\"]}", json, true);
    }

    @Test
    public void testSerializeWithUsernameLowerCase() throws Exception {
        Owner owner = Owner
                .builder()
                .withUsername("USERname")
                .withPassword("password")
                .build();

        String json = genericObjectMapper.writeValueAsString(owner);
        JSONAssert.assertEquals(
                "{\"x_password\":\"password\",\"username\":\"username\"}", json, true);
    }

    @Test
    public void testSerializeWithRegistrationDateNull() throws Exception {

        Owner owner = Owner
                .builder()
                .withRegistrationDate(null)
                .build();

        String json = genericObjectMapper.writeValueAsString(owner);
        assertThat(json,equalTo("{}"));
    }
}
