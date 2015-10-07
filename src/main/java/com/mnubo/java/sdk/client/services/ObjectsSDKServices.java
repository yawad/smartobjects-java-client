/*
 * ---------------------------------------------------------------------------
 *
 * COPYRIGHT (c) 2015 Mnubo Inc. All Rights Reserved.
 *
 * The copyright to the computer program(s) herein is the property of Mnubo Inc. The program(s) may be used and/or
 * copied only with the written permission from Mnubo Inc. or in accordance with the terms and conditions stipulated in
 * the agreement/contract under which the program(s) have been supplied.
 *
 * Author: marias Date : Jul 22, 2015
 *
 * ---------------------------------------------------------------------------
 */

package com.mnubo.java.sdk.client.services;

import static com.mnubo.java.sdk.client.Constants.OBJECT_PATH;
import static com.mnubo.java.sdk.client.utils.ValidationUtils.validIsBlank;

import com.mnubo.java.sdk.client.models.SmartObject;
import com.mnubo.java.sdk.client.spi.ObjectsSDK;

class ObjectsSDKServices implements ObjectsSDK {

    private final SDKService sdkCommonServices;

    ObjectsSDKServices(SDKService sdkCommonServices) {
        this.sdkCommonServices = sdkCommonServices;
    }

    @Override
    public void create(SmartObject object) {
        // url
        final String url = sdkCommonServices.getBaseUri().path(OBJECT_PATH).build().toString();

        validIsBlank(object.getObjectType(), "X_Object_Type cannot be null or empty");
        validIsBlank(object.getDeviceId(), "X_Device_Id cannot be null or empty");

        // posting
        sdkCommonServices. postRequest(url, SmartObject.class, object);

    }

    @Override
    public void update(SmartObject object, String deviceId) {
        validIsBlank(deviceId, "X_Device_Id cannot be null or empty");

        // url
        final String url = sdkCommonServices.getBaseUri().path(OBJECT_PATH).pathSegment(deviceId).build().toString();

        // putting
        sdkCommonServices.putRequest(url, object);
    }

    @Override
    public void delete(String deviceId) {
        validIsBlank(deviceId, "X_Device_Id cannot be null or empty");

        // url
        final String url = sdkCommonServices.getBaseUri().path(OBJECT_PATH).pathSegment(deviceId).build().toString();

        // putting
        sdkCommonServices.deleteRequest(url);
    }


}
