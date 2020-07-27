package io.techstack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import io.techstack.pages.base.AbstractPage;

public class SearchResultsPage extends AbstractPage {

    @FindBy(xpath = ".//div[@id = 'result-stats']")
    public WebElement resultsCountLabel;

    @Override
    public void waitForMainPageElement() {
        driver.waitForElementToBeDisplayed(resultsCountLabel);
    }

    public List<WebElement> getSearchResults(String request) {
        return driver.findElements(By.xpath(String.format(".//h3[contains(text(), '%s')]", request)));
    }
}
