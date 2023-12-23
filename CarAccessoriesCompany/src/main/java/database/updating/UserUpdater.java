package database.updating;

import classes.Starter;
import helpers.DataValidation;
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

        try (PreparedStatement preparedStmt = connection.prepareStatement(queryBuilder.toString())) {
            preparedStmt.setString(1, user.getFirstName());
            preparedStmt.setString(2, user.getLastName());
            preparedStmt.setString(3, user.getUsername());
            preparedStmt.setString(4, user.getPhoneNumber());
            preparedStmt.setString(5, user.getEmail());
            preparedStmt.setString(6, user.getPassword());
            preparedStmt.setString(7, user.getImagePath());
            preparedStmt.setString(8, user.getUserType());

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
    if (!isValidColumnName(fieldName)) {
        setStatus("Invalid column name");
        return false;
    }

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

    private boolean isValidColumnName(String columnName) {
        return columnName.matches("[a-zA-Z0-9_]+");
    }
}
