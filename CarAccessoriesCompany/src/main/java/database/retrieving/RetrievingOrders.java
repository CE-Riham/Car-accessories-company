package database.retrieving;

import classes.Starter;
import helpers.Generator;
import model.Order;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RetrievingOrders {
    private Connection con;
    private String status;

    public RetrievingOrders(Connection con) {
        this.con = con;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Order> selectProductsWithCondition(String condition) {
        List<Order> orders = new ArrayList<>();
        Statement st = null;
        try {
            String query = "SELECT * FROM orders " + condition;
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs != null && rs.next())
                orders.add(Generator.rsToOrder(rs));
            setStatus("Retrieving orders successfully");
            return orders;
        } catch (Exception e) {
            setStatus("Error while retrieving orders from database");
            return new ArrayList<>();
        } finally {
            try {
                if (st != null) st.close();
            } catch (Exception e) {
                Starter.logger.warning("can't close statement");
            }
        }
    }

    public List<Order> selectAllOrders() {
        return selectProductsWithCondition(";");
    }

    private List<Order> selectFromOrdersTable(String field, String input) {
        return selectProductsWithCondition("where " + field + " = \'" + input + "\';");
    }

    public List<Order> findOrdersByCustomerUsername(String customerUsername) {
        return selectFromOrdersTable("customerUsername", customerUsername);
    }
}