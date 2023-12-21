package database.retrieving;

import database.DatabaseConnection;
import database.retrieving.RetrievingUser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DBRetrievingUsersTest {
    private String condition;
    private String status;
    private DatabaseConnection connection;
    private RetrievingUser retrievingData;

    @Before
    @Given("I'm connected to a database to retrieve users")
    public void iMConnectedToADatabase() {
       connection = new DatabaseConnection(3306, "caraccessoriestest", "root", "12345678password");
       retrievingData = new RetrievingUser(connection.getCon());
    }
    @When("I fill in condition with {string} for users")
    public void iFillInConditionWith(String string) {
        condition = string;
    }
    @When("I want to retrieve {string} users")
    public void iWantToRetrieve(String entity) throws SQLException {
        if(entity.equals("users")) {
            retrievingData.selectUsersWithCondition(condition);
            status = retrievingData.getStatus();
        }
        else
            status = "Error while retrieving from database";
    }
    @Then("I should see {string} for retrieving users")
    public void iShouldSee(String message) {
        assertEquals(status, message);
    }
    @After
    @Then("close the connection after retrieving users")
    public void closeTheConnection() throws SQLException {
        connection.getCon().close();
    }
}
