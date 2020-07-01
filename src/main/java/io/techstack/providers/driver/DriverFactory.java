package io.techstack.providers.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private DriverFactory() {}

    public static WebDriver getDriver(String browser) {
        if ("FIREFOX".equalsIgnoreCase(browser)) {
            return new FirefoxDriver();
        } else if ("CHROME".equalsIgnoreCase(browser)) {
            return new ChromeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser name: " + browser);
        }
    }
}
