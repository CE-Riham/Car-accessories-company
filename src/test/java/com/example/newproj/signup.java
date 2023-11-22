package com.example.newproj;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class signup {
    @When("I click on sign up and flag is {string}")
    public void iClickOnSignUpAndFlagIs(String string) {
        assertEquals(true, string.equals("true"));

    }
    @When("he presses {string} and flag is {string}")
    public void hePressesAndFlagIs(String string, String string2) {
        assertEquals(true,string2.equals("true"));
    }
    @Then("there should be a user {string}")
    public void thereShouldBeAUser(String string) {
        assertEquals(true,string.equals("shahd28"));

    }
    @Then("the user should see {string}")
    public void theUserShouldSee(String string) {
        assertEquals(true, !string.equals("0"));
    }



}
