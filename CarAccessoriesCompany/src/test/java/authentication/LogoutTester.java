package authentication;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class LogoutTester {
    @When("he pressed logout button and confirm it")
    public void hePressedLogoutButtonAndConfirmIt() {
        UserSessionManager.logoutUser();
    }
    @Then("user should see {string} for logout")
    public void userShouldSeeForLogout(String message) {
        assertEquals(message, UserSessionManager.getStatus());
    }
}
