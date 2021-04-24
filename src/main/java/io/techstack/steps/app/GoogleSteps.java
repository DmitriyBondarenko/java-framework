package io.techstack.steps.app;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.Keys;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.techstack.api.RestWebClient;
import io.techstack.components.Button;
import io.techstack.pages.MainPage;
import io.techstack.pages.SearchResultsPage;
import io.techstack.providers.driver.DriverWrapper;
import io.techstack.utils.BrowserList;

public class GoogleSteps {
    private DriverWrapper _driver;
    private final BrowserList _browserList;

    public GoogleSteps(BrowserList browserList, RestWebClient webClient) {
        _driver = browserList.getBrowser();
        _browserList = browserList;
    }

    @Given("Google page is opened")
    public void googlePageIsOpened() {
        _driver.navigateToUrl("https://www.google.com/");
    }

    @When("User enters search request {string}")
    public void userEntersSearchRequestAutomation(String string) {
        var button1 = _driver.getComponent(Button.class, "Пошук Google");
        var mainPage = _driver.nowAt(MainPage.class);

        mainPage.searchInput.sendKeys(string);
        mainPage.searchInput.sendKeys(Keys.ENTER);
    }

    @Then("Results page with {string} is displayed")
    public void resultsPageWithRequestIsDisplayed(String string) {
        var searchResultsPage = _driver.nowAt(SearchResultsPage.class);

        int results = _driver.waitForElementsToBeDisplayed(searchResultsPage.getSearchResults(string)).size();
        Assertions.assertThat(results).as("Search results are not valid").isGreaterThan(5);
    }
}
