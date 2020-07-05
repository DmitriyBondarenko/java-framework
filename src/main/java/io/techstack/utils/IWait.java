package io.techstack.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public interface IWait extends WebDriver {
    long DEFAULT_TIMEOUT = 5;

    default void waitForElement(WebElement element) {
        new WebDriverWait(IWait.this, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(element));
    }

    default void waitForElement(By by) {
        new WebDriverWait(IWait.this, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    default void waitForElements(List<WebElement> elements) {
        new WebDriverWait(IWait.this, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElements(elements));
    }
}
