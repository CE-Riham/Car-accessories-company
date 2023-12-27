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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import model.Product;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminProductsController implements Initializable {

    // -------------------------------------------- section1: Class attributes -------------------------------------------- //

    private List<Product> allProducts;
    private List<Product> toViewProducts;
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

    // ---------------------------------------- section2: Navigation button action ---------------------------------------- //

    @FXML
    void onAdminProfileClick(ActionEvent event) {
        AdminStageHelper.showAdminProfile(event);
    }

    @FXML
    void onOrdersClick(ActionEvent event) {

    }

    @FXML
    void onProductsClick(ActionEvent event) {
        AdminStageHelper.showAdminProducts(event);
    }

    @FXML
    void onCategoriesClick(ActionEvent event) {
        AdminStageHelper.showAdminCategories(event);
    }

    @FXML
    void onCustomersClick(ActionEvent event) {
        AdminStageHelper.showAdminCustomers(event);
    }

    @FXML
    void onInstallersClick(ActionEvent event) {
    }

    @FXML
    void onAdminsClick(ActionEvent event) {
        AdminStageHelper.showAdminAdmins(event);
    }

    @FXML
    void onNotificationsCLick(ActionEvent event) {

    }

    @FXML
    void onLogoutClick(ActionEvent event) {
        UserSession.logoutUser(event);
    }

    // ------------------------------------------ section3: Page button actions ------------------------------------------- //

    @FXML
    void onCategorySelect(ActionEvent event) {
        filterProductsByCategory();
        fillProducts();
    }

    @FXML
    void onSearchFill(KeyEvent event) {
        System.out.println(searchTextField.getText());
        filterProductsByCategory();
        searchProducts();
        sortProducts();
        fillProducts();
    }

    @FXML
    void onSortTypeSelect(ActionEvent event) {
        fillProducts();
    }

    @FXML
    void onSortSelect(ActionEvent event) {
        fillProducts();
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

    // ------------------------------------------ section4: Initialising actions ------------------------------------------ //

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activateMenuButton();
        getAllProductsFromDB();
        fillAllComboBoxes();
        fillProducts();
    }

    // -------------------------------------------- section5: Fill combo Boxes -------------------------------------------- //

    private void fillAllComboBoxes() {
        fillCategoryCombo();
        fillSearchByCombo();
        fillSortTypeCombo();
        fillSortByCombo();
    }

    private void fillCategoryCombo() {
        RetrievingCategories categoriesRetriever = new RetrievingCategories(DBConnector.getConnector().getCon());
        List<String> allCategories = categoriesRetriever.selectAllCategories();
        allCategories.add("All Categories");
        categoryCombo.setItems(FXCollections.observableArrayList(allCategories));
    }

    private void fillSearchByCombo() {
        List<String> allFields = List.of("productID", "productName", "description");
        searchByCombo.setItems(FXCollections.observableArrayList(allFields));
        searchByCombo.setValue(allFields.get(0));
    }

    private void fillSortTypeCombo() {
        List<String> allFields = List.of("ASC", "DSC");
        sortTypeCombo.setItems(FXCollections.observableArrayList(allFields));
        sortTypeCombo.setValue(allFields.get(0));
    }

    private void fillSortByCombo() {
        List<String> allFields = List.of("productID", "price", "numberOfOrders", "availability");
        sortByCombo.setItems(FXCollections.observableArrayList(allFields));
        sortByCombo.setValue(allFields.get(0));
    }

    // ----------------------------------------- section6: Display toViewProducts ----------------------------------------- //

    private void fillProducts() {
        sortProducts();
        fillRow(row1, pageNumber * 12);
        fillRow(row2, pageNumber * 12 + 6);
    }

    private void fillRow(HBox row, int index) {
        row.getChildren().clear();
        for (int i = index; i < (index + 6) && i < toViewProducts.size(); i++) {
            row.getChildren().add(new ProductCard(toViewProducts.get(i)).getCard());
        }
        disableButton(prevButton, (index == 0));
        disableButton(nextButton, ((index + 6) >= toViewProducts.size()));
    }

    // --------------------------------------------- section7: helper methods --------------------------------------------- //

    private void activateMenuButton() {
        productsButton.setStyle("-fx-border-color: #C9B3AD;");
    }

    private void getAllProductsFromDB() {
        RetrievingProducts productsRetriever = new RetrievingProducts(DBConnector.getConnector().getCon());
        allProducts = productsRetriever.selectAllProducts();
        toViewProducts = new ArrayList<>(allProducts);
    }

    private void disableButton(Button button, boolean flag) {
        button.setDisable(flag);
    }

    private void sortProducts() {
        boolean sortingType = sortTypeCombo.getValue().equals("ASC");
        String sortBy = sortByCombo.getValue();
        toViewProducts = ProductComparator.sortProducts(sortBy, sortingType, toViewProducts);
    }

    private void filterProductsByCategory(){
        toViewProducts = ProductFilter.filterProductsBy("productCategory", categoryCombo.getValue(), new ArrayList<>(allProducts));
    }

    private void searchProducts(){
        toViewProducts = ProductFilter.filterProductsBy(searchByCombo.getValue(), searchTextField.getText(), toViewProducts);
    }
}