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
    String CLIENT = "client.http.client.";
    String CLIENT_DISABLE_REDIRECT_HANDLING = CLIENT + "disable-redirect-handling";
    String CLIENT_DISABLE_AUTOMATIC_RETRIES = CLIENT + "disable-automatic-retries";
    String CLIENT_MAX_CONNECTIONS_PER_ROUTE = CLIENT + "max-connections-per-route";
    String CLIENT_DEFAULT_TIMEOUT = CLIENT + "http.client.default-timeout";
    String CLIENT_CONNECT_TIMEOUT = CLIENT + "http.client.connect-timeout";
    String CLIENT_CONNECTION_REQUEST_TIMEOUT = CLIENT + "connection-request-timeout";
    String CLIENT_SOCKET_TIMEOUT = CLIENT + "http.client.socket-timeout";
    String CLIENT_MAX_TOTAL_CONNECTION = CLIENT + "max-total-connection";
    String CLIENT_BASE_PATH = CLIENT + "base-path";

    int CLIENT_VALIDATE_INACTIVITY_SERVER = 1000;
    int MAX_PORT_VALUE = 65536;

    // Server
    String OBJECT_PATH = "/objects";

    // Security server
    String TOKEN_CONSUMER_SEPARATOR = ":";
    String TOKEN_PATH = "/oauth/token";
    String TOKEN_GRANT_TYPE = "grant_type";
    String TOKEN_GRANT_TYPE_VALUE = "client_credentials";
    String TOKEN_SCOPE = "scope";

    long FLETCHING_TOKEN_MILISEC = 5000;

}
