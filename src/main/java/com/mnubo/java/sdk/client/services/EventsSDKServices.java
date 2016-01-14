/*
 * ---------------------------------------------------------------------------
 *
 * COPYRIGHT (c) 2015 Mnubo Inc. All Rights Reserved.
 *
 * The copyright to the computer program(s) herein is the property of Mnubo Inc. The program(s) may be used and/or
 * copied only with the written permission from Mnubo Inc. or in accordance with the terms and conditions stipulated in
 * the agreement/contract under which the program(s) have been supplied.
 *
 * Author: marias Date : Jul 24, 2015
 *
 * ---------------------------------------------------------------------------
 */

package com.mnubo.java.sdk.client.services;

import static com.mnubo.java.sdk.client.Constants.OBJECT_PATH;

import java.util.ArrayList;
import java.util.List;

import com.mnubo.java.sdk.client.models.Event;
import com.mnubo.java.sdk.client.spi.EventsSDK;

class EventsSDKServices implements EventsSDK {

    private static final String EVENT_PATH = "/events";
    private final SDKService sdkCommonServices;

    EventsSDKServices(SDKService sdkCommonServices) {
        this.sdkCommonServices = sdkCommonServices;
    }

    @Override
    public void send(String deviceId, List<Event> events) {
        // url
        final String url = sdkCommonServices.getBaseUri().path(OBJECT_PATH).pathSegment(deviceId, EVENT_PATH).build().toString();

        // posting
        sdkCommonServices.postRequest(url, Event.class, events);
    }

    @Override
    public void send(List<Event> events) {
    	final String url = sdkCommonServices.getBaseUri().path(EVENT_PATH).build().toString();
    	sdkCommonServices.postRequest(url, Event.class, events);
    }
}
