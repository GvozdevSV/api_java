package tests;
import config.BaseTest;
import config.Endpoints;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UsersTest extends BaseTest {
    @Test
    public void testGetUserByID() {
        Response response = given()
                .when()
                .get(Endpoints.USERS_URL + "/3")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(3))
                .extract().response();

        Assert.assertEquals(response.jsonPath().getInt("data.id"), 3);
    }
}
