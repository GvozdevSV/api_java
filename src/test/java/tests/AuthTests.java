package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import config.Endpoints;

import config.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AuthTests extends BaseTest {


    @Test
    public void testSuccessfulLogin() {
        String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(Endpoints.LOGIN_URL)
                .then()
                .statusCode(200)
                .body("token", notNullValue()) // Проверяем, что токен есть
                .extract().response();

        String token = response.jsonPath().getString("token");
        Assert.assertNotNull(token, "В ответе токен имеет значение null");
    }
}
