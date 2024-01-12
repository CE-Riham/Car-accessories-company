package database.deleting;

import classes.DBConnector;
import classes.Starter;
import database.updating.ProductUpdater;
import helpers.Alerts;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductReviewDeleter {
    private String status;
    private Connection connection;

    public ProductReviewDeleter(Connection connection) {
        this.connection = connection;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean deleteProductReview(String ReviewID) {
        if (!deleteProductReviewFromTable(ReviewID)) {
            setStatus("Couldn't delete productReview");
            Alerts.errorAlert("Deleting productReview", null, "Error while deleting productReview");
            return false;
        }
        setStatus("ProductReview was deleted successfully");
        return true;
    }

    private boolean deleteProductReviewFromTable(String reviewID) {
        PreparedStatement preparedStmt = null;
        try {
            String query = "delete from productReviews where reviewID = '" + reviewID + "';";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.execute();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (preparedStmt != null) {
                try {
                    preparedStmt.close();
                } catch (Exception e) {
                    Starter.logger.warning("Error while closing the statement.");
                }
            }
        }
    }
}