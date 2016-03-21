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
