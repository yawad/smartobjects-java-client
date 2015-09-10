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

package com.mnubo.java.sdk.client.config;

import static com.mnubo.java.sdk.client.Constants.AUTHENTICATION_PORT;
import static com.mnubo.java.sdk.client.Constants.HOST_NAME;
import static com.mnubo.java.sdk.client.Constants.HTTP_BASE_PATH;
import static com.mnubo.java.sdk.client.Constants.HTTP_CONNECTION_REQUEST_TIMEOUT;
import static com.mnubo.java.sdk.client.Constants.HTTP_CONNECT_TIMEOUT;
import static com.mnubo.java.sdk.client.Constants.HTTP_DEFAULT_TIMEOUT;
import static com.mnubo.java.sdk.client.Constants.HTTP_DISABLE_AUTOMATIC_RETRIES;
import static com.mnubo.java.sdk.client.Constants.HTTP_DISABLE_REDIRECT_HANDLING;
import static com.mnubo.java.sdk.client.Constants.HTTP_MAX_CONNECTIONS_PER_ROUTE;
import static com.mnubo.java.sdk.client.Constants.HTTP_MAX_TOTAL_CONNECTION;
import static com.mnubo.java.sdk.client.Constants.HTTP_SOCKET_TIMEOUT;
import static com.mnubo.java.sdk.client.Constants.PLATFORM_PORT;
import static com.mnubo.java.sdk.client.Constants.SECURITY_CONSUMER_KEY;
import static com.mnubo.java.sdk.client.Constants.SECURITY_CONSUMER_SECRET;
import static com.mnubo.java.sdk.client.utils.ValidationUtils.checkMandatoryProperty;
import static com.mnubo.java.sdk.client.utils.ValidationUtils.parseAsBoolean;
import static com.mnubo.java.sdk.client.utils.ValidationUtils.parseAsHttpProtocol;
import static com.mnubo.java.sdk.client.utils.ValidationUtils.parseAsInteger;
import static com.mnubo.java.sdk.client.utils.ValidationUtils.parseAsPort;
import static com.mnubo.java.sdk.client.utils.ValidationUtils.parseAsString;

public class MnuboSDKConfig {

    // default values
    private final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 200;
    private final int DEFAULT_MAX_TOTAL_CONNECTIONS = 200;
    private final int DEFAULT_TIMEOUT = 15000;
    private final int DEFAULT_HOST_PORT = 443;
    private final String DEFAULT_SCOPE = "ALL";
    private final String DEFAULT_HTTP_PROTOCOL = "https";
    private final String DEFAULT_BASE_PATH = "/api/v3";
    private final boolean DEFAULT_DISSABLE_COOKIE_MANAGEMENT = true;
    private final boolean DEFAULT_DISABLE_REDIRECT_HANDLING = false;
    private final boolean DEFAULT_DISABLE_AUTOMATIC_RETRIES = false;
    private final boolean DEFAULT_SYSTEM_PROPERTIES_ENABLE = true;

    // mandatory varibles
    private String hostName;
    private String SecurityConsumerKey;
    private String SecurityConsumerSecret;

    // optional variables
    private int platformPort = DEFAULT_HOST_PORT;
    private int authenticationPort = DEFAULT_HOST_PORT;
    private String scope = DEFAULT_SCOPE;
    private String httpProtocol = DEFAULT_HTTP_PROTOCOL;
    private boolean httpDisableCockieManagement = DEFAULT_DISSABLE_COOKIE_MANAGEMENT;
    private boolean httpDisableRedirectHandling = DEFAULT_DISABLE_REDIRECT_HANDLING;
    private boolean httpDisableAutomaticRetries = DEFAULT_DISABLE_AUTOMATIC_RETRIES;
    private boolean httpSystemPropertiesEnable = DEFAULT_SYSTEM_PROPERTIES_ENABLE;
    private int httpMaxTotalConnection = DEFAULT_MAX_TOTAL_CONNECTIONS;
    private int httpMaxConnectionPerRoute = DEFAULT_MAX_CONNECTIONS_PER_ROUTE;
    private int httpDefaultTimeout = DEFAULT_TIMEOUT;
    private int httpConnectionTiemout = DEFAULT_TIMEOUT;
    private int httpConnectionRequestTimeout = DEFAULT_TIMEOUT;
    private int httpSoketTimeout = DEFAULT_TIMEOUT;
    private String basePath = DEFAULT_BASE_PATH;

    public void sethostName(String platformServer) {
        this.hostName = parseAsString(platformServer, HOST_NAME);
    }

    public void setPlatformPort(String platformPort) {
        this.platformPort = parseAsPort(platformPort, PLATFORM_PORT);
    }

    public void setAuthenticationPort(String authenticationPort) {
        this.authenticationPort = parseAsPort(authenticationPort, AUTHENTICATION_PORT);
    }

    public void setSecurityConsumerKey(String securityConsumerkey) {
        this.SecurityConsumerKey = parseAsString(securityConsumerkey, SECURITY_CONSUMER_KEY);
    }

    public void setSecurityConsumerSecret(String securityConsumerSecret) {
        this.SecurityConsumerSecret = parseAsString(securityConsumerSecret, SECURITY_CONSUMER_SECRET);
    }

    public void setHttpProtocol(String httpProtocol) {
        this.httpProtocol = parseAsHttpProtocol(httpProtocol);
    }

    public void setHttpDisableRedirectHandling(String httpDisableRedirectHandling) {
        this.httpDisableRedirectHandling = parseAsBoolean(httpDisableRedirectHandling, HTTP_DISABLE_REDIRECT_HANDLING);
    }

    public void setHttpDisableAutomaticRetries(String httpDisableAutomaticRetries) {
        this.httpDisableAutomaticRetries = parseAsBoolean(httpDisableAutomaticRetries, HTTP_DISABLE_AUTOMATIC_RETRIES);
    }

    public void setHttpMaxConnectionPerRoute(String maxConnectionPerRoute) {
        this.httpMaxConnectionPerRoute = parseAsInteger(maxConnectionPerRoute, HTTP_MAX_CONNECTIONS_PER_ROUTE);
    }

    public void setHttpDefaultTimeout(String httpDefaultTimeout) {
        this.httpDefaultTimeout = parseAsInteger(httpDefaultTimeout, HTTP_DEFAULT_TIMEOUT);
    }

    public void setHttpConnectionTiemout(String httpConnectionTiemout) {
        this.httpConnectionTiemout = parseAsInteger(httpConnectionTiemout, HTTP_CONNECT_TIMEOUT);
    }

    public void setHttpConnectionRequestTimeout(String httpConnectionRequestTimeout) {
        this.httpConnectionRequestTimeout = parseAsInteger(httpConnectionRequestTimeout,
                HTTP_CONNECTION_REQUEST_TIMEOUT);
    }

    public void setHttpMaxTotalConnection(String maxTotalConnection) {
        this.httpMaxTotalConnection = parseAsInteger(maxTotalConnection, HTTP_MAX_TOTAL_CONNECTION);
    }

    public void setHttpSoketTimeout(String httpSoketTimeout) {
        this.httpSoketTimeout = parseAsInteger(httpSoketTimeout, HTTP_SOCKET_TIMEOUT);
    }

    public void setHttpBasePath(String basePath) {
        this.basePath = parseAsString(basePath, HTTP_BASE_PATH);
    }

    public String getHostName() {
        return hostName;
    }

    public int getPlatformPort() {
        return platformPort;
    }

    public int getAuthenticationPort() {
        return authenticationPort;
    }

    public String getSecurityConsumerKey() {
        return SecurityConsumerKey;
    }

    public String getSecurityConsumerSecret() {
        return SecurityConsumerSecret;
    }

    public String getScope() {
        return scope;
    }

    public String getHttpProtocol() {
        return httpProtocol;
    }

    public boolean isHttpDisableCockieManagement() {
        return httpDisableCockieManagement;
    }

    public boolean isHttpDisableRedirectHandling() {
        return httpDisableRedirectHandling;
    }

    public boolean isHttpDisableAutomaticRetries() {
        return httpDisableAutomaticRetries;
    }

    public boolean isHttpSystemPropertiesEnable() {
        return httpSystemPropertiesEnable;
    }

    public int getHttpMaxConnectionPerRoute() {
        return httpMaxConnectionPerRoute;
    }

    public int getHttpDefaultTimeout() {
        return httpDefaultTimeout;
    }

    public int getHttpConnectionTiemout() {
        return httpConnectionTiemout;
    }

    public int getHttpConnectionRequestTimeout() {
        return httpConnectionRequestTimeout;
    }

    public int getHttpSoketTimeout() {
        return httpSoketTimeout;
    }

    public int getHttpMaxTotalConnection() {
        return httpMaxTotalConnection;
    }

    public String getHttpBasePath() {
        return basePath;
    }

    public boolean isInitialized() {
        checkMandatoryProperty(hostName, HOST_NAME);
        checkMandatoryProperty(SecurityConsumerKey, SECURITY_CONSUMER_KEY);
        checkMandatoryProperty(SecurityConsumerSecret, SECURITY_CONSUMER_SECRET);
        return true;
    }

}
