package controllers.admin_controllers.admins;

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

public class AdminAdminsController extends AdminNavBarActions implements Initializable {

    private List<User> allAdmins;
    private int pageNumber = 0;
    @FXML
    private Button adminsButton;
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
        fillAdmins();
    }

    @FXML
    void onNextButtonClick(ActionEvent event) {
        pageNumber++;
        fillAdmins();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activateMenuButton(adminsButton);
        fillAdmins();
    }

    private void getAllAdminsFromDB() {
        RetrievingUser usersRetriever = new RetrievingUser(DBConnector.getConnector().getCon());
        allAdmins = usersRetriever.findUserByUserType("admin");
    }

    private void fillRow1(int index) {
        row1.getChildren().clear();
        for (int i = index; i < (index + 6) && i < allAdmins.size(); i++) {
            row1.getChildren().add(new UserCard(allAdmins.get(i)).getCard());
        }
        disableButton(prevButton, (index == 0));
    }

    private void fillRow2(int index) {
        row2.getChildren().clear();
        for (int i = index; i < (index + 6) && i < allAdmins.size(); i++) {
            row2.getChildren().add(new UserCard(allAdmins.get(i)).getCard());
        }
        disableButton(nextButton, ((index + 6) > allAdmins.size()));
    }

    private void fillAdmins() {
        getAllAdminsFromDB();
        fillRow1(pageNumber * 12);
        fillRow2(pageNumber * 12 + 6);
    }
}