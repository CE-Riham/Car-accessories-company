package model.installation;

import model.Address;
import model.User;

import java.sql.Time;
import java.util.List;

public class Installer extends User {
    private double pricePerHour;
    private boolean available;
    private Time installationStartHour;
    private Time installationEndHour;
    private String[] days;

    public double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Time getInstallationStartHour() {
        return installationStartHour;
    }

    public void setInstallationStartHour(Time installationStartHour) {
        this.installationStartHour = installationStartHour;
    }

    public Time getInstallationEndHour() {
        return installationEndHour;
    }

    public void setInstallationEndHour(Time installationEndHour) {
        this.installationEndHour = installationEndHour;
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = new String[days.length];
        System.arraycopy(days, 0, this.days, 0, days.length);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean worksOnDay(String day) {
        for (String d : days)
            if (d.equals(day))
                return true;
        return false;
    }

    public boolean worksAtTime(Time time) {
        return time.compareTo(installationStartHour) >= 0 && time.compareTo(installationEndHour) <= 0;
    }

    public void addListDays(List<String> daysList){
        days = new String[daysList.size()];
        for (int i = 0; i < daysList.size(); i++)
            this.days[i] = daysList.get(i);
    }
    public void addUserInformation(User user){
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setPhoneNumber(user.getPhoneNumber());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setImagePath(user.getImagePath());
    }
    public Installer() {
        super();
        setInstallationStartHour(Time.valueOf("00:00:00"));
        setInstallationEndHour(Time.valueOf("00:00:00"));
        setDays(new String[0]);
    }

    public Installer(String username, String firstName, String lastName,
                     String phoneNumber, String password, String email,
                     String imagePath, Address address, double pricePerHour, boolean available, Time installationStartHour, Time installationEndHour, String... days) {
        super(username, firstName, lastName, phoneNumber, password, email, imagePath, "installer", address);
        setPricePerHour(pricePerHour);
        setAvailable(available);
        setInstallationStartHour(installationStartHour);
        setInstallationEndHour(installationEndHour);
        setDays(days);
    }

    public Installer(User user, double pricePerHour, boolean available, Time installationStartHour, Time installationEndHour, String... days) {
        super(user);
        setPricePerHour(pricePerHour);
        setAvailable(available);
        setInstallationStartHour(installationStartHour);
        setInstallationEndHour(installationEndHour);
        setDays(days);
    }
}
