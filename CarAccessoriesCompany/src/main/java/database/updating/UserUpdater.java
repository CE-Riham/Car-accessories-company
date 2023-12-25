package database.updating;

import classes.Starter;
import helpers.DataValidation;
import helpers.Generator;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserUpdater {
    private String status;
    private Connection connection;

    public UserUpdater(Connection connection) {
        this.connection = connection;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean updateUserTest(User user, String condition) {
        Starter.logger.info(condition);
        String st = DataValidation.userValidationTest(user);
        if (st.equals("Valid")) {
            setStatus("User was updated successfully");
        } else {
            setStatus(st);
        }
        return false;
    }

    public boolean updateUsersAllFields(User user, String condition) {
        String st = DataValidation.userValidationTest(user);
        if ("Valid".equals(st)) {
            StringBuilder queryBuilder = new StringBuilder("UPDATE users SET firstName = ?, lastName = ?, username = ?, ");
            queryBuilder.append("phone = ?, email = ?, userPassword = ?, image = ?, userType = ?");
            queryBuilder.append(" ").append(condition);

            try (PreparedStatement preparedStmt = Generator.userToPS(connection.prepareStatement(queryBuilder.toString()), user)) {
                preparedStmt.executeUpdate();
                setStatus("User was updated successfully");
                return true;
            } catch (SQLException e) {
                setStatus("Couldn't update user");
                return false;
            }
        } else {
            setStatus(st);
            return false;
        }
    }


    public boolean updateUserSingleField(String fieldName, String newValue, String condition) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE users SET ");
        queryBuilder.append(fieldName).append(" = ? ").append(condition);

        try (PreparedStatement preparedStmt = connection.prepareStatement(queryBuilder.toString())) {
            preparedStmt.setString(1, newValue);
            preparedStmt.executeUpdate();
            setStatus("User " + fieldName + " was updated successfully");
            return true;
        } catch (SQLException e) {
            setStatus("Couldn't update user " + fieldName);
            return false;
        }
    }

    public boolean updateUserFirstName(String newFirstName, String condition) {
        return updateUserSingleField("firstName", newFirstName, condition);
    }

    public boolean updateUserLastName(String newLastName, String condition) {
        return updateUserSingleField("lastName", newLastName, condition);
    }

    public boolean updateUserPhone(String newPhoneNumber, String condition) {
        return updateUserSingleField("phone", newPhoneNumber, condition);
    }

    public boolean updateUserEmail(String newEmail, String condition) {
        return updateUserSingleField("email", newEmail, condition);
    }

    public boolean updateUserPassword(String newPassword, String condition) {
        return updateUserSingleField("userPassword", newPassword, condition);
    }

    public boolean updateUserImage(String newImage, String condition) {
        return updateUserSingleField("image", newImage, condition);
    }
}
