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

import static com.mnubo.java.sdk.client.Constants.PRINT_OBJECT_NULL;
import static com.mnubo.java.sdk.client.models.Owner.USERNAME;
import static com.mnubo.java.sdk.client.utils.ValidationUtils.validIsBlank;
import static org.joda.time.DateTime.now;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.joda.time.DateTime;

/**
 * SmartObject Bean. To build a SmartObject you must ask for SmartObjectBuilder using: the
 * static method SmartObject.builder()
 *
 * @author Mauro Arias
 * @since 2015/08/10
 */
public final class SmartObject {

    /**
     * {@value #DEVICE_ID} Constant key used during the deserialization and serialization
     * of json files.
     */
    public static final String DEVICE_ID = "x_device_id";

    /**
     * {@value #OBJECT_TYPE} Constant key used during the deserialization and
     * serialization of json files.
     */
    public static final String OBJECT_TYPE = "x_object_type";

    /**
     * {@value #REGISTRATION_DATE} Constant key used during the deserialization and
     * serialization of json files.
     */
    public static final String REGISTRATION_DATE = "x_registration_date";

    /**
     * {@value #OWNER} Constant key used during the deserialization and serialization of
     * json files.
     */
    public static final String OWNER = "x_owner";

    private final String deviceId;
    private final UUID objectId = null;
    private final String objectType;
    private final DateTime registrationDate;
    private final Owner owner;
    private final Map<String, Object> attributes;

    SmartObject(String deviceId, String objectType, DateTime registrationDate, Owner owner,
            Map<String, Object> attributes) {
        validIsBlank(objectType, "X_Object_Type cannot be null or empty");
        validIsBlank(deviceId, "X_Device_Id cannot be null or empty");
        this.deviceId = deviceId;
        this.objectType = objectType;
        if (registrationDate != null) {
            this.registrationDate = registrationDate;
        }
        else {
            this.registrationDate = now();
        }
        this.owner = owner;
        if (attributes != null) {
            this.attributes = attributes;
        }
        else {
            this.attributes = new HashMap<String, Object>();
        }

    }

    /**
     * Builder, returns a SmartObjectBuilder to build immutable smartObject instances.
     *
     * @return SmartObject Builder.
     */
    public static SmartObjectBuilder builder() {
        return new SmartObjectBuilder();
    }

    /**
     * SmartObjectBuilder nested class, this static Class builds immutable SmartObjects.
     * this needs to be requested with "SmartObject.builder() method".
     *
     */
    public static class SmartObjectBuilder {

        private String deviceId;
        private String objectType;
        private DateTime registrationDate = now();
        private Owner owner;
        private Map<String, Object> attributes = new HashMap<String, Object>();

        SmartObjectBuilder() {

        }

        /**
         * Add a deviceId to the SmartObject. Note that this parameter is mandatory.
         *
         * @param deviceId: device ID of the object.
         * @return SmartObjectBuilder: current Smart Object builder.
         * @throws IllegalStateException a run exception will be launched if this field is
         * not present
         *
         */
        public SmartObjectBuilder withDeviceId(String deviceId) throws IllegalStateException {
            this.deviceId = deviceId;
            return this;
        }

        /**
         * Add an object_type to the SmartObject. This parameter is mandatory.
         *
         * @param objectType: object type
         * @return SmartObjectBuilder: current Smart Object builder.
         * @throws IllegalStateException a run exception will be launched if this field is
         * not present
         *
         */
        public SmartObjectBuilder withObjectType(String objectType) throws IllegalStateException {
            this.objectType = objectType;
            return this;
        }

        /**
         * Add a registration date to the SmartObject. if this parameter is not set the
         * current time is set by default.
         *
         * @param registrationDate: DateTime of registration.
         * @return SmartObjectBuilder: current Smart Object builder.
         *
         */
        public SmartObjectBuilder withRegistrationDate(DateTime registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        /**
         * Add an owner user name to the SmartObject.
         *
         * @param ownerUsername: user name of owner.
         * @return SmartObjectBuilder: current Smart Object builder.
         *
         */
        public SmartObjectBuilder withOwner(String ownerUsername) {
            this.owner = Owner.builder().withUsername(ownerUsername).build();
            return this;
        }

        /**
         * Add a list of attributes to the SmartObject.
         *
         * @param attributes: Map of attributes.
         * @return SmartObjectBuilder: current Smart Object builder.
         *
         */
        public SmartObjectBuilder withAttributes(Map<String, Object> attributes) {
            this.attributes = attributes;
            return this;
        }

        /**
         * Add an attribute to the SmartObject.
         *
         * @param key: attribute key or name.
         * @param value: attribute value.
         * @return SmartObjectBuilder: current Smart Object builder.
         *
         */
        public SmartObjectBuilder withAddedAttribute(String key, Object value) {
            this.attributes.put(key, value);
            return this;
        }

        /**
         * Build the immutable SmartObject with parameters set. Note that Device_Id and
         * Object_type parameters are mandatory.
         *
         * @return SmartObject: immutable Smart Object instance built.
         */
        public SmartObject build() {
            return new SmartObject(deviceId, objectType, registrationDate, owner, attributes);
        }

    }

    /**
     * returns a copy of smartobject's attribute. it returns an empty map if there are not
     * attributes.
     *
     * @return attributes.
     *
     */
    public Map<String, Object> getAttributes() {
        return new HashMap<String, Object>(attributes);
    }

    /**
     * returns the device ID of the SmartObject.
     *
     * @return device Id.
     *
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * returns ObjectId. Note that this could be null.
     *
     * @return UUID object ID.
     *
     */
    public UUID getObjectId() {
        return objectId;
    }

    /**
     * returns the object_type.
     *
     * @return object_type.
     *
     */
    public String getObjectType() {
        return objectType;
    }

    /**
     * returns the registration date.
     *
     * @return registrationDate.
     *
     */
    public DateTime getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Returns the "username" associated to the SmartObject. Note that it is NULL if there
     * is not Owner associated to the SmartObject.
     *
     * @return "username" or "null".
     */
    public String getOwnerUserName() {
        if (owner == null) {
            return null;
        }
        return owner.getUsername();
    }

    /**
     * Returns the Owner instance associated to the SmartObject. Note that it is NULL if
     * there is not Owner associated to the SmartObject.
     *
     * @return "username" or "null".
     */
    public Owner getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        StringBuilder toPrint = new StringBuilder();
        toPrint.append("{\n");
        toPrint.append(line2String(DEVICE_ID, deviceId));
        toPrint.append(line2String("OBJECT_ID", objectId));
        toPrint.append(line2String(OBJECT_TYPE, objectType));
        toPrint.append(line2String(REGISTRATION_DATE, registrationDate));
        toPrint.append(line2String(OWNER + "." + USERNAME, owner != null ? owner.getUsername() : null));
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            toPrint.append(line2String(entry.getKey(), entry.getValue()));
        }
        toPrint.append("}\n");
        return toPrint.toString();

    }

    private String line2String(String name, Object value) {
        StringBuilder build = new StringBuilder();
        if (name != null) {
            build.append("     " + name + " : ");
            if (value != null) {
                build.append(value);
            }
            else {
                build.append(PRINT_OBJECT_NULL);
            }
            build.append("\n");
        }
        return build.toString();
    }

}
