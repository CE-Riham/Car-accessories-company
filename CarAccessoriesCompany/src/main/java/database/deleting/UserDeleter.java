package database.deleting;

import classes.Starter;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserDeleter {
    private String status;
    private Connection connection;

    public UserDeleter(Connection connection) {
        this.connection = connection;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean deleteUserFromUsersTableByUsername(User user) {
        PreparedStatement preparedStmt = null;
        try {
            String query = "delete from users where username = '" + user.getUsername() + "';";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();
            setStatus("User was deleted successfully.");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't delete user.");
            return false;
        } finally {
            if (preparedStmt != null) {
                try {
                    preparedStmt.close();
                } catch (Exception e) {
                    Starter.logger.warning("error while closing the statement.");
                }
            }
        }
    }

}