package model.installation;

import java.sql.Time;
import java.time.LocalDate;

public class InstallationRequest {
    private int installationRequestID;
    private String customerUsername;
    private String carModel;

    private LocalDate preferredDate;
    private Time preferredTime;

    public int getInstallationRequestID() {
        return installationRequestID;
    }

    public void setInstallationRequestID(int installationRequestID) {
        this.installationRequestID = installationRequestID;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public LocalDate getPreferredDate() {
        return preferredDate;
    }

    public void setPreferredDate(LocalDate preferredDate) {
        this.preferredDate = preferredDate;
    }

    public Time getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(Time preferredTime) {
        this.preferredTime = preferredTime;
    }

    public InstallationRequest() {
        preferredDate = LocalDate.of(1, 1, 1);
        preferredTime = Time.valueOf("00:00:00");
    }

    public InstallationRequest(int installationRequestID, String customerUsername, String carModel, LocalDate preferredDate, Time preferredTime) {
        this.installationRequestID = installationRequestID;
        this.customerUsername = customerUsername;
        this.carModel = carModel;
        this.preferredDate = preferredDate;
        this.preferredTime = preferredTime;
    }

}
