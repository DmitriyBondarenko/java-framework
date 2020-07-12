package io.techstack.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestWebClient {
    private HttpClient client;
    private HttpRequest request;

    public RestWebClient() {
        client = HttpClient.newHttpClient();
    }

    public HttpResponse<?> doPost() throws IOException, InterruptedException {
        request = HttpRequest.newBuilder(URI.create(""))
                             .POST(null)
                             .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<?> doGet() throws IOException, InterruptedException {
        request = HttpRequest.newBuilder(URI.create(""))
                             .GET()
                             .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
