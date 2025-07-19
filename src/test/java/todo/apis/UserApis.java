package todo.apis;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import todo.models.User;
import static io.restassured.RestAssured.given;

public class UserApis {

    public static Response register(User user) {
        String baseUri = "https://qacart-todo.herokuapp.com";
        String registerEndpoint = "/api/v1/users/register";

        return   given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .body(user)
                .when().post(registerEndpoint)
                .then().log().all()
                .extract().response();
    }

    public static Response login(User user){

        String baseUri = "https://qacart-todo.herokuapp.com";
        String loginEndpoint = "/api/v1/users/login";

        return given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .body(user)
                .when().post(loginEndpoint)
                .then().log().all()
                .extract().response();

    }
}
