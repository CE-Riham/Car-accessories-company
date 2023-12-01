package database;

import helpers.Generator;
import model.Address;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RetrievingData{
    private Connection con;
    private String status;

    public RetrievingData(Connection con){
        this.con = con;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> selectUsers(String condition) throws SQLException {
        List<User> users = new ArrayList<>();
        Statement st = null;
        try {
            String query = "SELECT * FROM users " + (condition.equals("") ? "":"where " + condition);
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

    public List<Address> selectAddresses(String condition) throws SQLException {
        List<Address> addresses = new ArrayList<>();
        Statement st = null;
        try {
            String query = "SELECT * FROM addresses " + (condition.equals("") ? "":"where " + condition);
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs != null && rs.next())
                addresses.add(Generator.rsToAddress(rs));
            setStatus("Retrieving addresses successfully");
            return addresses;
        }catch (Exception e){
            setStatus("Error while retrieving addresses from database");
            return new ArrayList<>();
        }finally {
            if(st != null)st.close();
        }
    }
}
