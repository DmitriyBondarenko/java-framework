package io.techstack.providers.driver;

import java.util.Objects;
import java.util.Optional;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.techstack.properties.PropertyReader;
import io.techstack.utils.BrowserFactory;

/**
 * This class provides methods to interact with WedDriver instance
 */
public class DriverProvider implements IDriverProvider {
    private static final String BROWSER;
    private static final String REMOTE_DRIVER;
    private WebDriverWrapper driver;

    static {
        BROWSER = PropertyReader.getProperty("target.browser");
        REMOTE_DRIVER = PropertyReader.getProperty("remote.driver");
    }

    @Override
    public WebDriverWrapper getInstance() {
        return Optional.ofNullable(driver).orElseGet(this::createDriverInstance);
    }

    @Override
    public void destroy() {
        if (Objects.nonNull(driver)) {
            driver.quit();
            driver = null;
        }
    }

    private WebDriverWrapper createDriverInstance() {
      switch (BROWSER) {
            case "chrome" -> WebDriverManager.chromedriver().setup();
            case "firefox" -> WebDriverManager.firefoxdriver().setup();
            default -> throw new RuntimeException("Incorrect browser type" + BROWSER);
        }
        driver = BrowserFactory.getDriver(BROWSER);
        driver.manage().window().maximize();
        return driver;
    }
}
