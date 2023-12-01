package helpers;

import model.Address;
import model.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import model.installReq;


public class Generator {
    public static User rsToUser(ResultSet rs) throws SQLException {
        User tmpUser = new User();
        tmpUser.setFirstName(rs.getString("firstName"));
        tmpUser.setLastName(rs.getString("lastName"));
        tmpUser.setUsername(rs.getString("username"));
        tmpUser.setPhoneNumber(rs.getString("phone"));
        tmpUser.setEmail(rs.getString("email"));
        tmpUser.setPassword(rs.getString("userPassword"));
        tmpUser.setImagePath(rs.getString("imagePath"));
        return tmpUser;
    }

    public static Address rsToAddress(ResultSet rs) throws SQLException {
        Address tmpAddress = new Address();
        tmpAddress.setCountry(rs.getString("country"));
        tmpAddress.setCity(rs.getString("city"));
        tmpAddress.setStreet(rs.getString("street"));
        return tmpAddress;
    }
    public static PreparedStatement userToPS(PreparedStatement preparedStmt, User user) throws SQLException {
       System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        System.out.println(user.getPhoneNumber());
        System.out.println("user.getAddress()");
        System.out.println(user.getImagePath());

        preparedStmt.setString (1, user.getFirstName());
        preparedStmt.setString (2, user.getLastName());
        preparedStmt.setString   (3, user.getUsername());
        preparedStmt.setString(4, user.getPhoneNumber());
        preparedStmt.setString    (5, user.getEmail());
        preparedStmt.setString    (6, user.getPassword());
        preparedStmt.setString    (7, user.getImagePath());
        preparedStmt.setString   (8, "Something");

        return preparedStmt;
    }
    public static PreparedStatement InstallToPS(PreparedStatement preparedStmt, installReq req) throws SQLException {
        LocalDate localDate = req.getPreferredData();
        Date sqlDate = Date.valueOf(localDate);
        preparedStmt.setInt (1, req.getUserid());
        preparedStmt.setInt (2, req.getProductid());
        preparedStmt.setInt   (3, req.getCarMakeModel());
        preparedStmt.setDate(4,sqlDate);
        return preparedStmt;
    }
}
