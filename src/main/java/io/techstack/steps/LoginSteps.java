package io.techstack.steps;

import io.cucumber.java.en.Given;
import io.techstack.api.RestWebClient;
import io.techstack.dto.UserDto;
import io.techstack.providers.driver.DriverWrapper;
import io.techstack.steps.base.BaseLoginAction;
import io.techstack.utils.BrowserList;

public class LoginSteps extends BaseLoginAction {
    private DriverWrapper _driver;
    private final BrowserList _browserList;
    private final UserDto _user;

    public LoginSteps(BrowserList browserList, UserDto userDto, RestWebClient webClient) {
        super(webClient, userDto);
        _driver = browserList.getBrowser();
        _browserList = browserList;
        _user = userDto;
    }

    @Given("User is logged in via API")
    public void userIsLoggedInToTheApplication() {
        loginViaApiUser(_driver, _user);
    }
}
