package todo.datagenerator;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import todo.apis.UserApis;
import todo.models.User;

public class UserDataGenerator {

    public static User generateUser() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = "12345678";

        User user = new User(firstName,lastName,email,password);
        return user;
    }

    public static User getRegisteredUser() {
        User user = generateUser();
        UserApis.register(user);
        return user;
    }

    public static String getUserToken() {
        User user = generateUser();
        Response response = UserApis.register(user);
        return response.body().path("access_token");
    }
}
