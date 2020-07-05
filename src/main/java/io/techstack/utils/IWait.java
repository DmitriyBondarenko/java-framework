package io.techstack.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface IWait {
    static final long DEFAULT_TIMEOUT = 10;

    default void waitForElement(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(element));
    }
}
