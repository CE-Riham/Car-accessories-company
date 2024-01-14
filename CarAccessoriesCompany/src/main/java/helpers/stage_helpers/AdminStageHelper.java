package helpers.stage_helpers;

import classes.Starter;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminStageHelper{

    private AdminStageHelper() {
        throw new IllegalStateException("Utility class");
    }
    protected static void showPage(ActionEvent event, String path, int width, int height) throws IOException {
        if (event.getSource() instanceof Node source) {
            Parent root = FXMLLoader.load(AdminStageHelper.class.getResource(path));
            Scene scene = source.getScene();
            Stage stage = (Stage) scene.getWindow();
            scene.setRoot(root);
            stage.setHeight(height);
            stage.setWidth(width);
            stage.centerOnScreen();
        }
    }
    public static void showAdminCategories(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/admin_pages/adminCategoriesPage.fxml", 1615, 938);
        } catch (Exception e) {
            Starter.logger.warning("Can't open admin categories page");
        }
    }

    public static void showAdminProfile(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/admin_pages/adminProfilePage.fxml", 1615, 938);
        } catch (Exception e) {
            Starter.logger.warning("Can't open admin profile page");
        }
    }

    public static void showAdminProducts(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/admin_pages/product/adminProductsPage.fxml", 1615, 965);
        } catch (Exception e) {
            Starter.logger.warning("Can't open admin products page");
        }
    }

    public static void showAdminCustomers(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/admin_pages/adminCustomersPage.fxml", 1615, 965);
        } catch (Exception e) {
            Starter.logger.warning("Can't open admin customers page");
        }
    }

    public static void showAdminAdmins(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/admin_pages/adminAdminsPage.fxml", 1615, 965);
        } catch (Exception e) {
            Starter.logger.warning("Can't open admin admins page");
        }
    }

    public static void showAddProducts(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/admin_pages/product/addProductPage.fxml", 608, 837);
        } catch (Exception e) {
            Starter.logger.warning("Can't open admin add product page");
        }
    }

    public static void showAdminUserProfile(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/admin_pages/userProfilePage.fxml", 1615, 965);
        } catch (Exception e) {
            Starter.logger.warning("Can't open admin display user page");
        }
    }

    public static void showAdminDisplayProductPage(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/admin_pages/product/adminProductPage.fxml", 1615, 965);
        } catch (Exception e) {
            Starter.logger.warning("Can't open admin display product page");
        }
    }

    public static void showUpdateProducts(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/admin_pages/product/updateProductPage.fxml", 608, 837);
        } catch (Exception e) {
            Starter.logger.warning("Can't open admin update product page");
        }
    }

    public static void showAdminOrdersPage(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/admin_pages/adminOrdersPage.fxml", 1615, 965);
        } catch (Exception e) {
            e.printStackTrace();
            Starter.logger.warning("Can't open admin orders page");
        }
    }
}
