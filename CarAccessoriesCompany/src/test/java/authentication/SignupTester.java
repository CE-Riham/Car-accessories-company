package authentication;

import database.DatabaseConnection;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.User;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignupTester {
    private Register userRegisterer;
    private User user;
    private DatabaseConnection connection;
    private String status;

    @Before
    @When("user is in sign-up page")
    public void userIsOnTheSignUpPage() {
        connection = new DatabaseConnection(3306, "caraccessoriestest", "root", "12345678password");
        userRegisterer = new Register(connection.getCon());
        user = new User();
    }
    @When("he fills in {string} with {string} for register")
    public void heFillsInWithForRegister(String field, String input) {
        if(field.equals("username"))
            user.setUsername(input);
        else if(field.equals("firstName"))
            user.setFirstName(input);
        else if(field.equals("lastName"))
            user.setLastName(input);
        else if(field.equals("phoneNumber"))
            user.setPhoneNumber(input);
        else if(field.equals("password"))
            user.setPassword(input);
        else if(field.equals("email"))
            user.setEmail(input);
        else if(field.equals("userType"))
            user.setUserType(input);
        else
            assert(false);
        assert(true);
    }

    @When("he submits the registration form")
    public void heSubmitsTheRegistrationForm() throws SQLException {
        userRegisterer.registerUser(user);
        status = userRegisterer.getStatus();
    }
    @Then("he should see {string} for register")
    public void heShouldSee(String message) {
        assertEquals(message, status);
    }

}
