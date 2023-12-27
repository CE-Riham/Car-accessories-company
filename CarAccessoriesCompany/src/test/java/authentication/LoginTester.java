package authentication;

import database.DatabaseConnection;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class LoginTester {

    private String status, username, password;
    private DatabaseConnection connection;
    private Login login;

    @Given("user is connected to the database")
    public void userIsConnectedToTheDatabase() {
        connection = new DatabaseConnection(3306, "caraccessoriescompany", "root", "12345678password");
        login = new Login(connection.getCon());
    }

    @When("he fills in {string} with {string} for login")
    public void heFillsInWith(String field, String input) {
        if(field.equals("username"))
            username = input;
        else
            password = input;
    }
    @When("user clicks on login")
    public void userClicksOnLogin(){
        login.loginUser(username, password);
        status = login.getStatus();
    }
    @Then("user should see {string} for login")
    public void userShouldSee(String message) {
        assertEquals(status,message);
    }
    @Then("close the connection")
    public void closeTheConnection() throws SQLException {
        connection.getCon().close();
    }
}
