package com.mnubo.java.sdk.client;

import lombok.Value;
import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Fire up a local REST server that can easily mock a REST service.
 */
public class LocalRestServer implements AutoCloseable {
    private final Server server;
    public final String baseUrl;
    public final String host;
    public final int port;

    public LocalRestServer(Consumer<LocalRestContext> createHandlersFunc) {
        try {
            try (ServerSocket ss = new ServerSocket(0)) {
                port = ss.getLocalPort();
            }
            host = InetAddress.getLocalHost().getHostName();
            baseUrl = "http://" + host + ":" + port;
            server = new Server(Protocol.HTTP, port);
            server.setNext(new LocalRestApplication(createHandlersFunc, baseUrl));
            server.start();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        server.stop();
    }

    private static class LocalRestApplication extends Application {
        private final Consumer<LocalRestContext> createHandlersFunc;
        private final String baseUrl;

        private LocalRestApplication(Consumer<LocalRestContext> createHandlersFunc, String baseUrl) {
            this.createHandlersFunc = createHandlersFunc;
            this.baseUrl = baseUrl;
        }

        @Override
        public Restlet createInboundRoot() {
            Router router = new Router(getContext());

            createHandlersFunc.accept(new LocalRestContext(getContext(), baseUrl, router));

            return router;
        }
    }

    @Value
    public static class LocalRestContext {
        public Context restletContext;
        public String baseUrl;
        public Router router;
    }

}