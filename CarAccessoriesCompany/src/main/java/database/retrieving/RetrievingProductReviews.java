package database.retrieving;

import classes.Starter;
import helpers.Generator;
import model.ProductReview;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RetrievingProductReviews {
    private Connection con;
    private String status;

    public RetrievingProductReviews(Connection con) {
        this.con = con;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductReview> selectReviewsByProductID(String productID) {
        List<ProductReview> reviews = new ArrayList<>();
        Statement st = null;
        try {
            String query = "SELECT * FROM productReviews where productID = \'" + productID + "\'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
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
        try {
            String query = "SELECT * FROM productReviews where reviewID = \'" + reviewID + "\'";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
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