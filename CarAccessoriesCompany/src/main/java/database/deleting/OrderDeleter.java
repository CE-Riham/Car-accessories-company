package database.deleting;

import classes.Starter;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderDeleter {
    private String status;
    private Connection connection;

    public OrderDeleter(Connection connection) {
        this.connection = connection;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean deleteOrderByOrderID(Order order) {
        PreparedStatement preparedStmt = null;
        String query = "delete from orders where orderID = ?";

        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, order.getOrderID());
            preparedStmt.execute();
            setStatus("Product was deleted successfully.");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't delete order.");
            return false;
        } finally {
            if (preparedStmt != null) {
                try {
                    preparedStmt.close();
                } catch (Exception e) {
                    Starter.logger.warning("error while closing the statement.");
                }
            }
        }
    }

}