package io.techstack.steps;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.techstack.providers.driver.DriverProvider;

public class GoogleSteps {

    WebDriver driver = new DriverProvider().getInstance();

    @Given("Google page is opened")
    public void googlePageIsOpened() {
        driver.get("www.google.com");
    }

    @When("User enters search request {string}")
    public void userEntersSearchRequestAutomation(String string) {

    }

    @Then("Results page is opened")
    public void resultsPageIsOpened() {
    }
}
