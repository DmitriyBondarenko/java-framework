package io.techstack.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.techstack.utils.PropertyReader;

public class UrlProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlProvider.class);
    public static final String REST_CLIENT_BASE_URL = String.format("%s:%s/", getBaseUrl(), getPort());

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
