package database;

import helpers.Generator;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertingData {
    private String status;
    private Connection connection;
    public InsertingData(Connection connection){
        this.connection = connection;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean insertUser(User user){
        try {
            String query = "insert into users "
                    + " values (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt = Generator.userToPS(preparedStmt, user);
            preparedStmt.execute();
            setStatus("User was inserted successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't insert user");
            return false;
        }
    }

    public boolean insertCustomer(String username, double account){
        try {
            String query = "insert into customers "
                    + " values (?, ?);";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, username);
            preparedStmt.setString (2, String.valueOf(account));
            preparedStmt.execute();
            setStatus("customer was inserted successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't insert customer");
            return false;
        }
    }
}
