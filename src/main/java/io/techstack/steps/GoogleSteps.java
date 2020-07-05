package io.techstack.steps;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.techstack.pages.GooglePage;
import io.techstack.providers.driver.DriverProvider;


public class GoogleSteps {
    private WebDriver driver;
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
    }

    @Then("Results page is opened")
    public void resultsPageIsOpened() {
        int results = driver.findElements(By.xpath(".//h3[contains(text(), 'Automation')]")).size();
        Assertions.assertThat(results).as("Search results are not valid").isGreaterThan(5);
    }
}
