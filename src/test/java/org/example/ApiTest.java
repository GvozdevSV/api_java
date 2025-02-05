package org.example;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    @Test
    public void testGetUser() {
        RestAssured.baseURI = "https://reqres.in/api";

        given()
                .when().get("/users/2")
                .then().statusCode(200)
                .body("data.id", equalTo(2));
    }
}


