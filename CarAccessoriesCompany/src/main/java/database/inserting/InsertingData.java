package database.inserting;

import classes.Starter;
import helpers.Generator;
import model.Order;
import model.products.Product;
import model.User;

import java.sql.*;

public class InsertingData {
    private String status;
    private Connection connection;

    public InsertingData(Connection connection) {
        this.connection = connection;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean insertUser(User user) {
        try {
            String query = "insert into users "
                    + " values (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt = Generator.userToPS(preparedStmt, user);
            preparedStmt.execute();
            setStatus("User was inserted successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't insert user");
            return false;
        }
    }

    public boolean insertCustomer(String username, double account) throws SQLException {
        PreparedStatement preparedStmt = null;
        try {
            String query = "insert into customers "
                    + " values (?, ?);";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, username);
            preparedStmt.setString(2, String.valueOf(account));
            preparedStmt.execute();
            setStatus("customer was inserted successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't insert customer");
            return false;
        } finally {
            if (preparedStmt != null) preparedStmt.close();
        }
    }

    public boolean insertInstaller(String username, double account, int installationTimes) throws SQLException {
        PreparedStatement preparedStmt = null;
        try {
            String query = "insert into installers "
                    + " values (?, ?, ?);";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, username);
            preparedStmt.setDouble(2, account);
            preparedStmt.setInt(3, installationTimes);
            preparedStmt.execute();
            setStatus("installer was inserted successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't insert installer");
            return false;
        } finally {
            if (preparedStmt != null) preparedStmt.close();
        }
    }

    public int insertProduct(Product product) {
        try {
            String query = "insert into products (productName, category, price, numberOfOrders, image, longDescription, " +
                    "shortDescription, availability) values (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt = Generator.productToPS(preparedStmt, product);
            preparedStmt.executeUpdate();
            setStatus("Product was inserted successfully");
            ResultSet rs = preparedStmt.getGeneratedKeys(); //to get productID
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            setStatus("Couldn't insert Product");
        }
        return -1;
    }

    public boolean insertCategory(String category) {
        PreparedStatement preparedStmt = null;
        try {
            String query = "insert into categories "
                    + " values (?);";
            preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, category);
            preparedStmt.execute();
            setStatus("category was inserted successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't insert category");
            return false;
        } finally {
            try {
                if (preparedStmt != null) preparedStmt.close();
            } catch (Exception e) {
                Starter.logger.warning("Couldn't close the statement");
            }
        }
    }

    public boolean insertOrder(Order order) {
        try {
            String query = "insert into orders (productID, customerUsername, orderStatus, orderDate, sendingDate, receivingDate)" +
                    " values (?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt = Generator.orderToPS(preparedStmt, order);
            preparedStmt.executeUpdate();
            setStatus("Order was inserted successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't insert Order");
            return false;
        }
    }

    public boolean insertProductRating(int productID, int rating) {
        try {
            String query = "insert into productRates (productID, customerRate)" +
                    " values (?, ?);";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt = Generator.rateToPS(preparedStmt, productID, rating);
            preparedStmt.executeUpdate();
            setStatus("Product rating was inserted successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't insert product rate");
            return false;
        }
    }
    public boolean insertProductReview(int productID, String customerComment) {
        try {
            String query = "insert into productReviews (productID, customerComment)" +
                    " values (?, ?);";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt = Generator.reviewToPS(preparedStmt, productID, customerComment);
            preparedStmt.executeUpdate();
            setStatus("Product review was inserted successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't insert product review");
            return false;
        }
    }
    public boolean insertInstallationRating(int installerID, int rating) {
        try {
            String query = "insert into installerRates (installerID, customerRate)" +
                    " values (?, ?);";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt = Generator.rateToPS(preparedStmt, installerID, rating);
            preparedStmt.executeUpdate();
            setStatus("Installation rating was inserted successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't insert installation rate");
            return false;
        }
    }
    public boolean insertInstallerReview(int installerID, String customerComment) {
        try {
            String query = "insert into installerReviews (installerID, customerComment)" +
                    " values (?, ?);";
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt = Generator.reviewToPS(preparedStmt, installerID, customerComment);
            preparedStmt.executeUpdate();
            setStatus("Installation review was inserted successfully");
            return true;
        } catch (Exception e) {
            setStatus("Couldn't insert installation review");
            return false;
        }
    }
}