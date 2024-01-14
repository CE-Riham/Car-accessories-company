package controllers.admin_controllers.products;

import cards.ProductReviewCard;
import classes.DBConnector;
import classes.UserSession;
import controllers.admin_controllers.AdminNavBarActions;
import database.deleting.ProductDeleter;
import database.deleting.ProductReviewDeleter;
import database.retrieving.RetrievingProductRates;
import database.retrieving.RetrievingProductReviews;
import helpers.Alerts;
import helpers.stage_helpers.AdminStageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.products.Product;
import model.products.ProductRate;
import model.products.ProductReview;

import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminDisplayProductController extends AdminNavBarActions implements Initializable {
    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section1: Class attributes -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    @FXML
    private Label availableAmountLabel;
    @FXML
    private Label longDescriptionLabel;
    @FXML
    private Button nextReviewsButton;
    @FXML
    private Label numberOfOrdersLabel;
    @FXML
    private Label numberOfReviewsLabel;
    @FXML
    private Button previousReviewsButton;
    @FXML
    private Label productCategoryLabel;
    @FXML
    private Label productIDLabel;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label productPriceLabel;
    @FXML
    private Label productRateLabel;
    @FXML
    private VBox reviewsPanel;
    @FXML
    private ImageView productImage;
    @FXML
    private Button deleteReviewButton;
    private List<ProductReview> allReviews = new ArrayList<>();
    private int reviewPageNumber;


    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section2: Page button actions ------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    @FXML
    void onUpdateProductClick(ActionEvent event) {
        AdminStageHelper.showUpdateProducts(event);
    }

    @FXML
    void onPreviousReviewsClick(ActionEvent event) {
        reviewPageNumber--;
        fillReviews();
    }

    @FXML
    void onNextReviewsClick(ActionEvent event) {
        reviewPageNumber++;
        fillReviews();
    }

    @FXML
    void onDeleteReviewClick(ActionEvent event) {
        handleDeleteProductReview();
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        handleDeleteProduct(event);
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section3: Initialising actions ------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeReviews();
        setProductFields();
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section4: Helper functions -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void retrieveAllReviewsFromDB() {
        allReviews = (new RetrievingProductReviews(DBConnector.getConnector().getCon())).selectReviewsByProductID(String.valueOf(UserSession.getProductToDisplay().getProductID()));
        disableButton(deleteReviewButton, allReviews.isEmpty());
    }

    private void fillReviews() {
        reviewsPanel.getChildren().clear();
        for (int i = reviewPageNumber * 6; i < (6 + 6 * reviewPageNumber) && i < allReviews.size(); i++) {
            reviewsPanel.getChildren().add(new ProductReviewCard(allReviews.get(i)).getCard());
        }
        disableButton(previousReviewsButton, (reviewPageNumber == 0));
        disableButton(nextReviewsButton, ((6 * reviewPageNumber + 6) >= allReviews.size()));
    }

    private void initializeReviews() {
        retrieveAllReviewsFromDB();
        reviewPageNumber = 0;
        fillReviews();
    }

    private void fillImage(String imagePath) {
        File file = new File(imagePath);
        String localUrl = file.toURI().toString();
        productImage.setImage(new Image(localUrl));
    }

    private void setProductFields() {
        Product tmpProduct = new Product(UserSession.getProductToDisplay());
        productPriceLabel.setText(String.valueOf(tmpProduct.getProductPrice()));
        productNameLabel.setText(tmpProduct.getProductName());
        productIDLabel.setText("ID: " + tmpProduct.getProductID());
        productCategoryLabel.setText(tmpProduct.getProductCategory());
        availableAmountLabel.setText(String.valueOf(tmpProduct.getAvailableAmount()));
        longDescriptionLabel.setText(tmpProduct.getLongDescription());
        numberOfOrdersLabel.setText(String.valueOf(tmpProduct.getNumberOfOrders()));
        numberOfReviewsLabel.setText(String.valueOf(allReviews.size()));
        fillImage(tmpProduct.getImagePath());
        findProductRate(tmpProduct.getProductID());
    }

    private void disableButton(Button button, boolean flag) {
        //true to disable
        button.setDisable(flag);
    }

    private void handleDeleteProduct(ActionEvent event) {
        String alertTitle = "Deleting product";
        Optional<ButtonType> result = Alerts.confirmationAlert(alertTitle, "Are you sure you want to delete this product?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            //delete the product
            boolean flag = new ProductDeleter(DBConnector.getConnector().getCon()).deleteProductByProductID(UserSession.getProductToDisplay());
            if (flag) {
                UserSession.setProductToDisplay(null);
                Alerts.informationAlert(alertTitle, null, "Product was deleted successfully");
                AdminStageHelper.showAdminProducts(event);
            } else {
                Alerts.errorAlert(alertTitle, null, "Couldn't delete the product");
            }
        }
    }

    private void handleDeleteProductReview() {
        String alertTitle = "Deleting review";
        Optional<String> result = Alerts.withInputAlert(alertTitle, null, "Enter review's ID");
        if (result.isPresent()) {
            List<ProductReview> reviews = new RetrievingProductReviews(DBConnector.getConnector().getCon()).selectReviewByReviewID(result.get());
            if (reviews.isEmpty()) {
                Alerts.errorAlert(alertTitle, null, "Invalid review's ID");
                return;
            }
            ProductReview tmpReview = reviews.get(0);
            if (tmpReview.getProductID() == UserSession.getProductToDisplay().getProductID()) {
                Optional<ButtonType> confirmationResult = Alerts.confirmationAlert(alertTitle, "Are you sure that you want to delete this review?");
                if (confirmationResult.isPresent() && confirmationResult.get() == ButtonType.OK) {
                    boolean flag = new ProductReviewDeleter(DBConnector.getConnector().getCon()).deleteProductReview(result.get());
                    if (flag) {
                        Alerts.informationAlert(alertTitle, null, "Review was deleted successfully");
                        initializeReviews();
                    } else {
                        Alerts.errorAlert(alertTitle, null, "Couldn't delete the review");
                    }
                }
            } else {
                Alerts.errorAlert(alertTitle, null, "This review belongs to the product with id = " + tmpReview.getProductID() + ", you can't delete it from here");
            }
        }
    }

    private void findProductRate(int productID) {
        RetrievingProductRates retrievingProductRates = new RetrievingProductRates(DBConnector.getConnector().getCon());
        List<ProductRate> rates = retrievingProductRates.selectRatesByProductID(String.valueOf(productID));
        int numberOdRate = rates.isEmpty() ? 1 : rates.size();
        double sum = 0;
        for (ProductRate rate : rates) sum += rate.getCustomerRate();
        productRateLabel.setText(new DecimalFormat("#.##").format(sum / numberOdRate));
    }
}
