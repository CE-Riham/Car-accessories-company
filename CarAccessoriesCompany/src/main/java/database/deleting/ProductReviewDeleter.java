package database.deleting;

import classes.Starter;
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

    public boolean deleteProductReview(String reviewID) {
        if (!deleteProductReviewFromTable(reviewID)) {
            setStatus("Couldn't delete productReview");
            Alerts.errorAlert("Deleting productReview", null, "Error while deleting productReview");
            return false;
        }
        setStatus("ProductReview was deleted successfully");
        return true;
    }

    private boolean deleteProductReviewFromTable(String reviewID) {
        PreparedStatement preparedStmt = null;
        String query = "delete from productReviews where reviewID = ?";
        try {
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, reviewID);
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