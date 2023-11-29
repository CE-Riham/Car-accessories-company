package database;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DBRetrievingTest {
    private String condition, status;
    private DatabaseConnection connection;
    private RetrievingData retrievingData;

    @BeforeAll
    @Given("I'm connected to a database")
    public void iMConnectedToADatabase() {
       connection = new DatabaseConnection();
       retrievingData = new RetrievingData(connection.getCon());
    }
    @When("I fill in condition with {string}")
    public void iFillInConditionWith(String string) {
        condition = string;
    }
    @When("I want to retrieve {string}")
    public void iWantToRetrieve(String entity) {
        if(entity.equals("users")) {
            retrievingData.selectUsers(condition);
            status = retrievingData.getStatus();
        }
        else if(entity.equals("addresses")){
            retrievingData.selectAddresses(condition);
            status = retrievingData.getStatus();
        }
        else
            status = "Error while retrieving from database";
    }
    @Then("I should see {string} for retrieving data")
    public void iShouldSee(String message) {
        assertEquals(status, message);
    }
    @AfterAll
    @Then("close the connection")
    public void closeTheConnection() throws SQLException {
        connection.getCon().close();
    }
}
