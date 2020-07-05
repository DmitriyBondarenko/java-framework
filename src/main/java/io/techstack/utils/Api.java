package io.techstack.utils;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.techstack.dto.User;
import io.techstack.properties.PropertyReader;

public class Api {

    private static RequestSpecification requestSpecification;

    static {
        RestAssured.baseURI = PropertyReader.getProperty("base.uri");
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter()).build();
    }

    public static void getRequest(String endpoint) {
        given().spec(requestSpecification)
                .request().get(endpoint)
                .then().assertThat().statusCode(200);
    }

    public static User addUser(String endpoint, User user) {
        return given().spec(requestSpecification)
                .body(user)
                .request().post(endpoint)
                .then().assertThat().statusCode(201)
                .extract().as(User.class);
    }
}
