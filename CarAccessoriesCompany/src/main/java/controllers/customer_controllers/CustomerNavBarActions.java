package controllers.customer_controllers;

import classes.UserSession;
import helpers.stage_helpers.AdminStageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CustomerNavBarActions {
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
    protected void onInstallersClick(ActionEvent event) {
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

}
