package io.techstack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import io.techstack.pages.base.AbstractPage;
import io.techstack.providers.driver.DriverWrapper;

public class GooglePage extends AbstractPage {

    public GooglePage(DriverWrapper driver) {
        super(driver);
    }

    @FindBy(xpath = ".//input[@name = 'q']")
    public WebElement searchInput;

    @FindBy(xpath = ".//h3[contains(text(), 'Automation')]")
    public List<WebElement> searchResults;

    public List<WebElement> getSearchResults(String request) {
       return driver.findElements(By.xpath(String.format(".//h3[contains(text(), '%s')]", request)));
    }
}
