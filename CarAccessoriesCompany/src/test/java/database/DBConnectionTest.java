package database;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class DBConnectionTest {
    private String databaseName, username, password;
    private int port;
    private DatabaseConnection testConnection;
    @When("I want to connect to database")
    public void iWantToConnectToDatabase() {
    }
    @When("I fill in {string} with {string}")
    public void iFillInWith(String field, String input) {
        if(field.equals("databaseName"))
            databaseName = input;
        else if (field.equals("username"))
                username = input;
        else if (field.equals("password"))
                password = input;
        else if (field.equals("port"))
            port = Integer.parseInt(input);
        else assert(false);
        assert(true);
    }
    @Then("he should see {string}")
    public void heShouldSee(String message) {
        testConnection = new DatabaseConnection(port, databaseName, username, password);
        String status = (testConnection.isStatus() ?
                "Connected to the database successfully" : "Couldn't connect to the database");
        assertEquals(status, message);

    }

}
