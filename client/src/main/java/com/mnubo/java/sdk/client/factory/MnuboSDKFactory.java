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

package com.mnubo.java.sdk.client.factory;

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
import static com.mnubo.java.sdk.client.Constants.HTTP_PROTOCOL;
import static com.mnubo.java.sdk.client.Constants.HTTP_SOCKET_TIMEOUT;
import static com.mnubo.java.sdk.client.Constants.PLATFORM_PORT;
import static com.mnubo.java.sdk.client.Constants.SECURITY_CONSUMER_KEY;
import static com.mnubo.java.sdk.client.Constants.SECURITY_CONSUMER_SECRET;
import static com.mnubo.java.sdk.client.utils.ValidationUtils.validIsBlank;
import static com.mnubo.java.sdk.client.utils.ValidationUtils.validIsFile;
import static com.mnubo.java.sdk.client.utils.ValidationUtils.validNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.mnubo.java.sdk.client.config.MnuboSDKConfig;
import com.mnubo.java.sdk.client.services.MnuboSDKClientImpl;
import com.mnubo.java.sdk.client.spi.MnuboSDKClient;

public abstract class MnuboSDKFactory {
    private static final MnuboSDKConfig config = new MnuboSDKConfig();
    private static MnuboSDKClient client = null;

    public static MnuboSDKClient getAdvanceClient(Properties properties) {
        validNotNull(properties, "Configuration properties instance");
        if (properties.containsKey(HOST_NAME)) {
            config.sethostName(properties.getProperty(HOST_NAME));
        }
        if (properties.containsKey(PLATFORM_PORT)) {
            config.setPlatformPort(properties.getProperty(PLATFORM_PORT));
        }
        if (properties.containsKey(AUTHENTICATION_PORT)) {
            config.setAuthenticationPort(properties.getProperty(AUTHENTICATION_PORT));
        }
        if (properties.containsKey(SECURITY_CONSUMER_KEY)) {
            config.setSecurityConsumerKey(properties.getProperty(SECURITY_CONSUMER_KEY));
        }
        if (properties.containsKey(SECURITY_CONSUMER_SECRET)) {
            config.setSecurityConsumerSecret(properties.getProperty(SECURITY_CONSUMER_SECRET));
        }
        if (properties.containsKey(HTTP_PROTOCOL)) {
            config.setHttpProtocol(properties.getProperty(HTTP_PROTOCOL));
        }
        if (properties.containsKey(HTTP_DISABLE_REDIRECT_HANDLING)) {
            config.setHttpDisableRedirectHandling(properties.getProperty(HTTP_DISABLE_REDIRECT_HANDLING));
        }
        if (properties.containsKey(HTTP_DISABLE_AUTOMATIC_RETRIES)) {
            config.setHttpDisableAutomaticRetries(properties.getProperty(HTTP_DISABLE_AUTOMATIC_RETRIES));
        }
        if (properties.containsKey(HTTP_MAX_CONNECTIONS_PER_ROUTE)) {
            config.setHttpMaxConnectionPerRoute(properties.getProperty(HTTP_MAX_CONNECTIONS_PER_ROUTE));
        }
        if (properties.containsKey(HTTP_DEFAULT_TIMEOUT)) {
            config.setHttpDefaultTimeout(properties.getProperty(HTTP_DEFAULT_TIMEOUT));
        }
        if (properties.containsKey(HTTP_CONNECT_TIMEOUT)) {
            config.setHttpConnectionTiemout(properties.getProperty(HTTP_CONNECT_TIMEOUT));
        }
        if (properties.containsKey(HTTP_CONNECTION_REQUEST_TIMEOUT)) {
            config.setHttpConnectionRequestTimeout(properties.getProperty(HTTP_CONNECTION_REQUEST_TIMEOUT));
        }
        if (properties.containsKey(HTTP_SOCKET_TIMEOUT)) {
            config.setHttpSoketTimeout(properties.getProperty(HTTP_SOCKET_TIMEOUT));
        }
        if (properties.containsKey(HTTP_MAX_TOTAL_CONNECTION)) {
            config.setHttpMaxTotalConnection(properties.getProperty(HTTP_MAX_TOTAL_CONNECTION));
        }
        if (properties.containsKey(HTTP_BASE_PATH)) {
            config.setHttpBasePath(properties.getProperty(HTTP_BASE_PATH));
        }
        client = new MnuboSDKClientImpl(config);
        return client;
    }

    public static MnuboSDKClient getAdvanceClient(InputStream config) throws IOException {
        validNotNull(config, "configuration strimming instance");
        Properties properties = new Properties();
        properties.load(config);
        return getAdvanceClient(properties);

    }

    public static MnuboSDKClient getAdvanceClient(File configFile) throws IOException {
        validIsFile(configFile);
        FileInputStream config = new FileInputStream(configFile);
        return getAdvanceClient(config);
    }

    /***
     * Returns an Image object that can then be painted on the screen. The url argument
     * must specify an absolute {@link URL}. The name argument is a specifier that is
     * relative to the url argument.
     * <p>
     * This method always returns immediately, whether or not the image exists. When this
     * applet attempts to draw the image on the screen, the data will be loaded. The
     * graphics primitives that draw the image will incrementally paint on the screen.
     *
     * @param url an absolute URL giving the base location of the image
     * @param name the location of the image, relative to the url argument
     * @return the image at the specified URL
     * @see Image
     */
    public static MnuboSDKClient getClient(String hostName, String securityConsumerKey, String securityConsumerSecret) {
        validIsBlank(hostName, "hostname property canno be null.");
        validIsBlank(securityConsumerKey, "securityConsumerKey property canno be null.");
        validIsBlank(securityConsumerSecret, "securityConsumerSecret property canno be null.");
        config.sethostName(hostName);
        config.setSecurityConsumerKey(securityConsumerKey);
        config.setSecurityConsumerSecret(securityConsumerSecret);
        client = new MnuboSDKClientImpl(config);
        return client;
    }

}
