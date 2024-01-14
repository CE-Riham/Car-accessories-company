package controllers.admin_controllers.users;

import classes.DBConnector;
import classes.Starter;
import classes.UserSession;
import controllers.admin_controllers.AdminNavBarActions;
import database.deleting.UserDeleter;
import helpers.Alerts;
import helpers.stage_helpers.AdminStageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.User;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserProfileController extends AdminNavBarActions implements Initializable {
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Circle profilePicture;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField countryTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField streetTextField;

    private void setProfilePicture() {
        File file = new File(UserSession.getUserToDisplay().getImagePath());
        String localUrl = file.toURI().toString();
        Image image = new Image(localUrl);
        profilePicture.setFill(new ImagePattern(image));
    }

    private void getProfileFromDB() {
        setProfilePicture();
        firstName.setText(UserSession.getUserToDisplay().getFirstName());
        lastName.setText(UserSession.getUserToDisplay().getLastName());
        User tmpUser = UserSession.getUserToDisplay();
        firstNameTextField.setText(tmpUser.getFirstName());
        lastNameTextField.setText(tmpUser.getLastName());
        usernameTextField.setText(tmpUser.getUsername());
        phoneTextField.setText(tmpUser.getPhoneNumber());
        emailTextField.setText(tmpUser.getEmail());
        //TODO
        //add address
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        String title = "Delete user";
        if (UserSession.getUserToDisplay().getUsername().equals(UserSession.getCurrentUser().getUsername())) {
            Alerts.errorAlert(title, null, "You can't delete your profile!");
            return;
        }
        Optional<ButtonType> result = Alerts.confirmationAlert(title, "Are you sure you want to delete this user?");
        if ((result.isPresent() && result.get() == ButtonType.OK)) {
            UserDeleter userDeleter = new UserDeleter(DBConnector.getConnector().getCon());
            boolean flag = userDeleter.deleteUserFromUsersTableByUsername(UserSession.getUserToDisplay());
            if (flag) {
                Alerts.informationAlert(title, null, userDeleter.getStatus());
                if (UserSession.getUserToDisplay().getUserType().equals("admin"))
                    AdminStageHelper.showAdminAdmins(event);
                else if (UserSession.getUserToDisplay().getUserType().equals("installer")) {
                    //TODO
                    //go to installers page //
                } else
                    AdminStageHelper.showAdminCustomers(event);

            } else
                Alerts.errorAlert(title, null, userDeleter.getStatus());
        }
    }

    @FXML
    void onSendMessageClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getProfileFromDB();
    }
}