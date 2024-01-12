package database.retrieving;

import classes.Starter;
import helpers.Generator;
import model.products.ProductReview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RetrievingProductReviews {
    private Connection connection;
    private String status;

    public RetrievingProductReviews(Connection con) {
        this.connection = con;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductReview> selectReviewsByProductID(String productID) {
        List<ProductReview> reviews = new ArrayList<>();
        String query = "SELECT * FROM productReviews where productID = ?";
        Statement st = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            st = connection.createStatement();
            preparedStatement.setString(1, productID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs != null && rs.next())
                reviews.add(Generator.rsToProductReview(rs));
            setStatus("Retrieving reviews successfully");
            return reviews;
        } catch (Exception e) {
            setStatus("Error while retrieving reviews from database");
            return new ArrayList<>();
        } finally {
            try {
                if (st != null) st.close();
            } catch (Exception e) {
                Starter.logger.warning("Can't close statement");
            }
        }
    }

    public List<ProductReview> selectReviewByReviewID(String reviewID) {
        List<ProductReview> result = new ArrayList<>();
        Statement st = null;
        String query = "SELECT * FROM productReviews where reviewID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            st = connection.createStatement();
            preparedStatement.setString(1, reviewID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs != null && rs.next()) result.add(Generator.rsToProductReview(rs));
            setStatus("Retrieving reviews successfully");
            return result;
        } catch (Exception e) {
            setStatus("Error while retrieving reviews from database");
            return new ArrayList<>();
        } finally {
            try {
                if (st != null) st.close();
            } catch (Exception e) {
                Starter.logger.warning("Can't close statement");
            }
        }
    }

}