package database;

import model.User;

import java.sql.*;

public class InsertingData {
    private Connection con;
    public InsertingData(Connection connection){
        con = connection;
    }

    public boolean insertUser(User user){
        try {
            String query = "insert into users "
                    + " values (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, "Riham");
            preparedStmt.setString (2, "Katout");
            preparedStmt.setString   (3, "rihamkatout3");
            preparedStmt.setString(4, "0599119482");
            preparedStmt.setString    (5, "rihamkatm@gmail.com");
            preparedStmt.setString    (6, "123456");
            preparedStmt.setString    (7, "");
            preparedStmt.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
