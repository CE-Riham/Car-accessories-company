package Controllers;

import database.InsertingData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.installReq;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class installRequest {
    private installReq installationRequest=new installReq();
    private Connection connection;
    private List<LocalDate> availableTimeSlots;
    private InsertingData userInserter=new InsertingData(connection);
    private List<installReq> upcomingInstallations;

    @Given("the customer wants to request installation services")
    public void theCustomerWantsToRequestInstallationServices() {
        installationRequest = new installReq(connection);

    }
    @When("they fill out the installation request form")
    public void theyFillOutTheInstallationRequestForm() {
        installationRequest.setUserid(1);
        installationRequest.setProductid(2);

    }
    @When("provide details such as car make\\/model and preferred date")
    public void provideDetailsSuchAsCarMakeModelAndPreferredDate() {
        installationRequest.setCarMakeModel(2003);
        installationRequest.setPreferredData(LocalDate.now());
    }
    @Then("the system should validate and store the installation request")
    public void theSystemShouldValidateAndStoreTheInstallationRequest() {
        installationRequest.validateAndStore();
    }
    @Given("the customer has submitted an installation request")
    public void theCustomerHasSubmittedAnInstallationRequest() {
        installationRequest = new installReq(connection);
        installationRequest.setUserid(1);
        installationRequest.setProductid(2);
        installationRequest.setCarMakeModel(2001);
        installationRequest.setPreferredData(LocalDate.now().plusDays(7));
        installationRequest.validateAndStore();
    }
    @When("the system checks for installer availability")
    public void theSystemChecksForInstallerAvailability() {
        availableTimeSlots = installationRequest.checkInstallerAvailability();

    }
    @Then("the system should provide available time slots")
    public void theSystemShouldProvideAvailableTimeSlots() {
        availableTimeSlots = installationRequest.getAvailableTimeSlots();
    }
    @Given("the customer has received available time slots")
    public void theCustomerHasReceivedAvailableTimeSlots() {
        installationRequest = new installReq();

    }
    @When("they select a preferred appointment time")
    public void theySelectAPreferredAppointmentTime() {
        LocalDate preferredTime = LocalDate.now().plusDays(7);
        installationRequest.setPreferredData(preferredTime);
    }
    @Then("the system should schedule the installation appointment")
    public void theSystemShouldScheduleTheInstallationAppointment() {
        boolean isScheduled = scheduleInstallation(installationRequest);
        assert(isScheduled);
    }
    @Given("the customer wants to view their scheduled installations")
    public void theCustomerWantsToViewTheirScheduledInstallations() {
        int customerId = 123;
        installationRequest = new installReq();
        installationRequest.setUserid(customerId);
    }
    @When("they access the scheduled installations section")
    public void theyAccessTheScheduledInstallationsSection() {
        upcomingInstallations = retrieveUpcomingInstallations(installationRequest.getUserid());

    }
    @Then("the system should display a list of upcoming installations")
    public void theSystemShouldDisplayAListOfUpcomingInstallations() {
        assertEquals(false, upcomingInstallations.isEmpty());
        System.out.println("Upcoming Installations: " + upcomingInstallations);

    }
    private List<installReq> retrieveUpcomingInstallations(int customerId) {
        List<installReq> installations = new ArrayList<>();
        installations.add(new installReq(customerId, 1, 2, LocalDate.now().plusDays(1)));
        installations.add(new installReq(customerId, 3, 4, LocalDate.now().plusDays(3)));

        return installations;
    }

    private boolean scheduleInstallation(installReq request) {
        try {
            userInserter.insertInstallationRequest(request);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
