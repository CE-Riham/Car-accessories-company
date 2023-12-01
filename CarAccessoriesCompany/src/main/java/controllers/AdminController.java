package controllers;

import authentication.UserSessionManager;
import classes.DBConnector;
import classes.Starter;
import classes.UserSession;
import database.UpdatingData;
import helpers.Alerts;
import helpers.DataValidation;
import helpers.StageHelper;
import helpers.Uploader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.User;

import java.util.Optional;

public class AdminController {
    private UpdatingData dataUpdater;
    private Uploader uploader;
    public AdminController(){
        uploader = new Uploader();
        dataUpdater = new UpdatingData(DBConnector.getConnector().getCon());
    }
    @FXML
    private Button adminNameButton;
    @FXML
    private Button adminsButton;
    @FXML
    private Button customersButton;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button installersButton;
    @FXML
    private Button ordersButton;

    @FXML
    private Button productsButton;
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
    private AnchorPane profile;

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

    //done
    private void disableAllMenuButtonsExcept(Button button){
        String style = "-fx-border-color: transparent;";
        adminNameButton.setStyle(style);
        adminsButton.setStyle(style);
        customersButton.setStyle(style);
        dashboardButton.setStyle(style);
        installersButton.setStyle(style);
        ordersButton.setStyle(style);
        productsButton.setStyle(style);
        button.setStyle("-fx-border-color: #C9B3AD;");
    }

    private void disableAllPanesExcept(AnchorPane pane){
        profile.setVisible(false);
        pane.setVisible(true);
    }

    //done
    private void setProfileEditable(boolean flag){
        firstNameTextField.setEditable(flag);
        lastNameTextField.setEditable(flag);
        phoneTextField.setEditable(flag);
        emailTextField.setEditable(flag);
        countryTextField.setEditable(flag);
        cityTextField.setEditable(flag);
        streetTextField.setEditable(flag);
    }

    private void getProfileFromDB(){
        firstName.setText(UserSession.getCurrentUser().getFirstName());
        lastName.setText(UserSession.getCurrentUser().getLastName());
        Image image = new Image(getClass().getResourceAsStream(UserSession.getCurrentUser().getImagePath()));
        profilePicture.setFill(new ImagePattern(image));
        //TODO
        //refresh picture
        User tmpUser = UserSession.getCurrentUser();
        //admin data
        firstNameTextField.setText(tmpUser.getFirstName());
        lastNameTextField.setText(tmpUser.getLastName());
        usernameTextField.setText(tmpUser.getUsername());
        phoneTextField.setText(tmpUser.getPhoneNumber());
        emailTextField.setText(tmpUser.getEmail());
        //TODO
        //add address
    }

    //done
    @FXML
    void onAdminNameClick(ActionEvent event) {
        disableAllMenuButtonsExcept(adminNameButton);
        disableAllPanesExcept(profile);
        getProfileFromDB();
        Starter.logger.info("Profile was opened successfully :)");
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
        Starter.logger.info("beeb");
    }

    //done
    @FXML
    void onLogoutClick(ActionEvent event) {
        Optional<ButtonType> result = Alerts.confirmationAlert("Logout", "Are you sure you want to logout?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Starter.logger.info("Logout");
            UserSessionManager.invalidateSession(UserSession.getSessionId());
            UserSession.setSessionId(null);
            StageHelper.showLogin(event);
        }
        else
            Starter.logger.info("Logout was canceled.");
    }

    @FXML
    void onChangePictureClick(ActionEvent event) {
        //TODO
        String savePath = "src/main/resources/assets/usersPictures/"+ UserSession.getCurrentUser().getUsername() + ".png";
        boolean uploadImageFlag = uploader.uploadImage();
        if(uploadImageFlag){
            boolean savingFlag = uploader.saveToFile(savePath);
            if(savingFlag){
                //save the new photo in database
                String imagePath = savePath.substring(18);
                User user = UserSession.getCurrentUser();
                user.setImagePath(imagePath);
                String condition = "where username = \'" + UserSession.getCurrentUser().getUsername() + "\';";
                dataUpdater.updateUser(user, condition);
                String status = dataUpdater.getStatus();
                if(status.equals("User was updated successfully")){
                    UserSession.setCurrentUser(user);
                    Alerts.informationAlert("Update", null, "Image was updated successfully.");
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
            if(DataValidation.passwordValidationTest(password)){
                User user = UserSession.getCurrentUser();
                user.setPassword(password);
                String condition = "where username = \'" + UserSession.getCurrentUser().getUsername() + "\';";
                dataUpdater.updateUser(user, condition);
                String status = dataUpdater.getStatus();
                if(status.equals("User was updated successfully")){
                    Alerts.informationAlert("Update", null, "password was updated successfully.");
                    UserSession.setCurrentUser(user);
                }
            }
            else{
                Alerts.errorAlert("Error", null, "Invalid password.");
            }
        });
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
        //TODO
        //edit address
        setProfileEditable(false);
        saveProfileButton.setVisible(false);
        cancelProfileButton.setVisible(false);
        editProfileButton.setVisible(true);
        changePictureButton.setVisible(false);
        changePasswordButton.setVisible(true);

        //update admin information
        String email = emailTextField.getText();
        String firstNameTmp = firstNameTextField.getText();
        String lastNameTmp = lastNameTextField.getText();
        String phone = phoneTextField.getText();
        String condition = "where username = \'" + usernameTextField.getText() + "\';";
        User user = new User(UserSession.getCurrentUser().getUsername(), firstNameTmp, lastNameTmp, phone,
                UserSession.getCurrentUser().getPassword(), email, UserSession.getCurrentUser().getImagePath(),
                "admin", UserSession.getCurrentUser().getAddress());

        dataUpdater.updateUser(user, condition);

        //result
        String status = dataUpdater.getStatus();
        if(status.equals("User was updated successfully")){
            Alerts.informationAlert("Update", null, status);
            UserSession.setCurrentUser(user);
        }
        else
            Alerts.errorAlert("Error", null, status);
        getProfileFromDB();
    }

}
