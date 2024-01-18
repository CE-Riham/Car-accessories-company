package helpers;

import classes.Starter;
import model.Address;
import model.installation.Installer;
import model.Order;
import model.User;
import model.products.Product;
import model.products.ProductRate;
import model.products.ProductReview;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Generator {
    private static String productID = "productID";

    private Generator() {
        throw new IllegalStateException("Utility class");
    }

    public static User rsToUser(ResultSet rs) throws SQLException {
        User tmpUser = new User();
        tmpUser.setFirstName(rs.getString("firstName"));
        tmpUser.setLastName(rs.getString("lastName"));
        tmpUser.setUsername(rs.getString("username"));
        tmpUser.setPhoneNumber(rs.getString("phone"));
        tmpUser.setEmail(rs.getString("email"));
        tmpUser.setPassword(rs.getString("userPassword"));
        tmpUser.setImagePath(rs.getString("image"));
        tmpUser.setUserType(rs.getString("userType"));
        return tmpUser;
    }

    public static Address rsToAddress(ResultSet rs) throws SQLException {
        Address tmpAddress = new Address();
        tmpAddress.setCountry(rs.getString("country"));
        tmpAddress.setCity(rs.getString("city"));
        tmpAddress.setStreet(rs.getString("street"));
        return tmpAddress;
    }

    public static Installer rsToInstaller(ResultSet rs) throws SQLException {
        Installer tmpInstaller = new Installer();
        tmpInstaller.setUsername(rs.getString("username"));
        tmpInstaller.setPricePerHour(rs.getDouble("pricePerHour"));
        tmpInstaller.setInstallationStartHour(rs.getTime("installationStartHour"));
        tmpInstaller.setInstallationEndHour(rs.getTime("installationEndHour"));
        tmpInstaller.setAvailable(rs.getBoolean("available"));
        return tmpInstaller;
    }

    public static Product rsToProduct(ResultSet rs) throws SQLException {
        Product tmpProduct = new Product();
        tmpProduct.setProductID(rs.getInt(productID));
        tmpProduct.setProductName(rs.getString("productName"));
        tmpProduct.setProductCategory(rs.getString("category"));
        tmpProduct.setProductPrice(rs.getDouble("price"));
        tmpProduct.setNumberOfOrders(rs.getInt("numberOfOrders"));
        tmpProduct.setImagePath(rs.getString("image"));
        tmpProduct.setLongDescription(rs.getString("longDescription"));
        tmpProduct.setShortDescription(rs.getString("shortDescription"));
        tmpProduct.setAvailableAmount(rs.getInt("availability"));
        return tmpProduct;
    }

    public static ProductReview rsToProductReview(ResultSet rs) throws SQLException {
        ProductReview productReview = new ProductReview();
        productReview.setReviewID(rs.getInt("reviewID"));
        productReview.setCustomerComment(rs.getString("customerComment"));
        productReview.setProductID(rs.getInt(productID));
        return productReview;
    }

    public static ProductRate rsToProductRate(ResultSet rs) throws SQLException {
        ProductRate productRate = new ProductRate();
        productRate.setProductRateID(rs.getInt("rateID"));
        productRate.setCustomerRate(rs.getInt("customerRate"));
        productRate.setProductID(rs.getInt(productID));
        return productRate;
    }

    public static Order rsToOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderID(rs.getInt("orderID"));
        order.setProductID(rs.getInt("productID"));
        order.setCustomerUsername(rs.getString("customerUsername"));
        order.setOrderStatus(rs.getInt("orderStatus"));
        Date orderDateSql = rs.getDate("orderDate");
        Date sendingDateSql = rs.getDate("sendingDate");
        Date receivingDateSql = rs.getDate("receivingDate");
        order.setOrderDate(orderDateSql != null ? orderDateSql.toLocalDate() : null);
        order.setSendingDate(sendingDateSql != null ? sendingDateSql.toLocalDate() : null);
        order.setReceivingDate(receivingDateSql != null ? receivingDateSql.toLocalDate() : null);

        return order;
    }


    public static PreparedStatement userToPS(PreparedStatement preparedStmt, User user) throws SQLException {
        preparedStmt.setString(1, user.getFirstName());
        preparedStmt.setString(2, user.getLastName());
        preparedStmt.setString(3, user.getUsername());
        preparedStmt.setString(4, user.getPhoneNumber());
        preparedStmt.setString(5, user.getEmail());
        preparedStmt.setString(6, user.getPassword());
        preparedStmt.setString(7, user.getImagePath());
        preparedStmt.setString(8, user.getUserType());
        return preparedStmt;
    }

    public static PreparedStatement productToPS(PreparedStatement preparedStmt, Product product) throws SQLException {
        preparedStmt.setString(1, product.getProductName());
        preparedStmt.setString(2, product.getProductCategory());
        preparedStmt.setDouble(3, product.getProductPrice());
        preparedStmt.setInt(4, product.getNumberOfOrders());
        preparedStmt.setString(5, product.getImagePath());
        preparedStmt.setString(6, product.getLongDescription());
        preparedStmt.setString(7, product.getShortDescription());
        preparedStmt.setInt(8, product.getAvailableAmount());
        return preparedStmt;
    }

    public static PreparedStatement orderToPS(PreparedStatement preparedStmt, Order order) throws SQLException {
        preparedStmt.setInt(1, order.getProductID());
        preparedStmt.setString(2, order.getCustomerUsername());
        preparedStmt.setInt(3, order.getOrderStatus());
        preparedStmt.setDate(4, Date.valueOf(order.getOrderDate()));
        preparedStmt.setDate(5, Date.valueOf(order.getSendingDate()));
        preparedStmt.setDate(6, Date.valueOf(order.getReceivingDate()));
        return preparedStmt;
    }

    public static PreparedStatement rateToPS(PreparedStatement preparedStmt, int id, int rate) throws SQLException {
        preparedStmt.setInt(1, id);
        preparedStmt.setInt(2, rate);
        return preparedStmt;
    }

    public static PreparedStatement reviewToPS(PreparedStatement preparedStmt, int id, String customerComment) throws SQLException {
        preparedStmt.setInt(1, id);
        preparedStmt.setString(2, customerComment);
        return preparedStmt;
    }

    public static LocalDate stringToDateConverter(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, formatter);
        } catch (Exception e) {
            Starter.logger.warning("Couldn't convert string to date");
            return LocalDate.of(1, 1, 1);
        }
    }

    public static Time stringToTimeConvertor(String time) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            time += ":00:00";
            LocalTime localTime = LocalTime.parse(time, formatter);
            return Time.valueOf(localTime);
        } catch (Exception e) {
            Starter.logger.warning("Couldn't convert string to time");
            return new Time(System.currentTimeMillis());
        }
    }
}
