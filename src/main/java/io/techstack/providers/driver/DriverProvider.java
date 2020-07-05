package io.techstack.providers.driver;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.techstack.properties.PropertyReader;

public class DriverProvider implements IDriverProvider {

    private static final String BROWSER;
    private WebDriverWrapper driver;

    static {
        BROWSER = PropertyReader.getProperty("target.browser");
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
        driver = DriverFactory.getDriver(BROWSER);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }
}
