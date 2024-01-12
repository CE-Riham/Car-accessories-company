package controllers.admin_controllers.products;

import classes.DBConnector;
import classes.UserSession;
import database.retrieving.RetrievingCategories;
import database.updating.ProductUpdater;
import helpers.Alerts;
import helpers.DataValidation;
import helpers.Uploader;
import helpers.stage_helpers.AdminStageHelper;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Product;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateProductController implements Initializable {
    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section1: Class attributes -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    @FXML
    private ComboBox<String> category;
    @FXML
    private Label imagePathLabel;
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
    private Uploader uploader;

    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section2: Page button actions ------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //

    @FXML
    void onUpdateProductClick(ActionEvent event) {
        handleUpdateProduct(event);
    }

    @FXML
    void onCancelButtonClick(ActionEvent event) {
        AdminStageHelper.showAdminDisplayProductPage(event);
    }

    @FXML
    void onChooseImageClick(ActionEvent event) {
        boolean uploadImageFlag = uploader.uploadImage();
        if (uploadImageFlag) {
            imagePathLabel.setText(uploader.getFileName());
        }
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section3: Initialising actions ------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        uploader = new Uploader();
        fillFields();
        fillCategories();
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section4: Helper functions -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void fillCategories() {
        RetrievingCategories categoriesRetriever = new RetrievingCategories(DBConnector.getConnector().getCon());
        List<String> allCategories = categoriesRetriever.selectAllCategories();
        category.setItems(FXCollections.observableArrayList(allCategories));
    }

    private void fillFields() {
        Product product = UserSession.getProductToDisplay();
        productName.setText(product.getProductName());
        longDescription.setText(product.getLongDescription());
        shortDescription.setText(product.getShortDescription());
        category.setValue(product.getProductCategory());
        productPrice.setText(String.valueOf(product.getProductPrice()));
        quantity.setText(String.valueOf(product.getAvailableAmount()));
    }

    private Product fillProductObjectFields() {
        Product product = UserSession.getProductToDisplay();
        product.setProductName(productName.getText());
        product.setLongDescription(longDescription.getText());
        product.setShortDescription(shortDescription.getText());
        product.setProductCategory(category.getValue());
        product.setProductPrice(Double.parseDouble(productPrice.getText()));
        product.setAvailableAmount(Integer.parseInt(quantity.getText()));
        if (!uploader.getFileName().isEmpty()) {
            String savePath = "src/main/resources/assets/products/" + product.getProductID() + ".png";
            boolean savingFlag = uploader.saveToFile(savePath, false);
            if (savingFlag) {
                //save new photo to database
                product.setImagePath(savePath);
            }
        }
        return product;
    }

    private String checkProductValidation() {
        String productNameString = productName.getText();
        String longDescriptionString = longDescription.getText();
        String shortDescriptionString = shortDescription.getText();
        String productCategory = category.getValue();
        String productPriceString = productPrice.getText();
        String quantityString = quantity.getText();
        return DataValidation.productFieldsTest(productNameString, longDescriptionString, shortDescriptionString,
                productCategory, productPriceString, quantityString);
    }

    private void handleUpdateProduct(ActionEvent event) {
        String alertTitle = "Updating product";
        String status = checkProductValidation();
        if (!status.equals("Valid"))
            Alerts.errorAlert(alertTitle, null, status);
        else {
            Product product = fillProductObjectFields();
            boolean flag = new ProductUpdater(DBConnector.getConnector().getCon()).updateProductsAllFields(product, "where productID = " + product.getProductID());
            if (flag) {
                UserSession.setProductToDisplay(product);
                Alerts.informationAlert(alertTitle, null, "Product was updated successfully");
            } else
                Alerts.errorAlert(alertTitle, null, "Couldn't update the product!");
            AdminStageHelper.showAdminDisplayProductPage(event);
        }
    }
}
