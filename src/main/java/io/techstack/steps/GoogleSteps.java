package io.techstack.steps;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.Keys;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.techstack.dto.UserDto;
import io.techstack.pages.MainPage;
import io.techstack.pages.SearchResultsPage;
import io.techstack.providers.driver.DriverProvider;
import io.techstack.providers.driver.DriverWrapper;
import io.techstack.utils.BrowserList;

public class GoogleSteps {
    private DriverWrapper _driver;
    private final BrowserList _browserList;
    private final UserDto _user;

    public GoogleSteps(BrowserList browserList, UserDto userDto) {
        _driver = browserList.getBrowser();
        _browserList = browserList;
        _user = userDto;
    }

    @Given("Google page is opened")
    public void googlePageIsOpened() {
        _driver.navigateToUrl("https://www.google.com/");
    }

    @When("User enters search request {string}")
    public void userEntersSearchRequestAutomation(String string) {
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
