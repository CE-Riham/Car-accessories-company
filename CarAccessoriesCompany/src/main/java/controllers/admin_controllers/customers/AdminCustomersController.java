package controllers.admin_controllers.customers;

import cards.UserCard;
import classes.DBConnector;
import classes.UserSession;
import database.retrieving.RetrievingUser;
import helpers.stage_helpers.AdminStageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import model.User;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminCustomersController implements Initializable {

    private List<User> allCustomers;
    private int pageNumber = 0;
    @FXML
    private Button customersButton;
    @FXML
    private Button prevButton;
    @FXML
    private Button nextButton;

    @FXML
    private HBox row1;

    @FXML
    private HBox row2;
    private void activateMenuButton(){
        customersButton.setStyle("-fx-border-color: #C9B3AD;");
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
    void onDashboardClick(ActionEvent event) {
        AdminStageHelper.showAdminDashboard(event);
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
    void onPrevButtonClick(ActionEvent event) {
        pageNumber--;
        fillCustomers();
    }

    @FXML
    void onNextButtonClick(ActionEvent event) {
        pageNumber++;
        fillCustomers();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activateMenuButton();
        fillCustomers();
    }

    private void getAllCustomersFromDB(){
        RetrievingUser usersRetriever = new RetrievingUser(DBConnector.getConnector().getCon());
        allCustomers = usersRetriever.findUserByUserType("customer");
    }

    private void fillRow1(int index){
        row1.getChildren().clear();
        for(int i = index; i<(index + 6) && i< allCustomers.size(); i++){
            row1.getChildren().add(new UserCard(allCustomers.get(i)).getCard());
        }
        disableButton(prevButton, (index == 0));
    }
    private void fillRow2(int index){
        row2.getChildren().clear();
        for(int i = index; i<(index + 6) && i< allCustomers.size(); i++){
            row2.getChildren().add(new UserCard(allCustomers.get(i)).getCard());
        }
        disableButton(nextButton, ((index + 6) > allCustomers.size()));
    }
    private void fillCustomers(){
        getAllCustomersFromDB();
        fillRow1(pageNumber*12);
        fillRow2(pageNumber * 12 + 6);
    }

    private void disableButton(Button button, boolean flag){
        button.setDisable(flag);
    }

}
