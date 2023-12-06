package controllers.admin_controllers;

import authentication.UserSessionManager;
import classes.UserSession;
import helpers.stage_helpers.AdminStageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminProductsController implements Initializable {
    @FXML
    private Button productsButton;
    private void disableAllMenuButtonsExcept(){
        productsButton.setStyle("-fx-border-color: #C9B3AD;");
    }

    @FXML
    void onAdminProfileClick(ActionEvent event) {
        AdminStageHelper.showAdminProfile(event);
    }

    @FXML
    void onAdminsClick(ActionEvent event) {

    }

    @FXML
    void onCustomersClick(ActionEvent event) {

    }

    @FXML
    void onDashboardClick(ActionEvent event) {
        AdminStageHelper.showAdminDashboard(event);
    }

    @FXML
    void onInstallersClick(ActionEvent event) {

    }

    @FXML
    void onLogoutClick(ActionEvent event) {
        UserSession.logoutUser(event);
    }

    @FXML
    void onNotificationsCLick(ActionEvent event) {

    }

    @FXML
    void onOrdersClick(ActionEvent event) {

    }

    @FXML
    void onProductsClick(ActionEvent event) {
        AdminStageHelper.showAdminProducts(event);
    }

    @FXML
    void onAddProductClick(ActionEvent event) {
        AdminStageHelper.showAddProducts(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        disableAllMenuButtonsExcept();
    }

}
