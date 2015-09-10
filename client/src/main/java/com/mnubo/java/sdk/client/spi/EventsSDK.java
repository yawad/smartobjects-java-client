package com.mnubo.java.sdk.client.spi;

import java.util.List;

import com.mnubo.java.sdk.client.models.Event;

public interface EventsSDK {
    void send(String ObjectId, List<Event> events);

    void send(List<Event> events);
}
