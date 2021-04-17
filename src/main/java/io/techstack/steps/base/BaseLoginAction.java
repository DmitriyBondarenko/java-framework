package io.techstack.steps.base;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import io.techstack.api.RestWebClient;
import io.techstack.dto.UserDto;
import io.techstack.providers.UrlProvider;
import io.techstack.providers.driver.DriverWrapper;

public class BaseLoginAction {
    private RestWebClient _webClient;
    private UserDto _user;

    public BaseLoginAction(RestWebClient webClient, UserDto user) {
        _webClient = webClient;
        _user = user;
    }

    protected void loginViaApiUser(DriverWrapper driver, UserDto user) {
        try {
            var client = RestWebClient.newBuilder().version(Version.HTTP_1_1).build();
            URI uri = new URIBuilder(String.format("%s%s", UrlProvider.REST_CLIENT_BASE_URL, "/authentication/login"))
                    .addParameter("content-type", "multipart/form-data;")
                    .addParameter("username", "admin")
                    .addParameter("password", "m!gration")
                    .build();

            var request = HttpRequest.newBuilder(uri).POST(BodyPublishers.noBody()).build();
            var response = client.send(request, BodyHandlers.ofString());
        } catch (Exception ex) {
            throw new RuntimeException(String.format("Unable to Login via API: %s", ex.getMessage()));
        }
    }
}
