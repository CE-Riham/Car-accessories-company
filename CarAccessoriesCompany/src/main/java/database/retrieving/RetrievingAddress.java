package database.retrieving;

import classes.Starter;
import helpers.Generator;
import model.Address;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RetrievingAddress {
    private Connection con;
    private String status;

    public RetrievingAddress(Connection con){
        this.con = con;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Address> selectAddressesWithCondition(String condition) {
        List<Address> addresses = new ArrayList<>();
        Statement st = null;
        try {
            String query = "SELECT * FROM addresses " + condition;
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
            if(st != null) {
                try {
                    st.close();
                }catch (Exception e){
                    Starter.logger.warning("Couldn't close the statement");
                }
            }
        }
    }
    public List<Address> selectFromAddressesTable(String field, String input){
        return selectAddressesWithCondition("where " + field + " = \'" + input + "\';");
    }
    public List<Address> findAddressByUsername(String username){
        return selectFromAddressesTable("username", username);
    }
    public List<Address> findAddressByCountry(String country){
        return selectFromAddressesTable("country", country);
    }
    public List<Address> findAddressByCity(String city){
        return selectFromAddressesTable("city", city);
    }
    public List<Address> findAddressByStreet(String street){
        return selectFromAddressesTable("street", street);
    }
}
