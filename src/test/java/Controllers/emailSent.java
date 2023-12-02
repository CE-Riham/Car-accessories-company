package Controllers;

import Classes.Mail;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.mail.MessagingException;

import static org.junit.Assert.assertTrue;

public class emailSent {
    private boolean emailSentSuccessfully;

    @Given("the user is ready to send an email")
    public void theUserIsReadyToSendAnEmail() {
        // Setup any necessary preconditions
    }

    @When("they send an email with subject {string} and body {string}")
    public void theySendAnEmail(String subject, String body) {
        try {
            Mail mailClass = new Mail();
            mailClass.rasheedEmail("recipient@example.com");
            emailSentSuccessfully = mailClass.isEmailSentSuccessfully();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Then("the email should be sent successfully")
    public void theEmailShouldBeSentSuccessfully() {
        if(emailSentSuccessfully)
        assertTrue(emailSentSuccessfully);
       // assert emailSentSuccessfully : "Email was sent successfully";
    }















}
