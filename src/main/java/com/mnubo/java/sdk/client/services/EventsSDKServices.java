package com.mnubo.java.sdk.client.services;

import com.mnubo.java.sdk.client.mapper.ObjectMapperConfig;
import com.mnubo.java.sdk.client.mapper.UUIDExistsResultDeserializer;
import com.mnubo.java.sdk.client.models.Event;
import com.mnubo.java.sdk.client.models.result.Result;
import com.mnubo.java.sdk.client.spi.EventsSDK;

import java.io.IOException;
import java.util.*;

import static com.mnubo.java.sdk.client.Constants.OBJECT_PATH;
import static com.mnubo.java.sdk.client.utils.ValidationUtils.*;
import static java.util.Arrays.asList;

class EventsSDKServices implements EventsSDK {
    private static final String EVENT_PATH = "/events";
    private static final String EVENT_PATH_SEGMENT = "events";
    private static final String EVENT_PATH_EXITS = EVENT_PATH + "/exists";
    private static final String REPORT_RESULTS_QUERY_PARAM = "report_results";
    private final SDKService sdkCommonServices;

    EventsSDKServices(SDKService sdkCommonServices) {
        this.sdkCommonServices = sdkCommonServices;
    }

    @Override
    public List<Result> send(String deviceId, List<Event> events) {

        notBlank(deviceId, "x_device_Id cannot be blank.");
        notNullNorEmpty(events, "Event list cannot be null or empty.");

        final String url = sdkCommonServices.getIngestionBaseUri()
                                            .path(OBJECT_PATH)
                                            .pathSegment(deviceId, EVENT_PATH_SEGMENT)
                                            .queryParam(REPORT_RESULTS_QUERY_PARAM, true)
                                            .build().toString();

        return postRequest(url, events);
    }



    @Override
    public List<Result> send(List<Event> events) {

        notNullNorEmpty(events, "Event list cannot be null or empty.");

        final String url = sdkCommonServices.getIngestionBaseUri()
                                            .path(EVENT_PATH)
                                            .queryParam(REPORT_RESULTS_QUERY_PARAM, true)
                                            .build().toString();

        return postRequest(url, events);
    }

    @Override
    public List<Result> send(Event... events) {

        return send(asList(events));
    }

    @Override
    public Map<UUID, Boolean> eventsExist(List<UUID> eventIds) {
        validNotNull(eventIds, "List of the eventIds cannot be null.");

        final String url = sdkCommonServices.getIngestionBaseUri()
                .path(EVENT_PATH_EXITS)
                .build().toString();

        String unparsed = sdkCommonServices.postRequest(url, String.class, eventIds);

        try {
            return ObjectMapperConfig.uuidExistsObjectMapper.readValue(unparsed, UUIDExistsResultDeserializer.targetClass);
        }
        catch(IOException ioe) {
            throw new RuntimeException("Cannot deserialize server's response", ioe);
        }
    }

    @Override
    public Boolean isEventExists(UUID eventId) {
        validNotNull(eventId, "eventId cannot be blank.");

        final Map<UUID, Boolean> subResults = eventsExist(Collections.singletonList(eventId));

        return subResults.get(eventId);
    }

    private List<Result> postRequest(String url, Object object) {
        Result[] results = sdkCommonServices.postRequest(url, Result[].class, object);
        return results == null ? new ArrayList<Result>() : Arrays.asList(results);
    }
}
