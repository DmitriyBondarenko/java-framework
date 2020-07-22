package io.techstack.utils;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.techstack.dto.UserDto;

/**
 * This class provides ability to interact with API REST service
 */
public class ApiHelper {
    private static final RequestSpecification requestSpecification;

    static {
        RestAssured.baseURI = PropertyReader.getProperty("base.uri");
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    public static void getRequest(String endpoint) {
        given().spec(requestSpecification)
               .request()
               .get(endpoint)
               .then()
               .assertThat()
               .statusCode(200);
    }

    public static UserDto addUser(String endpoint, UserDto user) {
        return given().spec(requestSpecification)
                      .body(user)
                      .when()
                      .post(endpoint)
                      .then()
                      .assertThat()
                      .statusCode(201)
                      .and()
                      .extract()
                      .as(UserDto.class);
    }
}
