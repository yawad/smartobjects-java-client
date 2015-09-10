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

public class SmartObject {
    public static final String DEVICE_ID = "x_device_id";
    public static final String OBJECT_TYPE = "x_object_type";
    public static final String REGISTRATION_DATE = "x_registration_date";
    public static final String OWNER = "x_owner";

    private String deviceId;
    private UUID objectId;
    private String objectType;

    // @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZZ")
    private DateTime registrationDate = now();
    private Owner owner;
    private Map<String, Object> attributes = new HashMap<String, Object>();

    SmartObject(String deviceId, String objectType, DateTime registrationDate, Owner owner,
            Map<String, Object> attributes) {
        validIsBlank(objectType, "X_Object_Type cannot be null or empty");
        validIsBlank(deviceId, "X_Device_Id cannot be null or empty");
        this.deviceId = deviceId;
        this.objectType = objectType;
        this.registrationDate = registrationDate;
        this.owner = owner;
        this.attributes = attributes;
    }

    public static SmartObjectBuilder builder() {
        return new SmartObjectBuilder();
    }

    public static class SmartObjectBuilder {

        private String deviceId;
        private String objectType;
        private DateTime registrationDate = now();
        private Owner owner;
        private Map<String, Object> attributes = new HashMap<String, Object>();

        SmartObjectBuilder() {

        }

        public SmartObjectBuilder withDeviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public SmartObjectBuilder withObjectType(String objectType) {
            this.objectType = objectType;
            return this;
        }

        public SmartObjectBuilder withRegistrationDate(DateTime registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public SmartObjectBuilder withOwner(String ownerUsername) {
            this.owner = Owner.builder().withUsername(ownerUsername).build();
            return this;
        }

        public SmartObjectBuilder withAttributes(Map<String, Object> attributes) {
            this.attributes = attributes;
            return this;
        }

        public SmartObjectBuilder withAddedAttribute(String key, Object value) {
            this.attributes.put(key, value);
            return this;
        }

        public SmartObject build() {
            return new SmartObject(deviceId, objectType, registrationDate, owner, attributes);
        }

    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public UUID getObjectId() {
        return objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public DateTime getRegistrationDate() {
        return registrationDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getOwnerUserName() {
        if (owner == null) {
            return null;
        }
        return owner.getUsername();
    }

}
