package io.techstack.providers.extensions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public interface WaitsProvider extends WebDriver {
    long DEFAULT_TIMEOUT = 5;

    default WebElement waitForElement(WebElement element) {
        return new WebDriverWait(WaitsProvider.this, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOf(element));
    }

    default List<WebElement> waitForElements(List<WebElement> elements) {
        return new WebDriverWait(WaitsProvider.this, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElements(elements));
    }
}
