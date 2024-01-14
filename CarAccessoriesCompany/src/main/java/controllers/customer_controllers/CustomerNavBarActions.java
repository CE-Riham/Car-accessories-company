package controllers.customer_controllers;

import classes.UserSession;
import helpers.stage_helpers.AdminStageHelper;
import helpers.stage_helpers.CustomerStageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CustomerNavBarActions {
    @FXML
    protected void onHomePageClick(ActionEvent event) {
        CustomerStageHelper.showCustomerHomePage(event);
    }

    @FXML
    protected void onCustomerProfileClick(ActionEvent event) {
        CustomerStageHelper.showCustomerProfile(event);
    }

    @FXML
    protected void onMyOrdersClick(ActionEvent event) {
        CustomerStageHelper.showCustomerOrders(event);
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
