/*
 * ---------------------------------------------------------------------------
 *
 * COPYRIGHT (c) 2015 Mnubo Inc. All Rights Reserved.
 *
 * The copyright to the computer program(s) herein is the property of Mnubo Inc. The program(s) may be used and/or
 * copied only with the written permission from Mnubo Inc. or in accordance with the terms and conditions stipulated in
 * the agreement/contract under which the program(s) have been supplied.
 *
 * Author: marias Date : Aug 10, 2015
 *
 * ---------------------------------------------------------------------------
 */

package com.mnubo.java.sdk.client.models;

import static com.mnubo.java.sdk.client.utils.ValidationUtils.validIsBlank;
import static org.joda.time.DateTime.now;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;

public class Event {
    public static final String EVENT_ID = "event_id";
    public static final String OBJECT = "x_object";
    public static final String EVENT_TYPE = "x_event_type";
    public static final String TIMESTAMP = "x_timestamp";

    private UUID eventId;
    private SmartObject smartObject;
    private String eventType;
    private DateTime timestamp = now();
    private Map<String, Object> timeseries = new HashMap<String, Object>();

    Event(UUID eventId, SmartObject smartObject, String eventType, DateTime timestamp, Map<String, Object> timeseries) {
        validIsBlank(eventType, "X_Event_Type cannot be null or empty");
        this.eventId = eventId;
        this.smartObject = smartObject;
        this.eventType = eventType;
        this.timestamp = timestamp;
        this.timeseries = timeseries;
    }

    public static EventBuilder builder() {
        return new EventBuilder();
    }

    public static class EventBuilder {

        private UUID eventId;
        private SmartObject smartObject;
        private String eventType;
        private DateTime timestamp = now();
        private Map<String, Object> timeseries = new HashMap<String, Object>();

        EventBuilder() {

        }

        public EventBuilder withEventID(UUID eventId) {
            this.eventId = eventId;
            return this;
        }

        public EventBuilder withSmartObject(String deviceId) {
            this.smartObject = SmartObject.builder().withDeviceId(deviceId).withObjectType("send event").build();
            return this;
        }

        public EventBuilder withEventType(String eventType) {
            this.eventType = eventType;
            return this;
        }

        public EventBuilder withTimestamp(DateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public EventBuilder withTimeseries(Map<String, Object> timeseries) {
            this.timeseries = timeseries;
            return this;
        }

        public EventBuilder withAddedTimeseries(String key, Object value) {
            this.timeseries.put(key, value);
            return this;
        }

        public Event build() {
            return new Event(eventId, smartObject, eventType, timestamp, timeseries);
        }

    }

    public Map<String, Object> getTimeseries() {
        return timeseries;
    }

    public String getObjectId() {
        if (smartObject == null || smartObject.getObjectId() == null) {
            return null;
        }
        return smartObject.getObjectId().toString();
    }

    public UUID getEventId() {
        return eventId;
    }

    public SmartObject getObject() {
        return smartObject;
    }

    public String getEventType() {
        return eventType;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

}
