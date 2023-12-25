package controllers.admin_controllers;

import classes.DBConnector;
import classes.Starter;
import classes.UserSession;
import database.updating.UserUpdater;
import helpers.Alerts;
import helpers.DataValidation;
import helpers.Uploader;
import helpers.stage_helpers.AdminStageHelper;
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

public class AdminProfileController implements Initializable {
    private UserUpdater dataUpdater;
    private String successfulUpdate = "User was updated successfully";
    private Uploader uploader;

    public AdminProfileController() {
        uploader = new Uploader();
        dataUpdater = new UserUpdater(DBConnector.getConnector().getCon());
    }

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

    private void activateMenuButton() {
        adminProfileButton.setStyle("-fx-border-color: #C9B3AD;");
    }

    private void setProfileEditable(boolean flag) {
        firstNameTextField.setEditable(flag);
        lastNameTextField.setEditable(flag);
        phoneTextField.setEditable(flag);
        emailTextField.setEditable(flag);
        countryTextField.setEditable(flag);
        cityTextField.setEditable(flag);
        streetTextField.setEditable(flag);
    }

    private void setProfilePicture() {
        File file = new File(UserSession.getCurrentUser().getImagePath());
        String localUrl = file.toURI().toString();
        Image image = new Image(localUrl);
        profilePicture.setFill(new ImagePattern(image));
    }

    private void getProfileFromDB() {
        setProfilePicture();
        firstName.setText(UserSession.getCurrentUser().getFirstName());
        lastName.setText(UserSession.getCurrentUser().getLastName());
        User tmpUser = UserSession.getCurrentUser();
        //admin data
        firstNameTextField.setText(tmpUser.getFirstName());
        lastNameTextField.setText(tmpUser.getLastName());
        usernameTextField.setText(tmpUser.getUsername());
        phoneTextField.setText(tmpUser.getPhoneNumber());
        emailTextField.setText(tmpUser.getEmail());
        //TODO
        //add address

        setProfileEditable(false);
        saveProfileButton.setVisible(false);
        cancelProfileButton.setVisible(false);
        editProfileButton.setVisible(true);
        changePictureButton.setVisible(false);
        changePasswordButton.setVisible(true);
    }

    @FXML
    void onAdminProfileClick(ActionEvent event) {
        AdminStageHelper.showAdminProfile(event);
    }

    @FXML
    void onCategoriesClick(ActionEvent event) {
        AdminStageHelper.showAdminCategories(event);
    }

    @FXML
    void onOrdersClick(ActionEvent event) {
        //TODO
    }

    @FXML
    void onProductsClick(ActionEvent event) {
        AdminStageHelper.showAdminProducts(event);
    }

    @FXML
    void onCustomersClick(ActionEvent event) {
        AdminStageHelper.showAdminCustomers(event);
    }

    @FXML
    void onInstallersClick(ActionEvent event) {

    }

    @FXML
    void onAdminsClick(ActionEvent event) {
        AdminStageHelper.showAdminAdmins(event);
    }

    @FXML
    void onNotificationsCLick(ActionEvent event) {
        Starter.logger.info("beeb");
    }

    @FXML
    void onChangePictureClick(ActionEvent event) {
        String savePath = "src/main/resources/assets/usersPictures/" + UserSession.getCurrentUser().getUsername() + ".png";
        boolean uploadImageFlag = uploader.uploadImage();
        if (uploadImageFlag) {
            boolean savingFlag = uploader.saveToFile(savePath, true);
            if (savingFlag) {
                //save the new photo in database
                User user = UserSession.getCurrentUser();
                user.setImagePath(savePath);
                String condition = " where username = \'" + UserSession.getCurrentUser().getUsername() + "\';";
                dataUpdater.updateUsersAllFields(user, condition);
                String status = dataUpdater.getStatus();
                if (status.equals(successfulUpdate)) {
                    UserSession.setCurrentUser(user);
                    Alerts.informationAlert("Change picture", null, "Picture was updated successfully.");
                    getProfileFromDB();
                }
            }
        }
    }

    //done
    @FXML
    void onEditProfileClick(ActionEvent event) {
        changePictureButton.setVisible(true);
        changePasswordButton.setVisible(false);
        editProfileButton.setVisible(false);
        saveProfileButton.setVisible(true);
        cancelProfileButton.setVisible(true);
        setProfileEditable(true);
    }

    //done
    @FXML
    void onChangePasswordClick(ActionEvent event) {
        setProfileEditable(false);
        String header = """
                - at least 8 characters
                - must contain at least 1 uppercase letter, 1 lowercase letter, and 1 number
                - Can contain special characters""";
        Optional<String> result = Alerts.withInputAlert("Change password", header, "Password: ");
        result.ifPresent(password -> {
            if (DataValidation.passwordValidationTest(password)) {
                String condition = "where username = \'" + UserSession.getCurrentUser().getUsername() + "\';";
                dataUpdater.updateUserPassword(password, condition);
                String status = dataUpdater.getStatus();
                if (status.equals("User userPassword was updated successfully")) {
                    Alerts.informationAlert("Update", null, "password was updated successfully.");
                    UserSession.getCurrentUser().setPassword(password);
                }
            } else {
                Alerts.errorAlert("Error", null, "Invalid password.");
            }
        });
    }

    //done
    @FXML
    void onCancelProfileClick(ActionEvent event) {
        getProfileFromDB();
    }

    @FXML
    void onSaveProfileClick(ActionEvent event) {
        //TODO
        //edit address
        //update admin information
        String email = emailTextField.getText();
        String firstNameTmp = firstNameTextField.getText();
        String lastNameTmp = lastNameTextField.getText();
        String phone = phoneTextField.getText();
        String condition = "where username = \'" + usernameTextField.getText() + "\';";
        User user = new User(UserSession.getCurrentUser().getUsername(), firstNameTmp, lastNameTmp, phone,
                UserSession.getCurrentUser().getPassword(), email, UserSession.getCurrentUser().getImagePath(),
                "admin", UserSession.getCurrentUser().getAddress());

        dataUpdater.updateUsersAllFields(user, condition);

        //result
        String status = dataUpdater.getStatus();
        if (status.equals(successfulUpdate)) {
            Alerts.informationAlert("Update", null, status);
            UserSession.setCurrentUser(user);
        } else
            Alerts.errorAlert("Error", null, status);
        getProfileFromDB();

    }

    @FXML
    void onLogoutClick(ActionEvent event) {
        UserSession.logoutUser(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activateMenuButton();
        getProfileFromDB();
    }
}