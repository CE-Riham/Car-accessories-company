package helpers;

import classes.Starter;
import model.Address;
import model.Order;
import model.products.Product;
import model.products.ProductRate;
import model.products.ProductReview;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        order.setOrderDate(rs.getDate("orderDate"));
        order.setSendingDate(rs.getDate("sendingDate"));
        order.setReceivingDate(rs.getDate("receivingDate"));
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

    public static Date stringToDateConvertor(String date){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        }catch (Exception e){
            Starter.logger.warning("Couldn't convert string to date");
            return new Date();
        }
    }
}
