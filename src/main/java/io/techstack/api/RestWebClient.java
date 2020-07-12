package io.techstack.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import io.techstack.utils.PropertyReader;

public class RestWebClient {
    private HttpClient client;
    private HttpRequest request;

    public RestWebClient() {
        client = HttpClient.newHttpClient();
        request = HttpRequest.newBuilder()
                             .uri(URI.create(PropertyReader.getProperty("base.uri")))
                             .GET()
                             .build();
    }
}
