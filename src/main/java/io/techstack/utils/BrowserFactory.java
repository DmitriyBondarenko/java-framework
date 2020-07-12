package io.techstack.utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.techstack.providers.driver.DriverWrapper;

/**
 * This class provides ability to create different Drivers objects
 * Available drivers to create: FirefoxDriver, ChromeDriver
 */
public class BrowserFactory {

    private BrowserFactory() {}

    public static DriverWrapper getDriver(String browser) {
        if ("FIREFOX".equalsIgnoreCase(browser)) {
            return new DriverWrapper(new FirefoxDriver());
        } else if ("CHROME".equalsIgnoreCase(browser)) {
            return new DriverWrapper(new ChromeDriver());
        } else {
            throw new IllegalArgumentException("Invalid browser name: " + browser);
        }
    }
}

