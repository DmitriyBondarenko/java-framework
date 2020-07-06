package io.techstack.steps;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.Keys;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.techstack.dto.User;
import io.techstack.pages.GooglePage;
import io.techstack.providers.driver.DriverProvider;
import io.techstack.providers.driver.WebDriverWrapper;
import io.techstack.utils.ApiClient;

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

    @Then("Results page with {string} is displayed")
    public void resultsPageWithRequestIsDisplayed(String string) {
        // =============== API USAGE =========================
        User requestUser = new User().setName("Sanya")
                                     .setJob("combiner");

        ApiClient.getRequest("/users/2");
        User user = ApiClient.addUser("/users", requestUser);
        System.out.println(user.toString());
        // ===================================================

        int results = driver.waitForElements(googlePage.getSearchResults(string)).size();

        Assertions.assertThat(results).as("Search results are not valid").isGreaterThan(5);
    }
}
