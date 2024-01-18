package controllers.customer_controllers;

import classes.DBConnector;
import classes.UserSession;
import database.inserting.InsertingData;
import helpers.Alerts;
import helpers.stage_helpers.CustomerStageHelper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class CustomerProductRateController {
    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section1: Class attributes -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private int rating;
    @FXML
    private Button blackStar1;
    @FXML
    private Button blackStar2;
    @FXML
    private Button blackStar3;
    @FXML
    private Button blackStar4;
    @FXML
    private Button blackStar5;
    @FXML
    private ImageView goldStar1;
    @FXML
    private ImageView goldStar2;
    @FXML
    private ImageView goldStar3;
    @FXML
    private ImageView goldStar4;
    @FXML
    private ImageView goldStar5;
    @FXML
    private TextField reviewTextField;


    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section2: Page button actions ------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    @FXML
    void onCancelClick(ActionEvent event) {
        CustomerStageHelper.showCustomerOrders(event);
    }

    @FXML
    void onSubmitClick(ActionEvent event) {
        handleSubmission(event);
    }

    @FXML
    void onStarClick(ActionEvent event) {
        handleRatingChange(event);
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // --------------------------------------------- section3: helper methods --------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //

    private boolean addRating() {
        return new InsertingData(DBConnector.getConnector().getCon()).insertProductRating(UserSession.getOrderToDisplay().getProductID(), rating);
    }

    private boolean addReview() {
        String review = reviewTextField.getText();
        return new InsertingData(DBConnector.getConnector().getCon()).insertProductReview(UserSession.getOrderToDisplay().getProductID(), review);
    }

    private void hideAllGoldStars() {
        goldStar1.setVisible(false);
        goldStar2.setVisible(false);
        goldStar3.setVisible(false);
        goldStar4.setVisible(false);
        goldStar5.setVisible(false);
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------------ section4: handlers ------------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void handleSubmission(ActionEvent event) {
        boolean flag = addRating() && addReview();
        String alertTitle = "Submitting feedback";
        if (flag) {
            Alerts.informationAlert(alertTitle, null, "Submitting your feedback successfully. Thank you :)");
            CustomerStageHelper.showCustomerOrders(event);
        } else {
            Alerts.errorAlert(alertTitle, null, "Error while submitting the feedback, please try again.");
        }
    }

    private void showGoldStars(int numberOfStars) {
        if (numberOfStars == 5)
            goldStar5.setVisible(true);
        if (numberOfStars >= 4)
            goldStar4.setVisible(true);
        if (numberOfStars >= 3)
            goldStar3.setVisible(true);
        if (numberOfStars >= 2)
            goldStar2.setVisible(true);
        if (numberOfStars >= 1)
            goldStar1.setVisible(true);
    }

    private void handleRatingChange(Event event) {
        hideAllGoldStars();
        Node source = (Node) event.getSource();
        if (source == blackStar5) {
            rating = 5;
        } else if (source == blackStar4) {
            rating = 4;
        } else if (source == blackStar3) {
            rating = 3;
        } else if (source == blackStar2) {
            rating = 2;
        } else if (source == blackStar1) {
            rating = 1;
        } else {
            rating = 0;
        }
        showGoldStars(rating);
    }
}
