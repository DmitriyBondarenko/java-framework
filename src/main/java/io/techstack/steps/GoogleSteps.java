package io.techstack.steps;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.Keys;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.techstack.dto.User;
import io.techstack.pages.MainPage;
import io.techstack.pages.SearchResultsPage;
import io.techstack.providers.driver.DriverProvider;
import io.techstack.providers.driver.DriverWrapper;

public class GoogleSteps {
    private final DriverWrapper driver;
    private final User user;

    public GoogleSteps(DriverProvider driverProvider, User user) {
        this.driver = driverProvider.getInstance();
        this.user = user;
    }

    @Given("Google page is opened")
    public void googlePageIsOpened() {
        driver.navigate().to("https://www.google.com/");
    }

    @When("User enters search request {string}")
    public void userEntersSearchRequestAutomation(String string) {
        MainPage mainPage = driver.nowAt(MainPage.class);

        mainPage.searchInput.sendKeys(string);
        mainPage.searchInput.sendKeys(Keys.ENTER);
    }

    @Then("Results page with {string} is displayed")
    public void resultsPageWithRequestIsDisplayed(String string) {
        SearchResultsPage searchResultsPage = driver.nowAt(SearchResultsPage.class);

        int results = driver.waitForElements(searchResultsPage.getSearchResults(string)).size();
        Assertions.assertThat(results).as("Search results are not valid").isGreaterThan(5);
    }
}
