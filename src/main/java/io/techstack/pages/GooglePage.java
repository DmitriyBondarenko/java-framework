package io.techstack.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import io.techstack.pages.base.AbstractPage;

public class GooglePage extends AbstractPage {

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = ".//input[@name = 'q']")
    public WebElement searchInput;

    @FindBy(xpath = ".//h3[contains(text(), 'Automation')]")
    public List<WebDriver> searchResults;

    public void openGoogle() {
        driver.get("https://www.google.com/");
    }
}
