package database;

import helpers.Generator;
import model.User;
import model.installReq;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertingData {
    private String status;
    private Connection con;
    public InsertingData(Connection connection){
        con = connection;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean insertUser(User user){
        try {
            String query = "insert into users_new "
                    + " values (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt = Generator.userToPS(preparedStmt, user);
            preparedStmt.execute();
            setStatus("User was inserted successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't insert user");
            return false;
        }
    }
    public boolean insertInstallReq(installReq req) {
        try {
            String query = "INSERT INTO installationrequests VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt = Generator.InstallToPS(preparedStmt, req);
            preparedStmt.executeUpdate();
            System.out.println("HHHHHHHHHHHHhhh : "+preparedStmt);

            setStatus("Install Request was inserted successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception for debugging
            setStatus("Couldn't insert Request due to a database error");
            return false;
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            setStatus("An unexpected error occurred while inserting the request");
            return false;
        }
    }
}
