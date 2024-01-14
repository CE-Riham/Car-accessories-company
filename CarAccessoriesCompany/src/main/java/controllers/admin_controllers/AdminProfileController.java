package controllers.admin_controllers;

import classes.DBConnector;
import classes.UserSession;
import database.updating.UserUpdater;
import helpers.Alerts;
import helpers.DataValidation;
import helpers.Uploader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

public class AdminProfileController extends AdminNavBarActions implements Initializable {
    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section1: Class attributes -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private UserUpdater dataUpdater;
    private Uploader uploader;
    @FXML
    private Button adminProfileButton;
    @FXML
    private Button changePictureButton;
    @FXML
    private Button editProfileButton;
    @FXML
    private Button changePasswordButton;
    @FXML
    private Button saveProfileButton;
    @FXML
    private Button cancelProfileButton;
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


    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section2: Page button actions ------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    @FXML
    void onChangePictureClick(ActionEvent event) {
        handleChangePicture();
    }

    @FXML
    void onEditProfileClick(ActionEvent event) {
        handleEditProfile();
    }

    @FXML
    void onChangePasswordClick(ActionEvent event) {
        handleChangePassword();
    }

    @FXML
    void onCancelProfileClick(ActionEvent event) {
        openProfile();
    }

    @FXML
    void onSaveProfileClick(ActionEvent event) {
        handleSaveProfileClick();
    }


    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section3: Initialising actions ------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        uploader = new Uploader();
        dataUpdater = new UserUpdater(DBConnector.getConnector().getCon());
        activateMenuButton(adminProfileButton);
        openProfile();
    }


    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section4: Helper functions -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void setEditable(boolean flag, TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setEditable(flag);
        }
    }

    private void setProfileEditable(boolean flag) {
        setEditable(flag, firstNameTextField, lastNameTextField, phoneTextField, emailTextField,
                countryTextField, cityTextField, streetTextField);
    }

    private void setText(Label label, String text) {
        label.setText(text);
    }

    private void setText(TextField textField, String text) {
        textField.setText(text);
    }

    private void setProfilePicture(String imagePath) {
        File file = new File(imagePath);
        String localUrl = file.toURI().toString();
        Image image = new Image(localUrl);
        profilePicture.setFill(new ImagePattern(image));
    }

    private void setVisible(boolean flag, Button... buttons) {
        for (Button button : buttons)
            button.setVisible(flag);
    }

    private void getProfileFromDB() {
        User tmpUser = UserSession.getCurrentUser();
        setProfilePicture(tmpUser.getImagePath());
        setText(firstName, tmpUser.getFirstName());
        setText(lastName, tmpUser.getLastName());
        setText(firstNameTextField, tmpUser.getFirstName());
        setText(lastNameTextField, tmpUser.getLastName());
        setText(usernameTextField, tmpUser.getUsername());
        setText(phoneTextField, tmpUser.getPhoneNumber());
        setText(emailTextField, tmpUser.getEmail());
        //TODO
        //add address
    }

    private void initializeProfileVisibility() {
        setProfileEditable(false);
        setVisible(false, saveProfileButton, cancelProfileButton, changePictureButton);
        setVisible(true, editProfileButton, changePasswordButton);
    }

    private void openProfile() {
        getProfileFromDB();
        initializeProfileVisibility();
    }

    private void handleChangePicture() {
        String savePath = "src/main/resources/assets/usersPictures/" + UserSession.getCurrentUser().getUsername() + ".png";
        boolean uploadImageFlag = uploader.uploadImage();
        if (uploadImageFlag) {
            boolean savingFlag = uploader.saveToFile(savePath, true);
            if (savingFlag) {
                //save the new photo in database
                User user = UserSession.getCurrentUser();
                user.setImagePath(savePath);
                String condition = " where username = \'" + UserSession.getCurrentUser().getUsername() + "\';";
                boolean successfulUpdate = dataUpdater.updateUsersAllFields(user, condition);
                if (successfulUpdate) {
                    UserSession.setCurrentUser(user);
                    Alerts.informationAlert("Change picture", null, "Picture was updated successfully.");
                    openProfile();
                }
            }
        }
    }

    private void handleEditProfile() {
        changePictureButton.setVisible(true);
        changePasswordButton.setVisible(false);
        editProfileButton.setVisible(false);
        saveProfileButton.setVisible(true);
        cancelProfileButton.setVisible(true);
        setProfileEditable(true);
    }

    private void handleChangePassword() {
        setProfileEditable(false);
        String header = """
                - at least 8 characters
                - must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number
                - Can contain special characters""";
        Optional<String> result = Alerts.withInputAlert("Change password", header, "Password: ");
        result.ifPresent(password -> {
            if (DataValidation.passwordValidationTest(password)) {
                String condition = "where username = \'" + UserSession.getCurrentUser().getUsername() + "\'";
                boolean flag = dataUpdater.updateUserPassword(password, condition);
                if (flag) {
                    Alerts.informationAlert("Update", null, "password was updated successfully.");
                    UserSession.getCurrentUser().setPassword(password);
                }
            } else {
                Alerts.errorAlert("Error", null, "Invalid password.");
            }
        });
    }

    private User prepareAdminToUpdate() {
        //TODO
        //edit address
        String email = emailTextField.getText();
        String firstNameTmp = firstNameTextField.getText();
        String lastNameTmp = lastNameTextField.getText();
        String phone = phoneTextField.getText();
        return new User(UserSession.getCurrentUser().getUsername(), firstNameTmp, lastNameTmp, phone,
                UserSession.getCurrentUser().getPassword(), email, UserSession.getCurrentUser().getImagePath(),
                "admin", UserSession.getCurrentUser().getAddress());
    }

    private void showUpdateProfileResult(Boolean successfulUpdate, User user) {
        String status = dataUpdater.getStatus();
        if (Boolean.TRUE.equals(successfulUpdate)) {
            Alerts.informationAlert("Update", null, status);
            UserSession.setCurrentUser(user);
        } else
            Alerts.errorAlert("Error", null, status);
    }

    private void handleSaveProfileClick() {
        User user = prepareAdminToUpdate();
        String condition = "where username = \'" + usernameTextField.getText() + "\';";
        showUpdateProfileResult(dataUpdater.updateUsersAllFields(user, condition), user);
        openProfile();
    }

}