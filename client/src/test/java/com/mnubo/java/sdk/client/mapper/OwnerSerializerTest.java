package com.mnubo.java.sdk.client.mapper;

import com.mnubo.java.sdk.client.models.Owner;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.lang.String.format;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by mauro on 08/03/16.
 */
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
                .withEventId(UUID.fromString("9ab392d8-a865-48da-9035-0dc0a728b454"))
                .withUsername("username")
                .withAttributes(attributes)
                .withPassword("password")
                .withRegistrationDate(now)
                .build();

        String json = mapper.writeValueAsString(owner);
        assertThat(json, equalTo(format(
                "{\"x_registration_date\":\"%s\",\"x_password\":\"password\",\"username\":\"username\",\"event_id\":\"9ab392d8-a865-48da-9035-0dc0a728b454\",\"boolean\":false,\"string\":\"stringValue\",\"float\":10.0,\"double\":10.0}",
                formatDate(now))));
    }

    @Test
    public void testSerializeAttributeList() throws Exception {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("list", Arrays.asList("1", "2"));

        Owner event = Owner
                .builder()
                .withAttributes(attributes)
                .build();

        String json = mapper.writeValueAsString(event);
        assertThat(json, equalTo("{\"list\":[\"1\",\"2\"]}"));
    }

    @Test
    public void testSerializeWithUsernameLowerCase() throws Exception {
        Owner owner = Owner
                .builder()
                .withUsername("USERname")
                .withPassword("password")
                .build();

        String json = mapper.writeValueAsString(owner);
        assertThat(json,equalTo(
                "{\"x_password\":\"password\",\"username\":\"username\"}"));
    }

    @Test
    public void testSerializeWithRegistrationDateNull() throws Exception {

        Owner owner = Owner
                .builder()
                .withRegistrationDate(null)
                .build();

        String json = mapper.writeValueAsString(owner);
        assertThat(json,equalTo("{}"));
    }
}
