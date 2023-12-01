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

    private ResultSet getFromData(String entity, String condition) throws SQLException {
        ResultSet rs = null;
        String query = "SELECT * FROM " + entity + " " + (condition.equals("") ? "":"where " + condition);
        Statement st = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            return rs;
        }finally {
            if(st != null) st.close();
        }
    }

    public List<User> selectUsers(String condition){
        List<User> users = new ArrayList<>();
        try {
            ResultSet rs = getFromData("users", condition);
            while (rs != null && rs.next())
                users.add(Generator.rsToUser(rs));

            setStatus("Retrieving users successfully");
            return users;
        }catch (Exception e){
            setStatus("Error while retrieving users from database");
            return new ArrayList<>();
        }
    }

    public List<Address> selectAddresses(String condition){
        List<Address> addresses = new ArrayList<>();
        try {
            ResultSet rs = getFromData("addresses", condition);
            while (rs != null && rs.next())
                addresses.add(Generator.rsToAddress(rs));

            setStatus("Retrieving addresses successfully");
            return addresses;
        }catch (Exception e){
            setStatus("Error while retrieving addresses from database");
            return new ArrayList<>();
        }
    }
}
