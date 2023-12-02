package database.retrieving;

import helpers.Generator;
import model.Address;
import model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RetrievingProducts {
    private Connection con;
    private String status;

    public RetrievingProducts(Connection con){
        this.con = con;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> selectProductsWithCondition(String condition) throws SQLException {
        List<Product> products = new ArrayList<>();
        Statement st = null;
        try {
            String query = "SELECT * FROM products " + condition;
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs != null && rs.next())
                products.add(Generator.rsToProduct(rs));
            setStatus("Retrieving products successfully");
            return products;
        }catch (Exception e){
            setStatus("Error while retrieving products from database");
            return new ArrayList<>();
        }finally {
            if(st != null)st.close();
        }
    }
    public List<Product> selectFromProductsTable(String field, String input) throws SQLException {
        return selectProductsWithCondition("where " + field + " = \'" + input + "\';");
    }
    public List<Product> findProductsByID(String ID) throws SQLException {
        return selectFromProductsTable("productID", ID);
    }
    public List<Product> findProductsByProductName(String productName) throws SQLException {
        return selectFromProductsTable("productName", productName);
    }
    public List<Product> findProductsByCategory(String category) throws SQLException {
        return selectFromProductsTable("category", category);
    }
}
