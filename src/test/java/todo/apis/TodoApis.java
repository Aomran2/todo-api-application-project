package todo.apis;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import todo.models.Todo;

import static io.restassured.RestAssured.given;

public class TodoApis {

    public static Response addTodo(Todo todo, String token) {

        String baseUri = "https://qacart-todo.herokuapp.com";
        String tasksEndpoint = "/api/v1/tasks";

        return given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body(todo)
                .when().post(tasksEndpoint)
                .then().log().all()
                .extract().response();
    }

    public static Response getTodo(String taskId,String token) {

        String baseUri = "https://qacart-todo.herokuapp.com";

        return given()
                .baseUri(baseUri)
                .auth().oauth2(token)
                .when().get("/api/v1/tasks/" + taskId)
                .then().log().all()
                .extract().response();
    }

    public static Response deleteTodo(String taskId, String token) {
        String baseUri = "https://qacart-todo.herokuapp.com";
        return  given()
                .baseUri(baseUri)
                .auth().oauth2(token)
                .when().delete("/api/v1/tasks/" + taskId)
                .then().log().all()
                .extract().response();
    }
}
