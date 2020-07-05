package io.techstack.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import io.techstack.providers.driver.WebDriverWrapper;

public interface IWait {
    long DEFAULT_TIMEOUT = 5;

    default void waitForElement(WebDriverWrapper driver, WebElement element) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(element));
    }

    default void waitForElements(WebDriverWrapper driver, List<WebElement> elements) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElements(elements));
    }
}
