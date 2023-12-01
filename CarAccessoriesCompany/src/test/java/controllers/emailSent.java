package controllers;

import classes.Mail;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.mail.MessagingException;

import static org.junit.Assert.assertTrue;

public class emailSent {
    private static final String FROM_EMAIL = "your-email@gmail.com";
   // private static final String PASSWORD = "your-password";

    private String toEmail;
    private String subject;
    private String body;
    Mail em=new Mail();

    @Given("the user is ready to send an email")
    public void theUserIsReadyToSendAnEmail() {

    }
    @When("they send an email with subject {string} and body {string}")
    public void theySendAnEmailWithSubjectAndBody(String string, String string2) throws MessagingException {
         this.subject = string;
         this.body = string2;
         toEmail = "recipient@example.com";
         em.rasheedEmail(FROM_EMAIL);
    }
    @Then("the email should be sent successfully")
    public void theEmailShouldBeSentSuccessfully() {
        assertTrue("Email should be sent successfully", isEmailSentSuccessfully());

    }
    private boolean isEmailSentSuccessfully() {
        try {
            em.rasheedEmail(FROM_EMAIL);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
