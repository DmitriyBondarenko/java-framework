package io.techstack.providers.driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.techstack.utils.BrowserList;
import io.techstack.utils.PropertyReader;
import lombok.SneakyThrows;

/**
 * This class provides methods to interact with WedDriver instance
 */
public class DriverProvider implements IDriverProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverProvider.class);
    private static final String BROWSER;
    private static final String REMOTE_DRIVER;
    private static final String HUB_URI;
    private DriverWrapper driver;
    public static BrowserList browserList;

    static {
        BROWSER = PropertyReader.getProperty("target.browser");
        REMOTE_DRIVER = PropertyReader.getProperty("remote.driver");
        HUB_URI = PropertyReader.getProperty("hub.uri");
        browserList = new BrowserList();
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

    @SneakyThrows
    private DriverWrapper createRemoteDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("sessionTimeout", "10m");
        desiredCapabilities.setCapability("screenResolution", "1900x2080x24");
        return new DriverWrapper(new RemoteWebDriver(URI.create(HUB_URI).toURL(), desiredCapabilities));
    }
}
