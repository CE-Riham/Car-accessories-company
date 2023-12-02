package database.updating;

import classes.Starter;
import helpers.DataValidation;
import helpers.Generator;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserUpdater{
    private String status;
    private Connection connection;
    public UserUpdater(Connection connection){
        this.connection = connection;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean updateUserTest(User user, String condition){
        Starter.logger.info(condition);
        String st = DataValidation.userValidationTest(user);
        if(st.equals("Valid"))
            setStatus("User was updated successfully");
        else
            setStatus(st);
        return false;
    }
    public boolean updateUsersAllFields(User user, String condition){
        String st = DataValidation.userValidationTest(user);
        if(st.equals("Valid")){
            try {
                String query = "UPDATE users SET firstName = ?, lastName = ?, username = ?, " +
                        "phone = ?, email = ?, userPassword = ?, image = ?, userType = ? " + condition;
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt = Generator.userToPS(preparedStmt, user);
                preparedStmt.execute();
                setStatus("User was updated successfully");
                return true;
            } catch (Exception e) {
                setStatus("Couldn't update user");
                return false;
            }
        }
        else
            setStatus(st);
        return false;
    }
    public boolean updateUserFirstName(String newFirstName, String condition){
        try {
            String query = "UPDATE users SET firstName = ? " + condition;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, newFirstName);
            preparedStmt.execute();
            setStatus("User firstName was updated successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update user firstName");
            return false;
        }
    }
    public boolean updateUserLastName(String newLastName, String condition){
        try {
            String query = "UPDATE users SET lastName = ? " + condition;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, newLastName);
            preparedStmt.execute();
            setStatus("User lastName was updated successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update user lastName");
            return false;
        }
    }
    public boolean updateUserPhone(String newPhoneNumber, String condition){
        try {
            String query = "UPDATE users SET phone = ? " + condition;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, newPhoneNumber);
            preparedStmt.execute();
            setStatus("User phone was updated successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update user phone");
            return false;
        }
    }
    public boolean updateUserEmail(String newEmail, String condition){
        try {
            String query = "UPDATE users SET email = ? " + condition;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, newEmail);
            preparedStmt.execute();
            setStatus("User email was updated successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update user email");
            return false;
        }
    }

    public boolean updateUserPassword(String newPassword, String condition){
        try {
            String query = "UPDATE users SET userPassword = ? " + condition;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, newPassword);
            preparedStmt.execute();
            setStatus("User password was updated successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update user password");
            return false;
        }
    }

    public boolean updateUserImage(String newImage, String condition){
        try {
            String query = "UPDATE users SET image = ? " + condition;
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, newImage);
            preparedStmt.execute();
            setStatus("User image was updated successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't update user image");
            return false;
        }
    }

}
