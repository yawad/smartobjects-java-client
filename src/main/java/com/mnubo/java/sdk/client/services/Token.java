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

import static com.mnubo.java.sdk.client.Constants.ACCESS_TOKEN;
import static com.mnubo.java.sdk.client.Constants.EXPIRE;
import static com.mnubo.java.sdk.client.Constants.JTI;
import static com.mnubo.java.sdk.client.Constants.SCOPE;
import static com.mnubo.java.sdk.client.Constants.TOKEN_TYPE;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

class Token {

    @JsonProperty(ACCESS_TOKEN)
    private String accessToken;

    @JsonProperty(TOKEN_TYPE)
    private String tokenType;

    @JsonProperty(EXPIRE)
    private int expiresIn;

    @JsonProperty(SCOPE)
    private String scope;

    @JsonProperty(JTI)
    private String jti;

    @JsonIgnore
    String getAccessToken() {
        return accessToken;
    }

    @JsonIgnore
    void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @JsonIgnore
    String getTokenType() {
        return tokenType;
    }

    @JsonIgnore
    void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @JsonIgnore
    int getExpiresIn() {
        return expiresIn;
    }

    @JsonIgnore
    void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    @JsonIgnore
    String getScope() {
        return scope;
    }

    @JsonIgnore
    void setScope(String scope) {
        this.scope = scope;
    }

    @JsonIgnore
    String getJti() {
        return jti;
    }

    @JsonIgnore
    void setJti(String jti) {
        this.jti = jti;
    }

}
