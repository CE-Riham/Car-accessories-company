package authentication;

import Classes.Starter;
import database.DatabaseConnection;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class LoginTester {
    private String status, username, password;
    private DatabaseConnection connection;
    private Login login;

    @BeforeAll
    @Given("user is connected to the database")
    public void userIsConnectedToTheDatabase() {
        connection = new DatabaseConnection();
        login = new Login(connection.getCon());
    }
    @When("he fills in {string} with {string}")
    public void heFillsInWith(String field, String input) {
        if(field.equals("username"))
                username = input;
        else
            password = input;
    }
    @When("user clicks on login")
    public void userClicksOnLogin() {
        login.loginUser(username, password);
        status = login.getStatus();
    }
    @Then("user should see {string}")
    public void userShouldSee(String message) {
        assertEquals(status, message);
    }
    @AfterAll
    @Then("close the connection")
    public void closeTheConnection() throws SQLException {
        connection.getCon().close();
    }
}
