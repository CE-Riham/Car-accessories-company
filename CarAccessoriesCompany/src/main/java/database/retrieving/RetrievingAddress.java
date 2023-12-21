package database.retrieving;

import helpers.Generator;
import model.Address;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public List<Address> selectAddressesWithCondition(String condition) throws SQLException {
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
            if(st != null)st.close();
        }
    }
    public List<Address> selectFromAddressesTable(String field, String input) throws SQLException {
        return selectAddressesWithCondition("where " + field + " = \'" + input + "\';");
    }
    public List<Address> findAddressByUsername(String username) throws SQLException {
        return selectFromAddressesTable("username", username);
    }
    public List<Address> findAddressByCountry(String country) throws SQLException {
        return selectFromAddressesTable("country", country);
    }
    public List<Address> findAddressByCity(String city) throws SQLException {
        return selectFromAddressesTable("city", city);
    }
    public List<Address> findAddressByStreet(String street) throws SQLException {
        return selectFromAddressesTable("street", street);
    }
}
