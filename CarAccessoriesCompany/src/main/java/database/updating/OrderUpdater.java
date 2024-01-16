package database.updating;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class OrderUpdater {
    private String status;
    private Connection connection;
    private Map<String, String> queries = new HashMap<>();
    private String[] allFields;

    public OrderUpdater(Connection connection) {
        this.connection = connection;
        String tmp = "UPDATE orders SET ";
        allFields = new String[]{"orderStatus", "orderDate", "sendingDate", "receivingDate"};
        for (String field : allFields) {
            queries.put(field, tmp + field + " = ? ");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private boolean updateOrderSingleDateField(String fieldName, LocalDate newValue, String condition) {
        if (!queries.containsKey(fieldName)) return false;
        String query = queries.get(fieldName);
        StringBuilder queryBuilder = new StringBuilder(query).append(condition);
        try (PreparedStatement preparedStmt = connection.prepareStatement(queryBuilder.toString())) {
            preparedStmt.setDate(1, Date.valueOf(newValue));
            preparedStmt.executeUpdate();
            setStatus("Order " + fieldName + " was updated successfully");
            return true;
        } catch (SQLException e) {
            setStatus("Couldn't update order " + fieldName);
            return false;
        }
    }
    private boolean updateOrderSingleIntegerField(String fieldName, int newValue, String condition) {
        if (!queries.containsKey(fieldName)) return false;
        String query = queries.get(fieldName);
        StringBuilder queryBuilder = new StringBuilder(query).append(condition);
        try (PreparedStatement preparedStmt = connection.prepareStatement(queryBuilder.toString())) {
            preparedStmt.setInt(1, newValue);
            preparedStmt.executeUpdate();
            setStatus("Order " + fieldName + " was updated successfully");
            return true;
        } catch (SQLException e) {
            setStatus("Couldn't update order " + fieldName);
            return false;
        }
    }
    public boolean updateOrderStatus(int newOrderStatus, String condition) {
        return updateOrderSingleIntegerField(allFields[0], newOrderStatus, condition);
    }

    public boolean updateOrderDate(LocalDate newOrderDate, String condition) {
        return updateOrderSingleDateField(allFields[1], newOrderDate, condition);
    }


    public boolean updateSendingDate(LocalDate newSendingDate, String condition) {
        return updateOrderSingleDateField(allFields[2], newSendingDate, condition);
    }

    public boolean updateReceivingDate(LocalDate newReceivingDate, String condition) {
        return updateOrderSingleDateField(allFields[3], newReceivingDate, condition);
    }


}
