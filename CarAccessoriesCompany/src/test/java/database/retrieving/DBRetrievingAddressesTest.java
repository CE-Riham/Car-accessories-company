package database.retrieving;

import database.DatabaseConnection;
import database.retrieving.RetrievingAddress;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DBRetrievingAddressesTest {
    private String condition, status;
    private DatabaseConnection connection;
    private RetrievingAddress retrievingData;

    @Before
    @Given("I'm connected to a database to retrieve addresses")
    public void iMConnectedToADatabase() {
       connection = new DatabaseConnection(3306, "caraccessoriestest", "root", "12345678password");
       retrievingData = new RetrievingAddress(connection.getCon());
    }
    @When("I fill in condition with {string} for addresses")
    public void iFillInConditionWith(String string) {
        condition = string;
    }
    @When("I want to retrieve {string} addresses")
    public void iWantToRetrieve(String entity) throws SQLException {
        if(entity.equals("addresses")){
            retrievingData.selectAddressesWithCondition(condition);
            status = retrievingData.getStatus();
        }
        else
            status = "Error while retrieving from database";
    }
    @Then("I should see {string} for retrieving addresses")
    public void iShouldSee(String message) {
        assertEquals(status, message);
    }
    @After
    @Then("close the connection after retrieving addresses")
    public void closeTheConnection() throws SQLException {
        connection.getCon().close();
    }
}
