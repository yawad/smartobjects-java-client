package com.mnubo.java.sdk.client.services;

import com.mnubo.java.sdk.client.config.MnuboSDKConfig;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;
import static org.mockito.Mockito.mock;

/**
 * Created by mauro on 09/03/16.
 */
public abstract class AbstractServiceTest {

    protected static String HOST = "host";
    protected static String OWNER_URL = format("https://%s:443/api/v3/owners", HOST);

    private static RestTemplate restTemplate = mock(RestTemplate.class);
    private static CredentialHandler credentials = mock(CredentialHandler.class);
    private static MnuboSDKConfig config = MnuboSDKConfig.builder().withHostName(HOST).withSecurityConsumerKey("CK")
            .withSecurityConsumerSecret("SC").build();
    private static MnuboSDKClientImpl client = new MnuboSDKClientImpl(config, restTemplate, credentials);

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    public static MnuboSDKClientImpl getClient() {
        return client;
    }

    public static RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public static CredentialHandler getCredentials() {
        return credentials;
    }

    public static MnuboSDKConfig getConfig() {
        return config;
    }
}
