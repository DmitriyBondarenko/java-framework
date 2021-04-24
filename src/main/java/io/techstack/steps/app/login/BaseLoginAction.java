package io.techstack.steps.app.login;

import org.openqa.selenium.Cookie;

import io.restassured.RestAssured;
import io.techstack.api.RestWebClient;
import io.techstack.dto.UserDto;
import io.techstack.providers.UrlProvider;
import io.techstack.providers.api.RestAssuredProvider;
import io.techstack.providers.driver.DriverWrapper;

public class BaseLoginAction {
    private RestWebClient _webClient;

    public BaseLoginAction(RestWebClient webClient) {
        _webClient = webClient;
    }

    protected void loginViaApiUser(DriverWrapper driver, UserDto user) {
        try {
            var response = RestAssured.given(RestAssuredProvider.getSpecification(UrlProvider.REST_CLIENT_BASE_URL))
                                      .contentType("multipart/form-data;")
                                      .queryParam("username", user.getLogin())
                                      .queryParam("password", user.getPassword())
                                      .post("/authentication/login");
            var cookies = response.getDetailedCookies();

            driver.navigateToUrl(UrlProvider.URL);

            for (var cookie : cookies) {
                Cookie driverCookie = new Cookie(cookie.getName(), cookie.getValue());
                driver.manage().addCookie(driverCookie);
            }
        } catch (Exception ex) {
            throw new RuntimeException(String.format("Unable to Login via API: %s", ex.getMessage()));
        }
    }
}
