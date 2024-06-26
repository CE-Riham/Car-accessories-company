package controllers.customer_controllers;

import classes.DBConnector;
import classes.UserSession;
import controllers.admin_controllers.AdminNavBarActions;
import database.deleting.OrderDeleter;
import database.retrieving.RetrievingProducts;
import database.updating.OrderUpdater;
import helpers.Alerts;
import helpers.stage_helpers.CustomerStageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Order;
import model.products.Product;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerDisplayOrderController extends AdminNavBarActions implements Initializable {
    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section1: Class attributes -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    // --------- Buttons
    @FXML
    private Button cancelOrderButton;
    @FXML
    private Button receiveOrderButton;

    // --------- Labels
    @FXML
    private Label availableAmountLabel;
    @FXML
    private Label productIDLabel;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label productPriceLabel;
    @FXML
    private Label sendingDateLabel;
    @FXML
    private Label receivingDateLabel;
    @FXML
    private Label orderDateLabel;
    @FXML
    private Label orderStatusLabel;

    // --------- Image views
    @FXML
    private ImageView productImage;


    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section2: Page button actions ------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    @FXML
    void onCancelOrderClick(ActionEvent event) {
        handleCancelOrder(event);
    }

    @FXML
    void onReceiveOrderClick(ActionEvent event) {
        handleReceiveOrder(event);
    }

    @FXML
    void onBackClick(ActionEvent event) {
        CustomerStageHelper.showCustomerOrders(event);
    }


    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section3: Initialising actions ------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayingButtons();
        fillProductFields();
        fillOrderFields();
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // --------------------------------------------- section4: helper methods --------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void displayingButtons() {
        displayButton(cancelOrderButton, UserSession.getOrderToDisplay().getOrderStatus() == 0);
        displayButton(receiveOrderButton, UserSession.getOrderToDisplay().getOrderStatus() == 1);
    }

    private void fillProductFields() {
        int productID = UserSession.getOrderToDisplay().getProductID();
        Product product = new RetrievingProducts(DBConnector.getConnector().getCon()).findProductsByID(String.valueOf(productID)).get(0);
        productNameLabel.setText(product.getProductName());
        productIDLabel.setText(String.valueOf(productID));
        productPriceLabel.setText(String.valueOf(product.getProductPrice()));
        availableAmountLabel.setText(String.valueOf(product.getAvailableAmount()));
        fillImage(productImage, product.getImagePath());
    }

    private void fillOrderFields() {
        Order order = UserSession.getOrderToDisplay();
        String[] status = {"pending", "sent", "received"};
        orderStatusLabel.setText(status[order.getOrderStatus()]);
        orderDateLabel.setText(String.valueOf(order.getOrderDate()));
        String sendingDate = String.valueOf(order.getSendingDate());
        sendingDate = sendingDate.equals("0001-01-01") ? "not sent yet" : sendingDate;
        sendingDateLabel.setText(sendingDate);
        String receivingDate = String.valueOf(order.getReceivingDate());
        receivingDate = receivingDate.equals("0001-01-01") ? "not received yet" : receivingDate;
        receivingDateLabel.setText(receivingDate);
    }

    private void fillImage(ImageView image, String imagePath) {
        File file = new File(imagePath);
        String localUrl = file.toURI().toString();
        image.setImage(new Image(localUrl));
    }

    private boolean deleteOrder() {
        return new OrderDeleter(DBConnector.getConnector().getCon()).deleteOrderByOrderID(UserSession.getOrderToDisplay());
    }

    private boolean receiveOrder() {
        OrderUpdater orderUpdater = new OrderUpdater(DBConnector.getConnector().getCon());
        String condition = "where orderID = \'" + UserSession.getOrderToDisplay().getOrderID() + "\'";
        boolean flag = orderUpdater.updateReceivingDate(LocalDate.now(), condition);
        return flag && orderUpdater.updateOrderStatus(2, condition);
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------------ section5: handlers ------------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void handleCancelOrder(ActionEvent event) {
        String alertTitle = "Canceling order";
        Optional<ButtonType> result = Alerts.confirmationAlert(alertTitle, "Are you sure you want to cancel the order?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean flag = deleteOrder();
            if (flag) {
                Alerts.informationAlert(alertTitle, null, "Canceling order successfully");
            } else {
                Alerts.errorAlert(alertTitle, null, "Couldn't cancel the order, try again");
            }
            CustomerStageHelper.showCustomerOrders(event);
        }
    }

    private void handleReceiveOrder(ActionEvent event) {
        String alertTitle = "Receiving order";
        Optional<ButtonType> result = Alerts.confirmationAlert(alertTitle, "Are you sure you received the order? Once you confirm you can't cancel.");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean flag = receiveOrder();
            if (flag) {
                Alerts.informationAlert(alertTitle, null, "Receiving order successfully");
                CustomerStageHelper.showCustomerOrderRatePage(event);
            } else {
                Alerts.errorAlert(alertTitle, null, "Couldn't retrieve the order, try again");
                CustomerStageHelper.showCustomerOrders(event);
            }
        }
    }
}
