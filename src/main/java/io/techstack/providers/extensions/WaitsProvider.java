package io.techstack.providers.extensions;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public interface WaitsProvider extends WebDriver {
    long DEFAULT_TIMEOUT = 5;

    //region Wait for Element
    default WebElement waitForElementToBeDisplayed(WebElement element) {
        return new WebDriverWait(WaitsProvider.this, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(element));
    }

    default void waitForElementToBeNotDisplayed(WebElement element) {
        new WebDriverWait(WaitsProvider.this, DEFAULT_TIMEOUT).until(ExpectedConditions.invisibilityOf(element));
    }

    default WebElement waitForElementToBeEnabled(WebElement element) {
        return new WebDriverWait(WaitsProvider.this, DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));
    }

    default WebElement waitForElementToBePresent(By locator, long timeout) {
        return new WebDriverWait(WaitsProvider.this, DEFAULT_TIMEOUT).until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    //endregion

    //region Wait for Elements
    default List<WebElement> waitForElementsToBeDisplayed(List<WebElement> elements) {
        return new WebDriverWait(WaitsProvider.this, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    default void waitForElementsToBeNotDisplayed(WebElement element) {
        new WebDriverWait(WaitsProvider.this, DEFAULT_TIMEOUT).until(ExpectedConditions.invisibilityOfAllElements(element));
    }
    //endregion

    //region Wait for URL
    default void waitForPageUrlContains(String url) {
        new WebDriverWait(WaitsProvider.this, DEFAULT_TIMEOUT).until(ExpectedConditions.urlContains(url));
    }
    //endregion

    //region Is conditions
    default boolean isElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(WaitsProvider.this, DEFAULT_TIMEOUT);
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    default boolean isElementVisibleNow(WebElement element) {
        WebDriverWait wait = new WebDriverWait(WaitsProvider.this, 0);
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)) != null;
        } catch (TimeoutException e) {
            return false;
        }
    }

    default boolean isElementExists(WebElement element) {
        try {
            if (element == null)
                return false;
            if (element.getTagName().contains("Exception"))
                return false;
        } catch (NoSuchElementException ex) {
            return false;
        }
        return true;
    }
    //endregion
}
