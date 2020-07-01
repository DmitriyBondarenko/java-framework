package io.techstack.properties;

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
                throw new IllegalArgumentException("Could not read property with key " + name);
            }
            return property;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
