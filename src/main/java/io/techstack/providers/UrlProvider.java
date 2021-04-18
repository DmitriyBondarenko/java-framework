package io.techstack.providers;

import io.techstack.utils.PropertyReader;

public class UrlProvider {
    public static final String REST_CLIENT_BASE_URL = String.format("%s:%s/", getBaseUrl(), getPort());
    public static final String URL = String.format("%s/", getBaseUrl());

    public static String getBaseUrl() {
        return switch (EnvironmentProvider.ENV) {
            case "master" -> PropertyReader.getProperty("app.url.master");
            default -> throw new RuntimeException("Unable to generate Base URL");
        };
    }

    private static String getPort() {
        return switch (EnvironmentProvider.ENV) {
            case "master" -> PropertyReader.getProperty("rest.client.base.url.port");
            default -> throw new RuntimeException("Unable to generate connection string");
        };
    }
}
