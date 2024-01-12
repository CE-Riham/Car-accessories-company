package database.retrieving;

import classes.Starter;
import helpers.Generator;
import model.products.ProductRate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RetrievingProductRates {
    private Connection connection;
    private String status;

    public RetrievingProductRates(Connection con) {
        this.connection = con;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductRate> selectRatesByProductID(String productID) {
        List<ProductRate> rates = new ArrayList<>();
        String query = "SELECT * FROM productRates where productID = ?";
        Statement st = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            st = connection.createStatement();
            preparedStatement.setString(1, productID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs != null && rs.next())
                rates.add(Generator.rsToProductRate(rs));
            setStatus("Retrieving rates successfully");
            return rates;
        } catch (Exception e) {
            setStatus("Error while retrieving rates from database");
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