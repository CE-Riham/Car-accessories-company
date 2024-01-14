package database.updating;

import classes.Starter;
import helpers.DataValidation;
import helpers.Generator;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserUpdater {
    private String status;
    private Connection connection;
    private Map<String, String> queries = new HashMap<>();
    private String[] allFields;

    public UserUpdater(Connection connection) {
        this.connection = connection;
        String tmp = "UPDATE users SET ";
        allFields = new String[]{"firstName", "lastName", "phone",
                "email", "userPassword", "image"};
        for (String field : allFields) {
            queries.put(field, tmp + field + " = ? ");
        }
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
        if (!queries.containsKey(fieldName)) return false;
        String query = queries.get(fieldName) ;
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(query).append(condition);
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
        return updateUserSingleField(allFields[0], newFirstName, condition);
    }

    public boolean updateUserLastName(String newLastName, String condition) {
        return updateUserSingleField(allFields[1], newLastName, condition);
    }

    public boolean updateUserPhone(String newPhoneNumber, String condition) {
        return updateUserSingleField(allFields[2], newPhoneNumber, condition);
    }

    public boolean updateUserEmail(String newEmail, String condition) {
        return updateUserSingleField(allFields[3], newEmail, condition);
    }

    public boolean updateUserPassword(String newPassword, String condition) {
        return updateUserSingleField(allFields[4], newPassword, condition);
    }

    public boolean updateUserImage(String newImage, String condition) {
        return updateUserSingleField(allFields[5], newImage, condition);
    }
}
