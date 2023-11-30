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

public class AdminController {

    @FXML
    private Button adminNameButton, adminsButton, customersButton, dashboardButton, installersButton, ordersButton;

    @FXML
    private Button productsButton, changePictureButton, editProfileButton, changePasswordButton, saveProfileButton, cancelProfileButton;

    @FXML
    private Label firstName, lastName;

    @FXML
    private AnchorPane profile;

    @FXML
    private Circle profilePicture;

    @FXML
    private TextField firstNameTextField, lastNameTextField, usernameTextField,phoneTextField, emailTextField, countryTextField, cityTextField, streetTextField;

    private void disableAllMenuButtonsExcept(Button button){
        adminNameButton.setStyle("-fx-border-color: transparent;");
        adminsButton.setStyle("-fx-border-color: transparent;");
        customersButton.setStyle("-fx-border-color: transparent;");
        dashboardButton.setStyle("-fx-border-color: transparent;");
        installersButton.setStyle("-fx-border-color: transparent;");
        ordersButton.setStyle("-fx-border-color: transparent;");
        productsButton.setStyle("-fx-border-color: transparent;");
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
        countryTextField.setEditable(flag);
        cityTextField.setEditable(flag);
        streetTextField.setEditable(flag);
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
    private
    @FXML
    void onAdminNameClick(ActionEvent event) {
        User tmpUser = Starter.userSession;
        disableAllMenuButtonsExcept(adminNameButton);
        disableAllPanesExcept(profile);
        firstName.setText(tmpUser.getFirstName());
        lastName.setText(tmpUser.getLastName());
        Image image = new Image(getClass().getResourceAsStream(tmpUser.getImagePath()));
        profilePicture.setFill(new ImagePattern(image));
        getProfileFromDB();
        System.out.println("Profile was opened successfully :)");
    }



    @FXML
    void onDashboardClick(ActionEvent event) {
        disableAllMenuButtonsExcept(dashboardButton);
    }

    @FXML
    void onOrdersClick(ActionEvent event) {
        disableAllMenuButtonsExcept(ordersButton);
    }

    @FXML
    void onProductsClick(ActionEvent event) {
        disableAllMenuButtonsExcept(productsButton);
    }

    @FXML
    void onCustomersClick(ActionEvent event) {
        disableAllMenuButtonsExcept(customersButton);
    }

    @FXML
    void onInstallersClick(ActionEvent event) {
        disableAllMenuButtonsExcept(installersButton);
    }

    @FXML
    void onAdminsClick(ActionEvent event) {
        disableAllMenuButtonsExcept(adminsButton);
    }

    @FXML
    void onNotificationsCLick(ActionEvent event) {
        System.out.println("beeb");
    }

    //done
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
    void onChangePictureClick(ActionEvent event) {

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

    @FXML
    void onChangePasswordClick(ActionEvent event) {
        setProfileEditable(false);
    }

    //done
    @FXML
    void onCancelProfileClick(ActionEvent event){
        setProfileEditable(false);
        saveProfileButton.setVisible(false);
        cancelProfileButton.setVisible(false);
        editProfileButton.setVisible(true);
        changePictureButton.setVisible(false);
        changePasswordButton.setVisible(true);
        getProfileFromDB();
    }

    @FXML
    void onSaveProfileClick(ActionEvent event){
        setProfileEditable(false);
        saveProfileButton.setVisible(false);
        cancelProfileButton.setVisible(false);
        editProfileButton.setVisible(true);
        changePictureButton.setVisible(false);
        changePasswordButton.setVisible(true);
    }

}
