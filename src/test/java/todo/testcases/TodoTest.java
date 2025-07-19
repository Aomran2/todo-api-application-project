package todo.testcases;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import todo.apis.TodoApis;
import todo.datagenerator.UserDataGenerator;
import todo.models.Todo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TodoTest {



    @Test
    public void shouldBeAbleToAddTodo() {
        String token = UserDataGenerator.getUserToken();
        Todo todo = new Todo( false, "Learn Appium");

        Response response = TodoApis.addTodo(todo,token);

        Todo returnedTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(), equalTo(201));
        assertThat(returnedTodo.getItem(), equalTo(todo.getItem()));
        assertThat(returnedTodo.getIsCompleted(), equalTo(todo.getIsCompleted()));
    }

    @Test
    public void shouldNotBeAbleToAddTodoIfItemIsMissing() {
        String token = UserDataGenerator.getUserToken();

        Todo todo = new Todo( false);

        Response response = TodoApis.addTodo(todo,token);

        Error retrunedError = response.body().as(Error.class);

        assertThat(response.statusCode(), equalTo(400));
        assertThat(retrunedError.getMessage(), equalTo("\"item\" is required"));
    }

    @Test
    public void shouldNotBeAbleToAddTodoIfIsCompletedIsMissing() {
        String token = UserDataGenerator.getUserToken();

        Todo todo = new Todo("Learn Appium");

        Response response = TodoApis.addTodo(todo,token);

        Error retrunedError = response.body().as(Error.class);

        assertThat(response.statusCode(), equalTo(400));
        assertThat(retrunedError.getMessage(), equalTo("\"isCompleted\" is required"));
    }

    @Test
    public void shouldBeAbleToGetTodoByID() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY4N2JjOThmZDJiOTE0MDAxNDg2YmM4ZSIsImZpcnN0TmFtZSI6IlNoYWxhIiwibGFzdE5hbWUiOiJCaW5zIiwiaWF0IjoxNzUyOTQyOTkxfQ.2hSMlvFhcgE9hJhr7v4WYJju06hsTU_GlboIJ1VPPnM";

        String taskId = "687bc991d2b914001486bc90";

        Response response = TodoApis.getTodo(taskId,token);

        Todo returnedTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(), equalTo(200));
        assertThat(returnedTodo.getId(), equalTo("687bc991d2b914001486bc90"));
    }

    @Test
    public void shouldBeAbleToDeleteTodo() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY4N2JjOThmZDJiOTE0MDAxNDg2YmM4ZSIsImZpcnN0TmFtZSI6IlNoYWxhIiwibGFzdE5hbWUiOiJCaW5zIiwiaWF0IjoxNzUyOTQyOTkxfQ.2hSMlvFhcgE9hJhr7v4WYJju06hsTU_GlboIJ1VPPnM";

        String taskId = "687bc991d2b914001486bc90";

        Response response = TodoApis.deleteTodo(taskId,token);

        Todo returnedTodo = response.body().as(Todo.class);

        assertThat(response.statusCode(), equalTo(200));
        assertThat(returnedTodo.getItem(), equalTo("Learn Appium"));
    }


}
