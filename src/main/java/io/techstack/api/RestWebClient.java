package io.techstack.api;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.swing.text.html.HTMLDocument;

import io.techstack.dto.AuthObject;

public class RestWebClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestWebClient.class);
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

    public AuthObject getAuthenticationDetails(String url) {
        var clientBaseUrl = URI.create(url);

        var doc = new HTMLDocument();

        int attempts = 2;
        var html = StringUtils.EMPTY;
        while (attempts > 0) {
            var request = HttpRequest.newBuilder(clientBaseUrl).GET().build();
            HttpResponse<Object> response = null;
            try {
                response = client.send(request, null);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            if (response.statusCode() != HttpStatus.SC_OK)
                LOGGER.error(String.format("Automation server is not responding: %s", response.statusCode()));
            response.body();
            attempts--;
        }
        return new AuthObject();
    }
}
