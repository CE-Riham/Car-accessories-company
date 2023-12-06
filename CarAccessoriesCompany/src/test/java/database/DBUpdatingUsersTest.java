package database;

import database.updating.UserUpdater;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.User;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class DBUpdatingUsersTest {
    private String condition;
    private String status;
    private DatabaseConnection connection;
    private UserUpdater userUpdater;
    private User user;
    @Before
    @Given("I'm connected to a database to update data")
    public void iMConnectedToADatabaseToUpdateData() {
        user = new User("rihamkatout", "Riham", "Katout", "0599119482", "asAS12!@", "rihamkatm@gmail.com", "rihamkatout.png", "admin", null);
        connection = new DatabaseConnection(3306, "caraccessoriestest", "root", "12345678password");
        userUpdater = new UserUpdater(connection.getCon());
    }
    @When("I fill in update condition with {string}")
    public void iFillInUpdateConditionWith(String condition) {
        this.condition = condition;
    }
    @When("I want to update {string} to {string}")
    public void iWantToUpdateTo(String field, String newValue) {
        if(field.equals("username"))
            user.setUsername(newValue);
        else if(field.equals("firstName"))
            user.setFirstName(newValue);
        else if(field.equals("lastName"))
            user.setLastName(newValue);
        else if(field.equals("phone"))
            user.setPhoneNumber(newValue);
        else if(field.equals("email"))
            user.setEmail(newValue);
        else if(field.equals("userPassword"))
            user.setPassword(newValue);
        else if(field.equals("image"))
            user.setImagePath(newValue);
        else if(field.equals("userType"))
            user.setUserType(newValue);
        else{
            status = "Invalid field";
        }
    }
    @When("I submit to update")
    public void iSubmitToUpdate() {
        userUpdater.updateUserTest(user, condition);
        status = userUpdater.getStatus();
    }
    @Then("I should see {string} for updating data")
    public void iShouldSeeForInsertingData(String message) {
        assertEquals(status, message);
    }
    @After
    @Then("close the connection and end inserting")
    public void closeTheConnectionAndEndInserting() throws SQLException {
        connection.getCon().close();
    }
}
