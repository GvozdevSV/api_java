package tests;
import config.BaseTest;
import config.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.user_models.CreateUserModel;
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

    @Test
    public void testCreateUser() {
        String requestBody = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        CreateUserModel response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(Endpoints.USERS_URL)
                .then()
                .statusCode(201)
                .extract().as(CreateUserModel.class);

        Assert.assertNotNull(response.getId(), "В ответе нет id");
        Assert.assertEquals(response.getName(), "morpheus", "Не корректное имя пользователя");
        Assert.assertEquals(response.getJob(), "leader", "Не корректная работа");
        Assert.assertNotNull(response.getCreatedAt(), "В ответе отсутствует Дата создания");
    }

    @Test
    public void testDeleteUserByID() {

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete(Endpoints.USERS_URL + "/2")
                .then()
                .statusCode(204)
                .extract().response();

        Assert.assertEquals(response.statusCode(), 204, "Не корректный статус код при удалении пользователя");
    }
}
