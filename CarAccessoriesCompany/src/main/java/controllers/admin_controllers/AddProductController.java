package controllers.admin_controllers;

import classes.DBConnector;
import database.InsertingData;
import database.updating.ProductUpdater;
import helpers.Alerts;
import helpers.DataValidation;
import helpers.stage_helpers.AdminStageHelper;
import helpers.Uploader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Category;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductController implements Initializable{
    private Uploader uploader;
    private String alertTitle = "Adding product";
    private InsertingData productInserter;
    @FXML
    private Label imagePathLabel;
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
        productInserter = new InsertingData(DBConnector.getConnector().getCon());
        uploader = new Uploader();
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
        if(!status.equals("Valid"))
            Alerts.errorAlert(alertTitle, null, status);
        else {
            Product product = new Product();
            product.setProductName(productNameString);
            product.setLongDescription(longDescriptionString);
            product.setShortDescription(shortDescriptionString);
            product.setProductCategory(Category.valueOf(productCategory.toUpperCase()));
            product.setProductPrice(Double.parseDouble(productPriceString));
            product.setAvailableAmount(Integer.parseInt(quantityString));
            int productID = productInserter.insertProduct(product);
            status = productInserter.getStatus();
            if(status.equals("Product was inserted successfully") && !uploader.getFileName().equals("")){
                String savePath = "src/main/resources/assets/products/" +  productID + ".png";
                boolean savingFlag = uploader.saveToFile(savePath, false);
                if(savingFlag){
                    //save the new photo in database
                    product.setImagePath(savePath);
                    String condition = " where productID = \'" + productID + "\';";
                    ProductUpdater productUpdater = new ProductUpdater(DBConnector.getConnector().getCon());
                    productUpdater.updateProductImage(savePath, condition);
                }
                Alerts.informationAlert(alertTitle, null, "Product was added successfully");
            }
            else
                Alerts.errorAlert(alertTitle, null, "Couldn't add the product!");
            AdminStageHelper.showAdminProducts(event);
        }
    }

    @FXML
    void onChooseImageClick(ActionEvent event) {
        boolean uploadImageFlag = uploader.uploadImage();
        if(uploadImageFlag){
            imagePathLabel.setText(uploader.getFileName());
        }
    }
}
