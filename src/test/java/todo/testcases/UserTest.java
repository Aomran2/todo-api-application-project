package todo.testcases;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import todo.apis.UserApis;
import todo.datagenerator.UserDataGenerator;
import todo.models.User;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class UserTest {



    @Test
    public void shouldBeAbleToRegister() {
//        User user = new User("Jack", "Doe", "Jack.Doe5@example.com", "12345678");
        User user = UserDataGenerator.generateUser();

//        String registerBody = "{\n" +
//                "  \"firstName\": \"John\",\n" +
//                "  \"lastName\": \"Doe\",\n" +
//                "  \"email\": \"John.Doe@example.com\",\n" +
//                "  \"password\": \"12345678\"\n" +
//                "}";

//        Response response = given()
//                .baseUri(baseUri)
//                .contentType(ContentType.JSON)
//                .body(user)
//                .when().post(registerEndpoint)
//                .then().log().all()
//                .extract().response();

        //calling the register api from userApis class
        Response response = UserApis.register(user);

        //Deserialize the Json response
        User returnedUser = response.body().as(User.class);

        assertThat(response.statusCode(), equalTo(201));
//        assertThat(response.path("firstName"), equalTo("Jack"));
        assertThat(returnedUser.getFirstName(), equalTo(user.getFirstName()));
    }

    @Test
    public void shouldNotBeAbleToRegisterWithSameEmail() {
        User user = UserDataGenerator.getRegisteredUser();
        Response response = UserApis.register(user);

        Error returnedMessage = response.body().as(Error.class);

        assertThat(response.statusCode(), equalTo(400));
        assertThat(returnedMessage.getMessage(), equalTo("Email is already exists in the Database"));
    }

    @Test
    public void shouldBeAbleToLogin() {

        User user = UserDataGenerator.getRegisteredUser();
        User loginData = new User(user.getEmail(), user.getPassword());

        Response response = UserApis.login(loginData);

        User returnedUser = response.body().as(User.class);

        assertThat(response.statusCode(), equalTo(200));
        assertThat(returnedUser.getAccessToken(), not(equalTo(null)));
        assertThat(returnedUser.getFirstName(), equalTo(user.getFirstName()));
    }

    @Test
    public void shouldNotBeAbleToLoginIfThePasswordIncorrect() {

//        User user = new User("Jack.Doe3@example.com", "1234567");
        User user = UserDataGenerator.getRegisteredUser();
        User loginData = new User(user.getEmail(), "wrongwrong");

        Response response = UserApis.login(loginData);

        Error returnedMessage = response.body().as(Error.class);

        assertThat(response.statusCode(), equalTo(401));
        assertThat(returnedMessage.getMessage(), equalTo("The email and password combination is not correct, please fill a correct email and password"));
    }
}
