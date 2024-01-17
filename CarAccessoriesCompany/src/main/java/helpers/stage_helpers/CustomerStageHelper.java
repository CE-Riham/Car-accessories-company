package helpers.stage_helpers;

import classes.Starter;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerStageHelper {
    private CustomerStageHelper(){
        Starter.logger.info("Hi customer stage helper :)");
    }
    protected static void showPage(ActionEvent event, String path, int width, int height) throws IOException {
        if (event.getSource() instanceof Node source) {
            Parent root = FXMLLoader.load(CustomerStageHelper.class.getResource(path));
            Scene scene = source.getScene();
            Stage stage = (Stage) scene.getWindow();
            scene.setRoot(root);
            stage.setHeight(height);
            stage.setWidth(width);
            stage.centerOnScreen();
        }
    }

    public static void showCustomerHomePage(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/customer_pages/customerHomePage.fxml", 1615, 965);
        } catch (Exception e) {
            Starter.logger.warning("Can't open customer home page");
        }
    }

    public static void showCustomerOrders(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/customer_pages/customerOrdersPage.fxml", 1615, 965);
        } catch (Exception e) {
            Starter.logger.warning("Can't open customer orders page");
        }
    }

    public static void showCustomerProfile(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/customer_pages/customerProfilePage.fxml", 1615, 938);
        } catch (Exception e) {
            Starter.logger.warning("Can't open customer profile page");
        }
    }

    public static void showCustomerDisplayProductPage(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/customer_pages/customerProductPage.fxml", 1615, 965);
        } catch (Exception e) {
            Starter.logger.warning("Can't open customer display product page");
        }
    }

    public static void showCustomerDisplayOrderPage(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/customer_pages/customerOrderPage.fxml", 608, 837);
        } catch (Exception e) {
            Starter.logger.warning("Can't open customer display order page");
        }
    }

    public static void showCustomerOrderRatePage(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/customer_pages/rateOrderPage.fxml", 608, 608);
        } catch (Exception e) {
            Starter.logger.warning("Can't open customer order rate page");
        }
    }
}
