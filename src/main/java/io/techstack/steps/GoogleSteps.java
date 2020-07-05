package io.techstack.steps;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.techstack.dto.User;
import io.techstack.pages.GooglePage;
import io.techstack.providers.driver.DriverProvider;
import io.techstack.providers.driver.WebDriverWrapper;

public class GoogleSteps {
    private final WebDriverWrapper driver;
    private final GooglePage googlePage;
    private final User user;

    public GoogleSteps(DriverProvider driverProvider, User user) {
        this.driver = driverProvider.getInstance();
        this.user = user;
        googlePage = new GooglePage(driver);
    }

    @Given("Google page is opened")
    public void googlePageIsOpened() {
        googlePage.openGoogle();
    }

    @When("User enters search request {string}")
    public void userEntersSearchRequestAutomation(String string) {
        driver.waitForElement(googlePage.searchInput);
        googlePage.searchInput.sendKeys(string);
        googlePage.searchInput.sendKeys(Keys.ENTER);
    }

    @Then("Results page is opened")
    public void resultsPageIsOpened() {
        driver.waitForElements(googlePage.searchResults);
        int results = googlePage.searchResults.size();
        Assertions.assertThat(results).as("Search results are not valid").isGreaterThan(5);
    }
}
