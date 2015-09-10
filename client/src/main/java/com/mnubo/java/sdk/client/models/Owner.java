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

import org.joda.time.DateTime;

public class Owner {

    public static final String REGISTRATION_DATE = "x_registration_date";
    public static final String PASSWORD = "x_password";
    public static final String USERNAME = "username";

    private String username;
    private String password;
    private DateTime registrationDate = now();
    private Map<String, Object> attributes = new HashMap<String, Object>();

    Owner(String username, String password, DateTime registrationDate, Map<String, Object> attributes) {
        validIsBlank(username, "usermame cannot be null or empty");
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
        this.attributes = attributes;
    }

    public static OwnerBuilder builder() {
        return new OwnerBuilder();
    }

    public static class OwnerBuilder {

        private String username;
        private String password;
        private DateTime registrationDate = now();
        private Map<String, Object> attributes = new HashMap<String, Object>();

        OwnerBuilder() {

        }

        public OwnerBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public OwnerBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public OwnerBuilder withRegistrationDate(DateTime registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public OwnerBuilder withAttributes(Map<String, Object> attributes) {
            this.attributes = attributes;
            return this;
        }

        public OwnerBuilder withAddedAttribute(String key, Object value) {
            this.attributes.put(key, value);
            return this;
        }

        public Owner build() {
            return new Owner(username, password, registrationDate, attributes);
        }

    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public DateTime getRegistrationDate() {
        return registrationDate;
    }

}
