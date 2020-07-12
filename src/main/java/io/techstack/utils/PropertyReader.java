package io.techstack.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private PropertyReader() {}

    public static String getProperty(String name) {
        try {
            Properties props = new Properties();
            props.load(PropertyReader.class.getResourceAsStream("/config.properties"));

            String property = props.getProperty(name);
            if (property == null) {
                throw new IllegalArgumentException(String.format("Could not read property with key: %s", name));
            }
            return property;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
