package helpers.stage_helpers;

import classes.Starter;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminStageHelper {

    private AdminStageHelper() {
        throw new IllegalStateException("Utility class");
    }
    private static void showPage(ActionEvent event, String path, int width, int height) throws IOException {
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
    public static void showAdminCategories(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/admin_pages/adminCategoriesPage.fxml", 1615, 938);
        }catch (Exception e){
            Starter.logger.warning("Can't open admin categories page");
        }
    }
    public static void showAdminProfile(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/admin_pages/adminProfilePage.fxml", 1615, 938);
        }catch (Exception e){
            Starter.logger.warning("Can't open admin profile page");
        }
    }
    public static void showAdminProducts(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/admin_pages/adminProductsPage.fxml", 1615, 965);
        }catch (Exception e){
            Starter.logger.warning("Can't open admin products page");
        }
    }

    public static void showAdminCustomers(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/admin_pages/adminCustomersPage.fxml", 1615, 965);
        }catch (Exception e){
            Starter.logger.warning("Can't open admin customers page");
        }
    }

    public static void showAdminAdmins(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/admin_pages/adminAdminsPage.fxml", 1615, 965);
        }catch (Exception e){
            Starter.logger.warning("Can't open admin admins page");
            e.printStackTrace();
            e.printStackTrace();
        }
    }

    public static void showAddProducts(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/admin_pages/addProductPage.fxml", 608, 837);
        }catch (Exception e){
            Starter.logger.warning("Can't open admin add product page");
            e.printStackTrace();
        }
    }
    public static void showAdminUserProfile(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/admin_pages/userProfilePage.fxml", 1615, 920);
        }catch (Exception e){
            Starter.logger.warning("Can't open admin display user page");
            e.printStackTrace();
        }
    }

}
