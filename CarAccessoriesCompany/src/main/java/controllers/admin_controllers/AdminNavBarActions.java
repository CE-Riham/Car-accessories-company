package controllers.admin_controllers;

import classes.UserSession;
import helpers.stage_helpers.AdminStageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminNavBarActions {
    @FXML
    protected void onAdminProfileClick(ActionEvent event) {
        AdminStageHelper.showAdminProfile(event);
    }

    @FXML
    protected void onOrdersClick(ActionEvent event) {
        AdminStageHelper.showAdminOrdersPage(event);
    }

    @FXML
    protected void onProductsClick(ActionEvent event) {
        AdminStageHelper.showAdminProducts(event);
    }

    @FXML
    protected void onCategoriesClick(ActionEvent event) {
        AdminStageHelper.showAdminCategories(event);
    }

    @FXML
    protected void onCustomersClick(ActionEvent event) {
        AdminStageHelper.showAdminCustomers(event);
    }

    @FXML
    protected void onInstallersClick(ActionEvent event) {
    }

    @FXML
    protected void onAdminsClick(ActionEvent event) {
        AdminStageHelper.showAdminAdmins(event);
    }

    @FXML
    protected void onNotificationsCLick(ActionEvent event) {

    }

    @FXML
    protected void onLogoutClick(ActionEvent event) {
        UserSession.logoutUser(event);
    }

    protected void activateMenuButton(Button button) {
        button.setStyle("-fx-border-color: #76453B;"); //color5
    }
    protected void disableButton(Button button, boolean flag) {
        button.setDisable(flag);
    }
    protected void displayButton(Button button, boolean flag) {
        button.setVisible(flag);
    }

}
