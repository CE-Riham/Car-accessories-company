package controllers.admin_controllers.customers;

import cards.UserCard;
import classes.DBConnector;
import classes.UserSession;
import controllers.admin_controllers.AdminNavBarActions;
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

public class AdminCustomersController extends AdminNavBarActions implements Initializable {

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
        activateMenuButton(customersButton);
        fillCustomers();
    }

    private void getAllCustomersFromDB() {
        RetrievingUser usersRetriever = new RetrievingUser(DBConnector.getConnector().getCon());
        allCustomers = usersRetriever.findUserByUserType("customer");
    }

    private void fillRow1(int index) {
        row1.getChildren().clear();
        for (int i = index; i < (index + 6) && i < allCustomers.size(); i++) {
            row1.getChildren().add(new UserCard(allCustomers.get(i)).getCard());
        }
        disableButton(prevButton, (index == 0));
    }

    private void fillRow2(int index) {
        row2.getChildren().clear();
        for (int i = index; i < (index + 6) && i < allCustomers.size(); i++) {
            row2.getChildren().add(new UserCard(allCustomers.get(i)).getCard());
        }
        disableButton(nextButton, ((index + 6) > allCustomers.size()));
    }

    private void fillCustomers() {
        getAllCustomersFromDB();
        fillRow1(pageNumber * 12);
        fillRow2(pageNumber * 12 + 6);
    }

}