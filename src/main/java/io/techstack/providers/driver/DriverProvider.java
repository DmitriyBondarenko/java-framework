package io.techstack.providers.driver;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.techstack.properties.PropertyReader;

public class DriverProvider implements IDriverProvider {

    private static final String BROWSER;
    private WebDriver driver;

    static {
        BROWSER = PropertyReader.getProperty("target.browser");
    }

    @Override
    public WebDriver getInstance() {
        switch (BROWSER) {
            case "chrome" -> WebDriverManager.chromedriver().setup();
            case "firefox" -> WebDriverManager.firefoxdriver().setup();
            default -> throw new RuntimeException("Incorrect browser type" + BROWSER);
        }
        return driver = DriverFactory.getDriver(BROWSER);
    }

    @Override
    public void destroy() {
        if (Objects.nonNull(driver)) {
            driver.quit();
            driver = null;
        }
    }
}
