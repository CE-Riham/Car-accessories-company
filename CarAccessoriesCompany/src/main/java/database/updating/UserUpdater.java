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
        if (st.equals("Valid"))
            setStatus("User was updated successfully");
        else
            setStatus(st);
        return false;
    }

    public boolean updateUsersAllFields(User user, String condition) {
        String st = DataValidation.userValidationTest(user);
        if (st.equals("Valid")) {
            String query = "UPDATE users SET firstName = ?, lastName = ?, username = ?, " +
                    "phone = ?, email = ?, userPassword = ?, image = ?, userType = ? " + condition;
            try (PreparedStatement preparedStmt = Generator.userToPS(connection.prepareStatement(query), user)) {
                preparedStmt.execute();
                setStatus("User was updated successfully");
                return true;
            } catch (SQLException e) {
                setStatus("Couldn't update user");
                return false;
            }
        } else
            setStatus(st);
        return false;
    }

    public boolean updateUserSingleField(String fieldName, String newValue, String condition) {
        if (!isValidColumnName(fieldName)) {
            setStatus("Invalid column name");
            return false;
        }

        String query = "UPDATE users SET " + fieldName + " = ? " + condition;
        try (PreparedStatement preparedStmt = connection.prepareStatement(query)) {
            preparedStmt.setString(1, newValue);
            preparedStmt.execute();
            setStatus("User " + fieldName + " was updated successfully");
            return true;
        } catch (SQLException e) {
            setStatus("Couldn't update user " + fieldName);
            return false;
        }
    }

    private boolean isValidColumnName(String columnName) {
        return columnName.matches("[a-zA-Z_]+");
    }
}
