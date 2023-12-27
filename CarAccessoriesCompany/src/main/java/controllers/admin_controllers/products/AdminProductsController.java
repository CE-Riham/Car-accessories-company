package controllers.admin_controllers.products;

import cards.ProductCard;
import classes.DBConnector;
import classes.UserSession;
import database.retrieving.RetrievingCategories;
import database.retrieving.RetrievingProducts;
import helpers.comparators.ProductComparator;
import helpers.filters.ProductFilter;
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
    private List<Product> toViewProducts;
    private String productID = "productID";
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
        fillProducts();
    }

    @FXML
    void onSearchFill(ActionEvent event) {

    }

    @FXML
    void onCategorySelect(ActionEvent event) {
        toViewProducts = ProductFilter.filterProductsBy("productCategory", categoryCombo.getValue(), new ArrayList<>(allProducts));
        fillProducts();
    }

    @FXML
    void onSortTypeSelect(ActionEvent event) {
        fillProducts();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activateMenuButton();
        getAllProductsFromDB();
        fillSearchByCombo();
        fillCategoryCombo();
        fillSortTypeCombo();
        fillSortByCombo();
        fillProducts();
    }

    private void getAllProductsFromDB() {
        RetrievingProducts productsRetriever = new RetrievingProducts(DBConnector.getConnector().getCon());
        allProducts = productsRetriever.selectAllProducts();
        toViewProducts = new ArrayList<>(allProducts);
    }

    private void fillRow1(int index) {
        row1.getChildren().clear();
        for (int i = index; i < (index + 6) && i < toViewProducts.size(); i++) {
            row1.getChildren().add(new ProductCard(toViewProducts.get(i)).getCard());
        }
        disableButton(prevButton, (index == 0));
    }

    private void fillRow2(int index) {
        row2.getChildren().clear();
        for (int i = index; i < (index + 6) && i < toViewProducts.size(); i++) {
            row2.getChildren().add(new ProductCard(toViewProducts.get(i)).getCard());
        }
        disableButton(nextButton, ((index + 6) >= toViewProducts.size()));
    }

    private void fillProducts() {
        sortProducts();
        fillRow1(pageNumber * 12);
        fillRow2(pageNumber * 12 + 6);
    }

    private void disableButton(Button button, boolean flag) {
        button.setDisable(flag);
    }

    private void fillSearchByCombo() {
        List<String> allFields = new ArrayList<>();
        allFields.add(productID);
        allFields.add("productName");
        allFields.add("description");
        searchByCombo.setItems(FXCollections.observableArrayList(allFields));
    }

    private void fillCategoryCombo() {
        RetrievingCategories categoriesRetriever = new RetrievingCategories(DBConnector.getConnector().getCon());
        List<String> allCategories = categoriesRetriever.selectAllCategories();
        allCategories.add("All Categories");
        categoryCombo.setItems(FXCollections.observableArrayList(allCategories));
    }

    private void fillSortByCombo() {
        List<String> allFields = new ArrayList<>();
        allFields.add(productID);
        allFields.add("price");
        allFields.add("numberOfOrders");
        allFields.add("availability");
        sortByCombo.setItems(FXCollections.observableArrayList(allFields));
        sortByCombo.setValue(productID);
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
        toViewProducts = ProductComparator.sortProducts(sortBy, sortingType, toViewProducts);
    }
}