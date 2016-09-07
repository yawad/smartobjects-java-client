package com.mnubo.java.sdk.client.mapper;


import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GzipRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("Content-Encoding", "gzip");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(stream)) {
            gzip.write(bytes);
        }
        return execution.execute(request, stream.toByteArray());
    }
}
