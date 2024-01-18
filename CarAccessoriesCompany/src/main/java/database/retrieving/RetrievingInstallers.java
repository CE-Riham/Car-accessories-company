package database.retrieving;

import classes.DBConnector;
import classes.Starter;
import helpers.Generator;
import model.installation.Installer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RetrievingInstallers {
    private Connection con;
    private String status;

    public RetrievingInstallers(Connection con) {
        this.con = con;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    private List<String> selectInstallerWorkingDaysFromDB(String username){
        List<String> days = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM installerWorkingDays where username = \'").append(username).append("\'");
        Statement st = null;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query.toString());
            while (rs != null && rs.next())
                days.add(rs.getString("workingDay"));

            setStatus("Retrieving days successfully");
            return days;
        } catch (Exception e) {
            setStatus("Error while retrieving days from database");
            return new ArrayList<>();
        } finally {
            try {
                if (st != null) st.close();
            } catch (Exception e) {
                Starter.logger.warning("can't close statement");
            }
        }
    }

    public List<Installer> selectInstallersWithCondition(String condition) {
        List<Installer> installers = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM installers ").append(condition);
        Statement st = null;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query.toString());
            while (rs != null && rs.next()) {
                Installer tmpInstaller = Generator.rsToInstaller(rs);
                tmpInstaller.addUserInformation(new RetrievingUser(DBConnector.getConnector().getCon()).findUserByUsername(tmpInstaller.getUsername()).get(0));
                tmpInstaller.addListDays(selectInstallerWorkingDaysFromDB(tmpInstaller.getUsername()));
                installers.add(tmpInstaller);
            }
            setStatus("Retrieving installers successfully");
            return installers;
        } catch (Exception e) {
            setStatus("Error while retrieving installers from database");
            return new ArrayList<>();
        } finally {
            try {
                if (st != null) st.close();
            } catch (Exception e) {
                Starter.logger.warning("can't close statement");
            }
        }
    }

    public List<Installer> selectAllInstallers() {
        return selectInstallersWithCondition(";");
    }

    public List<Installer> selectFromInstallerTable(String field, String input) {
        if(field.equals("pricePerHour"))
            return selectInstallersWithCondition("where " + field + " <= \'" + input + "\';");
        return selectInstallersWithCondition("where " + field + " = \'" + input + "\';");
    }

    public List<Installer> findInstallerByUsername(String username) {
        return selectFromInstallerTable("username", username);
    }

    public List<Installer> findInstallersByPricePerHourLessThanOrEqual(double price) {
        return selectFromInstallerTable("pricePerHour", String.valueOf(price));
    }

    public List<Installer> findInstallerByAvailability(String available) {
        return selectFromInstallerTable("available", available);
    }
}