package io.techstack.providers;

import io.techstack.utils.PropertyReader;

public class EnvironmentProvider {
    public static final String ENV = PropertyReader.getProperty("environment.flag");
}
