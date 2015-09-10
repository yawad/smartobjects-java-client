package com.mnubo.java.sdk.client;

public interface Constants {
    // Token
    String ACCESS_TOKEN = "access_token";
    String TOKEN_TYPE = "token_type";
    String EXPIRE = "expires_in";
    String SCOPE = "scope";
    String JTI = "jti";

    // Config
    String CONFIG = "client.config.";
    String HOST_NAME = CONFIG + "hostname";
    String PLATFORM_PORT = CONFIG + "platform-port";
    String AUTHENTICATION_PORT = CONFIG + "autentication-port";
    String HTTP_PROTOCOL = CONFIG + "http-protocol";
    String SECUTIRY = "client.security.";
    String SECURITY_CONSUMER_KEY = SECUTIRY + "consumer-key";
    String SECURITY_CONSUMER_SECRET = SECUTIRY + "consumer-secret";
    String HTTP = "client.http.client.";
    String HTTP_DISABLE_REDIRECT_HANDLING = HTTP + "disable-redirect-handling";
    String HTTP_DISABLE_AUTOMATIC_RETRIES = HTTP + "disable-automatic-retries";
    String HTTP_MAX_CONNECTIONS_PER_ROUTE = HTTP + "max-connections-per-route";
    String HTTP_DEFAULT_TIMEOUT = HTTP + "http.client.default-timeout";
    String HTTP_CONNECT_TIMEOUT = HTTP + "http.client.connect-timeout";
    String HTTP_CONNECTION_REQUEST_TIMEOUT = HTTP + "http.client.connection-request-timeout";
    String HTTP_SOCKET_TIMEOUT = HTTP + "http.client.socket-timeout";
    String HTTP_MAX_TOTAL_CONNECTION = HTTP + "max-total-connection";
    String HTTP_BASE_PATH = HTTP + "base-path";

    int MAX_PORT_VALUE = 65536;

    // Server
    String OBJECT_PATH = "/objects";

    // Security server
    String TOKEN_CONSUMER_SEPARATOR = ":";
    String TOKEN_PATH = "/oauth/token";
    String TOKEN_GRANT_TYPE = "grant_type";
    String TOKEN_GRANT_TYPE_VALUE = "client_credentials";
    String TOKEN_SCOPE = "scope";

}
