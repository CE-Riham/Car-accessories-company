package helpers;

import model.Address;
import model.Product;
import model.ProductReview;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Generator {
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
        tmpProduct.setProductID(rs.getInt("productID"));
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
        productReview.setProductID(rs.getInt("productID"));
        return productReview;
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
}
