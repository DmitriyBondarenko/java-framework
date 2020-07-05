package io.techstack.providers.driver;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;
import java.util.Set;

import io.techstack.utils.IWait;

public class WebDriverWrapper implements WebDriver, IWait, JavascriptExecutor, TakesScreenshot, HasCapabilities {
    private final WebDriver webDriver;

    private final String uniqueInstanceMarker = RandomStringUtils.randomAlphabetic(20);
    private final String browserName;
    private final String browserVersion;

    public WebDriverWrapper(WebDriver webDriver) {
        this.webDriver = webDriver;
        WebDriver capWebDriver = webDriver;
        if (webDriver instanceof WrapsDriver) {
            capWebDriver = ((WrapsDriver) webDriver).getWrappedDriver();
        }

        Capabilities caps = ((RemoteWebDriver) capWebDriver).getCapabilities();
        this.browserName = caps.getBrowserName();
        this.browserVersion = caps.getVersion();
    }

    public WebDriver getWrappedDriver() {
        return webDriver;
    }

    @Override
    public void get(String url) {
        webDriver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return webDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return webDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return webDriver.getPageSource();
    }

    @Override
    public void close() {
        webDriver.close();
    }

    @Override
    public void quit() {
        webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return webDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return webDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return webDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return webDriver.navigate();
    }

    @Override
    public Options manage() {
        return webDriver.manage();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) webDriver).executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        return ((JavascriptExecutor) webDriver).executeAsyncScript(script, args);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return ((TakesScreenshot) webDriver).getScreenshotAs(target);
    }

    @Override
    public Capabilities getCapabilities() {
        WebDriver driver = webDriver;
        if (driver instanceof EventFiringWebDriver) {
            driver = ((EventFiringWebDriver) driver).getWrappedDriver();
        }
        return ((HasCapabilities) driver).getCapabilities();
    }

    @Override
    public int hashCode() {
        return uniqueInstanceMarker.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebDriverWrapper that = (WebDriverWrapper) o;
        return webDriver.equals(that.webDriver);
    }

    public String getBrowserName() {
        return browserName;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }
}
