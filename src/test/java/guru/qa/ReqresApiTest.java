package guru.qa;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;

public class ReqresApiTest extends TestBase {

    @Test
    @DisplayName("Проверка успешного создания пользователя")
    void createUserTest() {
        JSONObject requestBody = new JSONObject()
                .put("name", "Alexander")
                .put("job", "qa engineer");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .post("/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_201)
                .body("name", is("Alexander"))
                .body("job", is("qa engineer"));
    }

    @Test
    @DisplayName("Проверка успешной авторизации пользователя")
    void successfulLoginUserTest() {
        JSONObject requestBody = new JSONObject()
                .put("email", "eve.holt@reqres.in")
                .put("password", "cityslicka");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .post("/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Проверка неуспешной авторизации пользователя")
    void unsuccessfulLoginUserTest() {
        JSONObject requestBody = new JSONObject()
                .put("email", "peter@klaven");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .post("/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_400)
                .body("error", is("Missing password"));
    }

    @Test
    @DisplayName("Проверка успешной регистрации пользователя")
    void successfulRegisterUserTest() {
        JSONObject requestBody = new JSONObject()
                .put("email", "eve.holt@reqres.in")
                .put("password", "pistol");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .post("/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Проверка неуспешной регистрации пользователя")
    void unsuccessfulRegisterUserTest() {
        JSONObject requestBody = new JSONObject()
                .put("email", "sydney@fife");

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(requestBody.toString())
                .when()
                .post("/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(STATUS_CODE_400)
                .body("error", is("Missing password"));
    }
}
