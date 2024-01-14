package controllers.admin_controllers.products;

import cards.ProductCard;
import classes.DBConnector;
import classes.UserSession;
import controllers.admin_controllers.AdminNavBarActions;
import database.retrieving.RetrievingCategories;
import database.retrieving.RetrievingProducts;
import helpers.comparators.ProductComparator;
import helpers.filters.ProductFilter;
import helpers.stage_helpers.AdminStageHelper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.products.Product;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminProductsController extends AdminNavBarActions implements Initializable {

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


    // ------------------------------------------ section2: Page button actions ------------------------------------------- //

    @FXML
    void filterProducts(Event event) {
        fillFilteredData();
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

    // ------------------------------------------ section3: Initialising actions ------------------------------------------ //

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activateMenuButton(productsButton);
        getAllProductsFromDB();
        fillAllComboBoxes();
        fillFilteredData();
    }

    // -------------------------------------------- section4: Fill combo Boxes -------------------------------------------- //

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
        categoryCombo.setValue(allCategories.get(allCategories.size()-1));
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

    // ----------------------------------------- section5: Display toViewProducts ----------------------------------------- //

    private void fillProducts() {
        fillRow(row1, pageNumber * 12);
        fillRow(row2, pageNumber * 12 + 6);
    }

    private void fillRow(HBox row, int index) {
        row.getChildren().clear();
        for (int i = index; i < (index + 6) && i < toViewProducts.size(); i++) {
            row.getChildren().add(new ProductCard(toViewProducts.get(i)).getCard());
        }
        disableButton(prevButton, (pageNumber == 0));
        disableButton(nextButton, ((index + 6) >= toViewProducts.size()));
    }

    // --------------------------------------------- section6: helper methods --------------------------------------------- //
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
        toViewProducts = ProductComparator.sortProducts(sortBy, sortingType, new ArrayList<>(toViewProducts));
    }

    private void filterProductsByCategory() {
        toViewProducts = ProductFilter.filterProductsBy("productCategory", categoryCombo.getValue(), new ArrayList<>(allProducts));
    }

    private void searchProducts() {
        toViewProducts = ProductFilter.filterProductsBy(searchByCombo.getValue(), searchTextField.getText(), toViewProducts);
    }

    private void fillFilteredData(){
        filterProductsByCategory();
        searchProducts();
        sortProducts();
        fillProducts();
    }
}