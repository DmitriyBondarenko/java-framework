package io.techstack.steps.app.login;

import io.cucumber.java.en.Given;
import io.techstack.api.RestWebClient;
import io.techstack.dto.UserDto;
import io.techstack.providers.driver.DriverWrapper;
import io.techstack.utils.BrowserList;

public class LoginSteps extends BaseLoginAction {
    private DriverWrapper _driver;
    private final BrowserList _browserList;
    private RestWebClient _webClient;

    public LoginSteps(BrowserList browserList, RestWebClient webClient) {
        super(webClient);
        _driver = browserList.getBrowser();
        _browserList = browserList;
        _webClient = webClient;
    }

    @Given("User is logged in via API")
    public void userIsLoggedInToTheApplication() {
        var user = new UserDto().setLogin("admin").setPassword("m!gration");
        loginViaApiUser(_driver, user);
    }
}
