package io.techstack.steps;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.techstack.providers.driver.DriverProvider;

public class GoogleSteps {
    private final WebDriver driver;

    public GoogleSteps(DriverProvider driverProvider) {
        this.driver = driverProvider.getInstance();
    }

    @Given("Google page is opened")
    public void googlePageIsOpened() {
        driver.get("https://www.google.com/");
    }

    @When("User enters search request {string}")
    public void userEntersSearchRequestAutomation(String string) {
        driver.findElement(By.xpath(".//input[@name = 'q']")).sendKeys(string);
        driver.findElement(By.xpath(".//input[@name = 'q']")).sendKeys(Keys.ENTER);
    }

    @Then("Results page is opened")
    public void resultsPageIsOpened() {
        int results = driver.findElements(By.xpath(".//h3[contains(text(), 'Automation')]")).size();
        Assertions.assertThat(results).as("Search results are not valid").isGreaterThan(5);
    }
}
