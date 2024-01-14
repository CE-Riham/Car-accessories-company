package database.retrieving;

import database.DatabaseConnection;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DBRetrievingProducts {
    private String condition, status;
    private DatabaseConnection connection;
    private RetrievingOrders retrievingData;

    @Before
    @Given("I'm connected to a database to retrieve products")
    public void iMConnectedToADatabase() {
        connection = new DatabaseConnection(3306, "caraccessoriestest", "root", "12345678password");
        retrievingData = new RetrievingOrders(connection.getCon());
    }

    @When("I fill in condition with {string} for products")
    public void iFillInConditionWith(String string) {
        condition = string;
    }

    @When("I want to retrieve {string} products")
    public void iWantToRetrieve(String entity) {
        if (entity.equals("products")) {
            retrievingData.selectProductsWithCondition(condition);
            status = retrievingData.getStatus();
        } else
            status = "Error while retrieving from database";
    }

    @Then("I should see {string} for retrieving products")
    public void iShouldSee(String message) {
        assertEquals(status, message);
    }

    @After
    @Then("close the connection after retrieving products")
    public void closeTheConnection() throws SQLException {
        connection.getCon().close();
    }
}
