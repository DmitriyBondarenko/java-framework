package io.techstack.steps.base;

import io.techstack.api.RestWebClient;
import io.techstack.dto.UserDto;
import io.techstack.providers.driver.DriverWrapper;

public class BaseLoginAction {
    private RestWebClient _webClient;
    private UserDto _user;

    public BaseLoginAction(RestWebClient webClient, UserDto user) {
        _webClient = webClient;
        _user = user;
    }

    protected void loginViaApiUser(DriverWrapper driver, UserDto user) {
        var client = new RestWebClient();
    }
}
