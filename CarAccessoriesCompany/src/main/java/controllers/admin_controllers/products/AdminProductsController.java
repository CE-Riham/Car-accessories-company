package controllers.admin_controllers.products;

import cards.ProductCard;
import classes.DBConnector;
import classes.UserSession;
import database.retrieving.RetrievingCategories;
import database.retrieving.RetrievingProducts;
import helpers.comparators.ProductComparators;
import helpers.stage_helpers.AdminStageHelper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.Product;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminProductsController implements Initializable {
    private List<Product> allProducts;
    private List<Product> viewedProducts;
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

    @FXML
    private ComboBox<String> searchByCombo;

    @FXML
    private TextField searchTextField;

    @FXML
    private ComboBox<String> sortByCombo;

    @FXML
    private ComboBox<String> sortTypeCombo;

    @FXML
    private ComboBox<String> categoryCombo;

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

    @FXML
    void onSortSelect(ActionEvent event) {
        sortProducts();
    }

    @FXML
    void onSearchFill(ActionEvent event) {

    }

    @FXML
    void onCategorySelect(ActionEvent event) {

    }

    @FXML
    void onSortTypeSelect(ActionEvent event) {
        sortProducts();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activateMenuButton();
        getAllProductsFromDB();
        fillProducts();
        fillSearchByCombo();
        fillCategoryCombo();
        fillSortTypeCombo();
        fillSortByCombo();
    }

    private void getAllProductsFromDB() {
        RetrievingProducts productsRetriever = new RetrievingProducts(DBConnector.getConnector().getCon());
        allProducts = productsRetriever.selectAllProducts();
        viewedProducts = new ArrayList<>(allProducts);
    }

    private void fillRow1(int index) {
        row1.getChildren().clear();
        for (int i = index; i < (index + 6) && i < viewedProducts.size(); i++) {
            row1.getChildren().add(new ProductCard(viewedProducts.get(i)).getCard());
        }
        disableButton(prevButton, (index == 0));
    }

    private void fillRow2(int index) {
        row2.getChildren().clear();
        for (int i = index; i < (index + 6) && i < viewedProducts.size(); i++) {
            row2.getChildren().add(new ProductCard(viewedProducts.get(i)).getCard());
        }
        disableButton(nextButton, ((index + 6) >= viewedProducts.size()));
    }

    private void fillProducts() {
        fillRow1(pageNumber * 12);
        fillRow2(pageNumber * 12 + 6);
    }

    private void disableButton(Button button, boolean flag) {
        button.setDisable(flag);
    }

    private void fillSearchByCombo() {
        List<String> allFields = new ArrayList<>();
        allFields.add("productID");
        allFields.add("productName");
        allFields.add("category");
        allFields.add("description");
        searchByCombo.setItems(FXCollections.observableArrayList(allFields));
    }

    private void fillCategoryCombo() {
        RetrievingCategories categoriesRetriever = new RetrievingCategories(DBConnector.getConnector().getCon());
        List<String> allCategories = categoriesRetriever.selectAllCategories();
        categoryCombo.setItems(FXCollections.observableArrayList(allCategories));
    }

    private void fillSortByCombo() {
        List<String> allFields = new ArrayList<>();
        allFields.add("productID");
        allFields.add("price");
        allFields.add("numberOfOrders");
        allFields.add("availability");
        sortByCombo.setItems(FXCollections.observableArrayList(allFields));
        sortByCombo.setValue("productID");
    }

    private void fillSortTypeCombo() {
        List<String> allFields = new ArrayList<>();
        allFields.add("ASC");
        allFields.add("DSC");
        sortTypeCombo.setItems(FXCollections.observableArrayList(allFields));
        sortTypeCombo.setValue("ASC");
    }

    private void sortProducts() {
        boolean sortingType = sortTypeCombo.getValue().equals("ASC");
        String sortBy = sortByCombo.getValue();
        if (sortBy.equals("productID"))
            viewedProducts = ProductComparators.sortProductsByID(viewedProducts, sortingType);
        else if (sortBy.equals("price"))
            viewedProducts = ProductComparators.sortProductsByPrice(viewedProducts, sortingType);
        else if (sortBy.equals("numberOfOrders"))
            viewedProducts = ProductComparators.sortProductsByNumberOfOrders(viewedProducts, sortingType);
        else if (sortBy.equals("availability"))
            viewedProducts = ProductComparators.sortProductsByAvailability(viewedProducts, sortingType);
        fillProducts();
    }
}