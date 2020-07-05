package io.techstack.steps;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.techstack.pages.GooglePage;
import io.techstack.providers.driver.DriverProvider;
import io.techstack.providers.driver.WebDriverWrapper;

public class GoogleSteps {
    private final WebDriverWrapper driver;
    private final GooglePage googlePage;

    public GoogleSteps(DriverProvider driverProvider) {
        this.driver = driverProvider.getInstance();
        googlePage = new GooglePage(driver);
    }

    @Given("Google page is opened")
    public void googlePageIsOpened() {
        googlePage.openGoogle();
    }

    @When("User enters search request {string}")
    public void userEntersSearchRequestAutomation(String string) {
        driver.waitForElement(this.driver, googlePage.searchInput);
        googlePage.searchInput.sendKeys(string);
        googlePage.searchInput.sendKeys(Keys.ENTER);
    }

    @Then("Results page is opened")
    public void resultsPageIsOpened() {
        driver.waitForElements(this.driver, googlePage.searchResults);
        int results = googlePage.searchResults.size();
        Assertions.assertThat(results).as("Search results are not valid").isGreaterThan(5);
    }
}
