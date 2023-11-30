package Controllers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class orderProduct {
    private boolean productChosen;
    private boolean orderScreenVisible;
    @When("they choose a product")
    public void theyChooseAProduct() {
        productChosen = true;

    }
    @When("click on the order button")
    public void clickOnTheOrderButton() {
        orderScreenVisible = productChosen;

    }
    @Then("the order screen should be visible")
    public void theOrderScreenShouldBeVisible() {
        assertTrue(orderScreenVisible);

    }
    @When("they do not choose any product")
    public void theyDoNotChooseAnyProduct() {
        productChosen = false;

    }
    @Then("nothing should happen")
    public void nothingShouldHappen() {
        assertFalse(orderScreenVisible);

    }



}
