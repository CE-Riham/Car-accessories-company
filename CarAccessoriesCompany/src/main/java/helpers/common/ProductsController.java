package helpers.common;

import cards.ProductCard;
import classes.DBConnector;
import classes.Starter;
import database.retrieving.RetrievingCategories;
import database.retrieving.RetrievingProducts;
import helpers.comparators.ProductComparator;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import model.products.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsController {
    private ProductsController() {
        Starter.logger.info("Hi from Products controller");
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section1: Fill combo Boxes -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private static void fillCategoryCombo(ComboBox<String> categoryCombo) {
        RetrievingCategories categoriesRetriever = new RetrievingCategories(DBConnector.getConnector().getCon());
        List<String> allCategories = categoriesRetriever.selectAllCategories();
        allCategories.add("All Categories");
        categoryCombo.setItems(FXCollections.observableArrayList(allCategories));
        categoryCombo.setValue(allCategories.get(allCategories.size() - 1));
    }

    private static void fillSearchByCombo(ComboBox<String> searchByCombo) {
        List<String> allFields = List.of("productID", "productName", "description");
        searchByCombo.setItems(FXCollections.observableArrayList(allFields));
        searchByCombo.setValue(allFields.get(0));
    }

    private static void fillSortTypeCombo(ComboBox<String> sortTypeCombo) {
        List<String> allFields = List.of("ASC", "DSC");
        sortTypeCombo.setItems(FXCollections.observableArrayList(allFields));
        sortTypeCombo.setValue(allFields.get(0));
    }

    private static void fillSortByCombo(ComboBox<String> sortByCombo) {
        List<String> allFields = List.of("productID", "price", "numberOfOrders", "availability");
        sortByCombo.setItems(FXCollections.observableArrayList(allFields));
        sortByCombo.setValue(allFields.get(0));
    }
    public static void fillAllComboBoxes(ComboBox<String>categoryCombo, ComboBox<String>searchByCombo, ComboBox<String>sortTypeCombo, ComboBox<String>sortByCombo) {
        ProductsController.fillCategoryCombo(categoryCombo);
        ProductsController.fillSearchByCombo(searchByCombo);
        ProductsController.fillSortTypeCombo(sortTypeCombo);
        ProductsController.fillSortByCombo(sortByCombo);
    }
    // -------------------------------------------------------------------------------------------------------------------- //
    // ----------------------------------------- section5: Display toViewProducts ----------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    public static void fillFilteredData(List<Product> toViewProducts, HBox row1, HBox row2, int pageNumber) {
        fillRow(toViewProducts, row1, pageNumber * 12);
        fillRow(toViewProducts, row2, pageNumber * 12 + 6);
    }

    private static void fillRow(List<Product> toViewProducts, HBox row, int index) {
        row.getChildren().clear();
        for (int i = index; i < (index + 6) && i < toViewProducts.size(); i++) {
            row.getChildren().add(new ProductCard(toViewProducts.get(i)).getCard());
        }
    }

    private static void disableButton(Button button, boolean flag) {
        button.setDisable(flag);
    }

    public static void disableProductsButtons(List<Product> toViewProducts, Button prevButton, Button nextButton, int pageNumber) {
        disableButton(prevButton, (pageNumber == 0));
        disableButton(nextButton, ((pageNumber * 12 + 12) >= toViewProducts.size()));
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // --------------------------------------------- section4: helper methods --------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    public static List<Product> getAllProductsFromDB() {
        RetrievingProducts productsRetriever = new RetrievingProducts(DBConnector.getConnector().getCon());
        return productsRetriever.selectAllProducts();
    }

}
