package io.techstack.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.techstack.pages.base.AbstractPage;
import io.techstack.providers.driver.DriverWrapper;

public class MainPage extends AbstractPage {

    @FindBy(xpath = ".//input[@name = 'q']")
    public WebElement searchInput;

    @Override
    public void waitForMainPageElement() {
        driver.waitForElementToBeDisplayed(searchInput);
    }
}
