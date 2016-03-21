package com.mnubo.java.sdk.client.mapper;

import com.mnubo.java.sdk.client.models.Event;
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
public class EventSerializerTest extends AbstractSerializerTest {
    @Test
    public void testSerialize() throws Exception {
        DateTime now = DateTime.now();

        Map<String, Object> timeseries = new HashMap<>();
        timeseries.put("string", "stringValue");
        timeseries.put("double", 10d);
        timeseries.put("float", 10f);
        timeseries.put("boolean", false);

        Event event = Event
                .builder()
                .withTimeseries(timeseries)
                .withEventID(UUID.fromString("9ab392d8-a865-48da-9035-0dc0a728b454"))
                .withSmartObject("device")
                .withTimestamp(now)
                .withEventType("type")
                .build();

        String json = mapper.writeValueAsString(event);
        assertThat(json, equalTo(format(
                "{\"x_timestamp\":\"%s\",\"x_object\":{\"x_device_id\":\"device\"},\"event_id\":\"9ab392d8-a865-48da-9035-0dc0a728b454\",\"x_event_type\":\"type\",\"boolean\":false,\"string\":\"stringValue\",\"float\":10.0,\"double\":10.0}",
                formatDate(event.getTimestamp()))));
    }

    @Test
    public void testSerializeTimeserieList() throws Exception {
        Map<String, Object> timeseries = new HashMap<>();
        timeseries.put("list", Arrays.asList("1", "2"));

        Event event = Event
                .builder()
                .withTimeseries(timeseries)
                .withEventType("type")
                .build();

        String json = mapper.writeValueAsString(event);
        assertThat(json, equalTo(format(
                "{\"x_timestamp\":\"%s\",\"x_event_type\":\"type\",\"list\":[\"1\",\"2\"]}",
                formatDate(event.getTimestamp()))));
    }

    @Test
    public void testSerializeWithSmartObject() throws Exception {
        Event event = Event
                .builder()
                .withSmartObject("deviceId")
                .withEventType("type")
                .build();

        String json = mapper.writeValueAsString(event);
        assertThat(json, equalTo(format(
                "{\"x_timestamp\":\"%s\",\"x_object\":{\"x_device_id\":\"deviceId\"},\"x_event_type\":\"type\"}",
                formatDate(event.getTimestamp()))));
    }

    @Test
    public void testSerializeWithoutEventType() throws Exception {

        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("X_Event_Type cannot be null or empty");
        Event.builder().build();
    }

    @Test
    public void testSerializeWithTimeStampNull() throws Exception {
        Event event = Event.builder().withEventType("type").withTimestamp(null).build();

        String json = mapper.writeValueAsString(event);
        assertThat(json, equalTo(format("{\"x_timestamp\":\"%s\",\"x_event_type\":\"type\"}", event.getTimestamp())));
    }
}
