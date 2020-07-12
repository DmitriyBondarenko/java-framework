package io.techstack.providers.driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.techstack.utils.PropertyReader;
import io.techstack.utils.BrowserFactory;

/**
 * This class provides methods to interact with WedDriver instance
 */
public class DriverProvider implements IDriverProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverProvider.class);
    private static final String BROWSER;
    private static final String REMOTE_DRIVER;
    private DriverWrapper driver;

    static {
        BROWSER = PropertyReader.getProperty("target.browser");
        REMOTE_DRIVER = PropertyReader.getProperty("remote.driver");
    }

    @Override
    public DriverWrapper getInstance() {
        return Optional.ofNullable(driver).orElseGet(this::createDriverInstance);
    }

    @Override
    public void destroy() {
        if (Objects.nonNull(driver)) {
            try {
                driver.manage().deleteAllCookies();
            } catch (Exception ex) {
                LOGGER.error(String.format("An exception occurred while cookies deleting: %s", ex));
            }
            try {
                driver.close();
            } catch (Exception ex) {
                LOGGER.error(String.format("An exception occurred while closing the driver: %s", ex));
            }
            try {
                driver.quit();
            } catch (Exception ex) {
                LOGGER.error(String.format("An exception occurred while quiting the driver: %s", ex));
            }
            driver = null;
        }
    }

    private DriverWrapper createDriverInstance() {
        return switch (REMOTE_DRIVER) {
            case "local" -> createLocalDriver();
            case "remote" -> createRemoteDriver();
            default -> throw new RuntimeException(String.format("Incorrect parameter type for remote/local driver: " +
                    "%s", REMOTE_DRIVER));
        };
    }

    private DriverWrapper createLocalDriver() {
        return switch (BROWSER) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions()
                        .addArguments("start-maximized")
                        .addArguments("disable-infobars");
                WebDriverManager.chromedriver().setup();
                yield driver = new DriverWrapper(new ChromeDriver(options));
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                yield driver = new DriverWrapper(new FirefoxDriver());
            }
            default -> throw new RuntimeException(String.format("Incorrect browser type: %s", BROWSER));
        };
    }

    private DriverWrapper createRemoteDriver() {
        return null;
    }
}
