package Controllers;

import Classes.Starter;
import helpers.Alerts;
import helpers.StageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.User;

import java.util.Optional;

public class UserController {
    @FXML
    private TextField addressTextField;

    @FXML
    private Button cancelProfileButton;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Button changePictureButton;

    @FXML
    private Button editProfileButton;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label firstName;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private Button installersButton;

    @FXML
    private Label lastName;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Button ordersButton;

    @FXML
    private TextField phoneTextField;

    @FXML
    private AnchorPane profile;

    @FXML
    private Circle profilePicture;

    @FXML
    private Button saveProfileButton;

    @FXML
    private Button userNameButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    void onCancelProfileClick(ActionEvent event) {
        setProfileEditable(false);
        saveProfileButton.setVisible(false);
        cancelProfileButton.setVisible(false);
        editProfileButton.setVisible(true);
        changePictureButton.setVisible(false);
        changePasswordButton.setVisible(true);
        getProfileFromDB();
    }

    @FXML
    void onChangePasswordClick(ActionEvent event) {
        setProfileEditable(false);

    }

    @FXML
    void onChangePictureClick(ActionEvent event) {

    }

    @FXML
    void onEditProfileClick(ActionEvent event) {
        changePictureButton.setVisible(true);
        changePasswordButton.setVisible(false);
        editProfileButton.setVisible(false);
        saveProfileButton.setVisible(true);
        cancelProfileButton.setVisible(true);
        setProfileEditable(true);
    }

    @FXML
    void onInstallersClick(ActionEvent event) {
        disableAllMenuButtonsExcept(installersButton);

    }

    @FXML
    void onLogoutClick(ActionEvent event) {
        Optional<ButtonType> result = Alerts.confirmationMessage("Logout", "Are you sure you want to logout?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.out.println("Logout");
            Starter.sessionManager.invalidateSession(Starter.userSession.getSessionId());
            Starter.userSession = null;
            StageHelper.showLogin(event);
        }
        else
            System.out.println("Logout canceled.");
    }

    @FXML
    void onNotificationsCLick(ActionEvent event) {
        System.out.println("Notifications");

    }

    @FXML
    void onOrdersClick(ActionEvent event) {
        disableAllMenuButtonsExcept(ordersButton);

    }

    @FXML
    void onSaveProfileClick(ActionEvent event) {
        setProfileEditable(false);
        saveProfileButton.setVisible(false);
        cancelProfileButton.setVisible(false);
        editProfileButton.setVisible(true);
        changePictureButton.setVisible(false);
        changePasswordButton.setVisible(true);
    }

    @FXML
    void onUserNameClick(ActionEvent event) {
        User tmpUser = Starter.userSession;
        disableAllMenuButtonsExcept(userNameButton);
        disableAllPanesExcept(profile);
        firstName.setText(tmpUser.getFirstName());
        lastName.setText(tmpUser.getLastName());
        Image image = new Image(getClass().getResourceAsStream(tmpUser.getImagePath()));
        profilePicture.setFill(new ImagePattern(image));
        getProfileFromDB();
        System.out.println("Profile was opened successfully :)");
    }
    private void disableAllMenuButtonsExcept(Button button){
        userNameButton.setStyle("-fx-border-color: transparent;");
        installersButton.setStyle("-fx-border-color: transparent;");
        ordersButton.setStyle("-fx-border-color: transparent;");
        button.setStyle("-fx-border-color: #C9B3AD;");
    }

    private void disableAllPanesExcept(AnchorPane pane){
        profile.setVisible(false);
        pane.setVisible(true);
    }

    private void setProfileEditable(boolean flag){
        firstNameTextField.setEditable(flag);
        lastNameTextField.setEditable(flag);
        usernameTextField.setEditable(flag);
        phoneTextField.setEditable(flag);
        emailTextField.setEditable(flag);
        addressTextField.setEditable(flag);
    }

    private void getProfileFromDB(){
        User tmpUser = Starter.userSession;
        //admin data
        firstNameTextField.setText(tmpUser.getFirstName());
        lastNameTextField.setText(tmpUser.getLastName());
        usernameTextField.setText(tmpUser.getUsername());
        phoneTextField.setText(tmpUser.getPhoneNumber());
        emailTextField.setText(tmpUser.getEmail());
        //TODO
        //add address
    }
}
