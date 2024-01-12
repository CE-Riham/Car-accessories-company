package database.deleting;

import classes.Starter;
import model.products.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductDeleter {
    private String status;
    private Connection connection;

    public ProductDeleter(Connection connection) {
        this.connection = connection;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean deleteProductByProductID(Product product) {
        PreparedStatement preparedStmt = null;
        String query = "delete from products where productID = ?";

        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, product.getProductID());
            preparedStmt.execute();
            setStatus("Product was deleted successfully.");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't delete product.");
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