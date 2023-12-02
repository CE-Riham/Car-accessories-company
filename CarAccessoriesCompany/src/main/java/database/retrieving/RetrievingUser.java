package database.retrieving;

import helpers.Generator;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RetrievingUser {
    private Connection con;
    private String status;

    public RetrievingUser(Connection con){
        this.con = con;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public List<User> selectUsersWithCondition(String condition) throws SQLException {
        List<User> users = new ArrayList<>();
        Statement st = null;
        try {
            String query = "SELECT * FROM users " + condition;
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs != null && rs.next())
                users.add(Generator.rsToUser(rs));
            setStatus("Retrieving users successfully");
            return users;
        }catch (Exception e){
            setStatus("Error while retrieving users from database");
            return new ArrayList<>();
        }finally {
            if(st != null)st.close();
        }
    }
    public List<User> selectFromUsersTable(String field, String input) throws SQLException {
        return selectUsersWithCondition("where " + field + " = \'" + input + "\';");
    }
    public List<User> findUserByUsername(String username) throws SQLException {
        return selectFromUsersTable("username", username);
    }
    public List<User> findUserByFirstName(String firstName) throws SQLException {
        return selectFromUsersTable("firstName", firstName);
    }
    public List<User> findUserByLastName(String lastName) throws SQLException {
        return selectFromUsersTable("lastName", lastName);
    }
    public List<User> findUserByEmail(String email) throws SQLException {
        return selectFromUsersTable("email", email);
    }
    public List<User> findUserByPhone(String phone) throws SQLException {
        return selectFromUsersTable("phone", phone);
    }
    public List<User> findUserByUserType(String userType) throws SQLException {
        return selectFromUsersTable("userType", userType);
    }

}
