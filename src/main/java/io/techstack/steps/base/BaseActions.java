package io.techstack.steps.base;

import org.assertj.core.api.Assertions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.techstack.api.RestWebClient;
import io.techstack.providers.UrlProvider;
import io.techstack.providers.driver.DriverProvider;
import io.techstack.providers.driver.DriverWrapper;
import io.techstack.utils.BrowserList;

public class BaseActions {
    private DriverWrapper _driver;
    private final BrowserList _browserList;

    public BaseActions(BrowserList browserList, RestWebClient webClient) {
        _driver = browserList.getBrowser();
        _browserList = browserList;
    }

    //region Browser actions
    @When("User clicks back button in the browser")
    public void whenUserClickBackButtonInTheBrowser() {
        _driver.navigate().back();
    }

    @When("User clicks refresh button in the browser")
    public void whenUserClicksRefreshButtonInTheBrowser() {
        _driver.navigate().refresh();
    }

    @When("User waits for {int} seconds")
    public void whenUserWaitsForSeconds(int seconds) throws InterruptedException {
        if (seconds > 10) {
            throw new RuntimeException("Unable to wait longer than 10 seconds");
        }
        Thread.sleep(seconds * 1000L);
    }

    @When("User switches to previous tab")
    public void whenUserSwitchesToPreviousTab() {
        _driver.switchTo().window(_driver.getWindowHandles()
                              .stream()
                              .findFirst()
                              .get());
    }

    @When("User switches to the last tab")
    public void whenUserSwitchesToTheLastTab() {
        _driver.switchTo().window(_driver.getWindowHandles()
                              .stream()
                              .reduce((first, second) -> second)
                              .get());
    }

    @Given("User creates new browser")
    public void givenUserCreatesNewBrowser() {
        _browserList.addDriver(new DriverProvider().createDriverInstance());
        _driver = _browserList.getBrowser(1);
    }
    //endregion

    //region Check/Navigate to URL
    @When("User navigates to {string} url via address line")
    public void whenUserNavigatesToUrlViaAddressLine(String url) {
        var navigationUrl = String.format("%s%s", UrlProvider.URL, url);
        _driver.navigateToUrl(navigationUrl);
    }

    @Then("URL is {string}")
    public void thenUrlIs(String urlPart) {
        var expectedUrl = String.format("%s%s", UrlProvider.URL, urlPart);
        Assertions.assertThat(expectedUrl)
                  .as(String.format("URL is not equals to '%s'", expectedUrl))
                  .isEqualTo(_driver.getCurrentUrl());
    }

    @Then("URL contains {string}")
    public void thenUrlContains(String url) {
        Assertions.assertThat(_driver.getCurrentUrl())
                  .as(String.format("URL doesn't contain '%s'", url))
                  .contains(url);

    }

    @Then("URL does not contain {string}")
    public void thenUrlDoesNotContain(String url) {
        Assertions.assertThat(_driver.getCurrentUrl())
                  .as(String.format("URL contains '%s'", url))
                  .doesNotContain(url);
    }
    //endregion
}
