package database;

import helpers.Generator;
import model.Address;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
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


    public List<User> selectUsers(String condition){
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM users_new ";
            if (!condition.equals(""))
                query += "where " + condition;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs != null && rs.next()) {
                User tmpUser = Generator.rsToUser(rs);
                users.add(tmpUser);
                System.out.println(tmpUser);
            }
            setStatus("Retrieving users successfully");
            return users;
        }catch (Exception e){
            setStatus("Error while retrieving users from database");
            return null;
        }
    }

    public List<Address> selectAddresses(String condition){
        List<Address> addresses = new ArrayList<>();
        try {
            String query = "SELECT * FROM addresses ";
            if (!condition.equals(""))
                query += "where " + condition;

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs != null && rs.next()) {
                Address tmpAddress = Generator.rsToAddress(rs);
                addresses.add(tmpAddress);
                System.out.println(tmpAddress);
            }
            setStatus("Retrieving addresses successfully");
            return addresses;
        }catch (Exception e){
            setStatus("Error while retrieving addresses from database");
            return null;
        }
    }


}
