package classes;

import authentication.UserSessionManager;
import helpers.Alerts;
import helpers.stage_helpers.AuthenticationStageHelper;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import model.Order;
import model.products.Product;
import model.User;

import java.util.Optional;

public class UserSession {
    private static String sessionId;
    private static User currentUser;
    private static User userToDisplay;
    private static Product productToDisplay;
    private static Order orderToDisplay;

    public static String getSessionId() {
        return sessionId;
    }

    public static void setSessionId(String sessionId) {
        UserSession.sessionId = sessionId;
    }

    public static User getCurrentUser() {
        return new User(currentUser);
    }

    public static void setCurrentUser(User currentUser) {
        UserSession.currentUser = new User(currentUser);
    }

    public static User getUserToDisplay() {
        return userToDisplay;
    }

    public static void setUserToDisplay(User userToDisplay) {
        UserSession.userToDisplay = userToDisplay;
    }

    public static Product getProductToDisplay() { return productToDisplay;}

    public static void setProductToDisplay(Product productToDisplay) { UserSession.productToDisplay = productToDisplay;}

    public static Order getOrderToDisplay() {
        return orderToDisplay;
    }

    public static void setOrderToDisplay(Order orderToDisplay) {
        UserSession.orderToDisplay = orderToDisplay;
    }

    public static void logoutUser(ActionEvent event) {
        Optional<ButtonType> result = Alerts.confirmationAlert("Logout", "Are you sure you want to logout?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Starter.logger.info("Logout");
            UserSessionManager.logoutUser();
            AuthenticationStageHelper.showLogin(event);
        } else
            Starter.logger.info("Logout was canceled.");
    }

    @Override
    public String toString() {
        return "Classes.UserSession{" + currentUser.getUsername() + ": " +
                "sessionId='" + sessionId + '\'' +
                '}';
    }
}