package database.retrieving;

import classes.Starter;
import helpers.Generator;
import model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RetrievingProducts {
    private Connection con;
    private String status;

    public RetrievingProducts(Connection con) {
        this.con = con;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> selectProductsWithCondition(String condition) {
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
        } catch (Exception e) {
            setStatus("Error while retrieving products from database");
            return new ArrayList<>();
        } finally {
            try {
                if (st != null) st.close();
            } catch (Exception e) {
                Starter.logger.warning("can't close statement");
            }
        }
    }

    public List<Product> selectAllProducts() {
        return selectProductsWithCondition(";");
    }

    public List<Product> selectFromProductsTable(String field, String input) {
        return selectProductsWithCondition("where " + field + " = \'" + input + "\';");
    }

    public List<Product> findProductsByID(String id) {
        return selectFromProductsTable("productID", id);
    }

    public List<Product> findProductsByProductName(String productName) {
        return selectFromProductsTable("productName", productName);
    }

    public List<Product> findProductsByCategory(String category) {
        return selectFromProductsTable("category", category);
    }
}