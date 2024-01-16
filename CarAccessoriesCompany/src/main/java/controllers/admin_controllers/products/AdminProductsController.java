package controllers.admin_controllers.products;

import controllers.admin_controllers.AdminNavBarActions;
import helpers.common.ProductsController;
import helpers.comparators.ProductComparator;
import helpers.filters.ProductFilter;
import helpers.stage_helpers.AdminStageHelper;
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
    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section1: Class attributes -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
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


    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section2: Page button actions ------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    @FXML
    void filterProducts(Event event) {
        handleFilterProducts();
    }

    @FXML
    void onAddProductClick(ActionEvent event) {
        AdminStageHelper.showAddProducts(event);
    }

    @FXML
    void onPrevButtonClick(ActionEvent event) {
        pageNumber--;
        ProductsController.fillFilteredData(toViewProducts, row1, row2, pageNumber);
        ProductsController.disableProductsButtons(toViewProducts, prevButton, nextButton, pageNumber);
    }

    @FXML
    void onNextButtonClick(ActionEvent event) {
        pageNumber++;
        ProductsController.fillFilteredData(toViewProducts, row1, row2, pageNumber);
        ProductsController.disableProductsButtons(toViewProducts, prevButton, nextButton, pageNumber);
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section3: Initialising actions ------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activateMenuButton(productsButton);
        allProducts = ProductsController.getAllProductsFromDB();
        toViewProducts = new ArrayList<>(allProducts);
        ProductsController.fillAllComboBoxes(categoryCombo, searchByCombo, sortTypeCombo, sortByCombo);
        ProductsController.fillFilteredData(toViewProducts, row1, row2, pageNumber);
        ProductsController.disableProductsButtons(toViewProducts, prevButton, nextButton, pageNumber);
    }


    // -------------------------------------------------------------------------------------------------------------------- //
    // --------------------------------------------- section4: helper methods --------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
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

    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------------ section7: handlers ------------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void handleFilterProducts() {
        filterProductsByCategory();
        searchProducts();
        sortProducts();
        ProductsController.fillFilteredData(toViewProducts, row1, row2, pageNumber);
    }
}