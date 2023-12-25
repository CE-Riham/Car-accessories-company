package controllers.admin_controllers.products;

import cards.ProductCard;
import classes.DBConnector;
import classes.UserSession;
import database.retrieving.RetrievingProducts;
import helpers.stage_helpers.AdminStageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.Product;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminProductsController implements Initializable {
    private List<Product> allProducts;
    private int pageNumber = 0;
    @FXML
    private Button productsButton;
    @FXML
    private Button prevButton;
    @FXML
    private Button nextButton;

    @FXML
    private HBox row1;

    @FXML
    private HBox row2;

    private void activateMenuButton() {
        productsButton.setStyle("-fx-border-color: #C9B3AD;");
    }

    @FXML
    void onAdminProfileClick(ActionEvent event) {
        AdminStageHelper.showAdminProfile(event);
    }

    @FXML
    void onAdminsClick(ActionEvent event) {
        AdminStageHelper.showAdminAdmins(event);
    }

    @FXML
    void onCustomersClick(ActionEvent event) {
        AdminStageHelper.showAdminCustomers(event);
    }

    @FXML
    void onCategoriesClick(ActionEvent event) {
        AdminStageHelper.showAdminCategories(event);
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

    @FXML
    void onPrevButtonClick(ActionEvent event) {
        pageNumber--;
        fillProducts();
    }

    @FXML
    void onNextButtonClick(ActionEvent event) {
        pageNumber++;
        fillProducts();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activateMenuButton();
        fillProducts();
    }

    private void getAllProductsFromDB() {
        RetrievingProducts productsRetriever = new RetrievingProducts(DBConnector.getConnector().getCon());
        allProducts = productsRetriever.selectAllProducts();
    }

    private void fillRow1(int index) {
        row1.getChildren().clear();
        for (int i = index; i < (index + 6) && i < allProducts.size(); i++) {
            row1.getChildren().add(new ProductCard(allProducts.get(i)).getCard());
        }
        disableButton(prevButton, (index == 0));
    }

    private void fillRow2(int index) {
        row2.getChildren().clear();
        for (int i = index; i < (index + 6) && i < allProducts.size(); i++) {
            row2.getChildren().add(new ProductCard(allProducts.get(i)).getCard());
        }
        disableButton(nextButton, ((index + 6) >= allProducts.size()));
    }

    private void fillProducts() {
        getAllProductsFromDB();
        fillRow1(pageNumber * 12);
        fillRow2(pageNumber * 12 + 6);
    }

    private void disableButton(Button button, boolean flag) {
        button.setDisable(flag);
    }
}