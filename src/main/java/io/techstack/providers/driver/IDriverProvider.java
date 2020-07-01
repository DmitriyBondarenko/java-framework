package io.techstack.providers.driver;

import org.openqa.selenium.WebDriver;

/**
 * This interface provides ability to interact with web driver instance.
 */
public interface IDriverProvider {

    WebDriver getInstance();

    default void destroy() {
        getInstance().quit();
    }
}
