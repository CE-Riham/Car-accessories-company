package database.retrieving;

import classes.Starter;
import helpers.Generator;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
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
    public List<User> selectUsersWithCondition(String condition){
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
            try{
                if (st != null) st.close();
            }catch (Exception e){
                Starter.logger.warning("Can't close statement");
            }
        }
    }
    public List<User> selectFromUsersTable(String field, String input){
        return selectUsersWithCondition("where " + field + " = \'" + input + "\';");
    }
    public List<User> findUserByUsername(String username){
        return selectFromUsersTable("username", username);
    }
    public List<User> findUserByFirstName(String firstName){
        return selectFromUsersTable("firstName", firstName);
    }
    public List<User> findUserByLastName(String lastName){
        return selectFromUsersTable("lastName", lastName);
    }
    public List<User> findUserByEmail(String email){
        return selectFromUsersTable("email", email);
    }
    public List<User> findUserByPhone(String phone){
        return selectFromUsersTable("phone", phone);
    }
    public List<User> findUserByUserType(String userType){
        return selectFromUsersTable("userType", userType);
    }

}
