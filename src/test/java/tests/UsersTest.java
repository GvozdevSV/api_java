package tests;
import config.BaseTest;
import config.Endpoints;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import models.user_models.UserModel;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UsersTest extends BaseTest {
    @Test
    public void testGetUserByID() {
        UserModel response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(Endpoints.USERS_URL + "/3")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(3))
                .extract().as(UserModel.class);

        Assert.assertEquals(response.getData().getId(), 3);
        Assert.assertNotNull(response.getData().getFirst_name(), "В ответе отсутствует Имя");
        Assert.assertNotNull(response.getData().getLast_name(), "В ответе отсутствует Фамилия");
        Assert.assertNotNull(response.getData().getAvatar(), "В ответе отсутствует Аватар");
        Assert.assertNotNull(response.getData().getEmail(), "В ответе отсутствует Электронная почта");
        Assert.assertNotNull(response.getSupport().getText(), "В ответе отсутствует Текст");
        Assert.assertNotNull(response.getSupport().getUrl(), "В ответе отсутствует URL");
    }
}
