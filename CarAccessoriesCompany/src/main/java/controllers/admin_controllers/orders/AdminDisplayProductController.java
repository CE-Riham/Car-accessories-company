package controllers.admin_controllers.orders;

import classes.DBConnector;
import classes.UserSession;
import controllers.admin_controllers.AdminNavBarActions;
import database.deleting.OrderDeleter;
import database.retrieving.RetrievingProducts;
import database.retrieving.RetrievingUser;
import database.updating.OrderUpdater;
import helpers.Alerts;
import helpers.stage_helpers.AdminStageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Address;
import model.User;
import model.products.Product;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminDisplayProductController extends AdminNavBarActions implements Initializable {
    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section1: Class attributes -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    // --------- Buttons
    @FXML
    private Button acceptOrderButton;
    @FXML
    private Button rejectOrderButton;

    // --------- Labels
    @FXML
    private Label availableAmountLabel;
    @FXML
    private Label customerAddressLabel;
    @FXML
    private Label customerNameLabel;
    @FXML
    private Label customerPhoneLabel;
    @FXML
    private Label productIDLabel;
    @FXML
    private Label customerEmailLabel;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label productPriceLabel;

    // --------- Image views
    @FXML
    private ImageView customerImage;
    @FXML
    private ImageView productImage;


    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section2: Page button actions ------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    @FXML
    void onRejectOrderClick(ActionEvent event) {
        handleRejectOrder(event);
    }

    @FXML
    void onAcceptOrderClick(ActionEvent event) {
        handleAcceptOrder(event);
    }

    @FXML
    void onBackClick(ActionEvent event) {
        AdminStageHelper.showAdminOrdersPage(event);
    }


    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section4: Initialising actions ------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayingButtons();
        fillProductFields();
        fillCustomerFields();
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // --------------------------------------------- section5: helper methods --------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void displayingButtons() {
        boolean flag = UserSession.getOrderToDisplay().getOrderStatus() == 0;
        displayButton(acceptOrderButton, flag);
        displayButton(rejectOrderButton, flag);
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

    private void fillCustomerFields() {
        String customerName = UserSession.getOrderToDisplay().getCustomerUsername();
        User customer = new RetrievingUser(DBConnector.getConnector().getCon()).findUserByUsername(customerName).get(0);
        customerNameLabel.setText(customer.getFirstName() + " " + customer.getLastName());
        customerEmailLabel.setText(customer.getEmail());
        customerPhoneLabel.setText(customer.getPhoneNumber());
        customerAddressLabel.setText(generateAddress(customer.getAddress()));
        fillImage(customerImage, customer.getImagePath());
    }

    private String generateAddress(Address address) {
        return address.getCountry() + ", " + address.getCity() + ", " + address.getStreet();
    }

    private void fillImage(ImageView image, String imagePath) {
        File file = new File(imagePath);
        String localUrl = file.toURI().toString();
        image.setImage(new Image(localUrl));
    }

    private boolean acceptOrder() {
        OrderUpdater orderUpdater = new OrderUpdater(DBConnector.getConnector().getCon());
        String condition = "where orderID = \'" + UserSession.getOrderToDisplay().getOrderID() + "\'";
        boolean flag = orderUpdater.updateSendingDate(LocalDate.now(), condition);
        return flag ? flag && orderUpdater.updateOrderStatus(1, condition) : flag;
    }

    private boolean deleteOrder(){
        return new OrderDeleter(DBConnector.getConnector().getCon()).deleteOrderByOrderID(UserSession.getOrderToDisplay());
    }
    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------------ section6: handlers ------------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void handleAcceptOrder(ActionEvent event) {
        boolean flag = acceptOrder();
        String alertTitle = "Accepting order";
        if (flag) {
            Alerts.informationAlert(alertTitle, null, "Accepting order successfully");
        } else {
            Alerts.errorAlert(alertTitle, null, "Couldn't accept the order, try again");
        }
        AdminStageHelper.showAdminOrdersPage(event);
    }
    private void handleRejectOrder(ActionEvent event){
        boolean flag = deleteOrder();
        String alertTitle = "Rejecting order";
        if (flag) {
            Alerts.informationAlert(alertTitle, null, "Rejecting order successfully");
        } else {
            Alerts.errorAlert(alertTitle, null, "Couldn't reject the order, try again");
        }
        AdminStageHelper.showAdminOrdersPage(event);
    }
}
