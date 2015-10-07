/*
 * ---------------------------------------------------------------------------
 *
 * COPYRIGHT (c) 2015 Mnubo Inc. All Rights Reserved.
 *
 * The copyright to the computer program(s) herein is the property of Mnubo Inc. The program(s) may be used and/or
 * copied only with the written permission from Mnubo Inc. or in accordance with the terms and conditions stipulated in
 * the agreement/contract under which the program(s) have been supplied.
 *
 * Author: marias Date : Aug 4, 2015
 *
 * ---------------------------------------------------------------------------
 */

package com.mnubo.java.sdk.client.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.mnubo.java.sdk.client.config.MnuboSDKConfig;

class SDKService {

    private RestTemplate template;
    private final CredentialHandler credential;
    private final MnuboSDKConfig config;

    SDKService(RestTemplate template, CredentialHandler credential, MnuboSDKConfig config) {
        this.credential = credential;
        this.template = template;
        this.config = config;
    }

    void postRequest(String url) {
        // entity
        HttpEntity<?> request = new HttpEntity<Object>(getAutorizationHeader());

        template.postForEntity(url, request, String.class);
    }

    <T> T postRequest(String url, Class<T> objectClass, Object object) {
        // entity
        HttpEntity<?> request = new HttpEntity<Object>(object, getAutorizationHeader());

        // send request
        return template.postForObject(url, request, objectClass);

    }

    void putRequest(String url, Object object) {
        // entity
        HttpEntity<?> request = new HttpEntity<Object>(object, getAutorizationHeader());

        template.put(url, request);
    }

    void deleteRequest(String url) {
        // entity
        HttpEntity<?> request = new HttpEntity<Object>(getAutorizationHeader());

        template.exchange(url, HttpMethod.DELETE, request, String.class);
    }

    HttpHeaders getAutorizationHeader() {
        // header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", credential.getAutorizationToken());
        return headers;
    }

    MnuboSDKConfig getConfig() {
        return config;
    }

    UriComponentsBuilder getBaseUri()
    {
        return UriComponentsBuilder.newInstance().host(getConfig().getHostName())
                                   .port(getConfig().getPlatformPort()).scheme(getConfig().getHttpProtocol())
                                   .path(getConfig().getHttpBasePath());
    }

}
