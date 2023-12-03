package controllers;

import helpers.DataValidation;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import model.Category;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable{
    private Product addedProduct;
    @FXML
    private ComboBox<Category> category;

    @FXML
    private TextField longDescription;

    @FXML
    private TextField productName;

    @FXML
    private TextField productPrice;

    @FXML
    private TextField quantity;

    @FXML
    private TextField shortDescription;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        ObservableList<Category> list = FXCollections.observableArrayList(Category.values());
        category.setItems(list);
        addedProduct = new Product();
    }
    @FXML
    void onAddProductClick(ActionEvent event) {
        String productNameString = productName.getText();
        String longDescriptionString = longDescription.getText();
        String shortDescriptionString = shortDescription.getText();
        String productCategory = String.valueOf(category.getValue());
        String productPriceString = productPrice.getText();
        String quantityString = quantity.getText();
        String status = DataValidation.productFieldsTest(productNameString, longDescriptionString, shortDescriptionString,
                productCategory, productPriceString, quantityString);
        System.out.println(status);
    }
    @FXML
    void onCategorySelected(ActionEvent event) {
        System.out.println(category.getSelectionModel().getSelectedItem().toString());
    }}
