package controllers.autintication_controllers;

import authentication.Login;
import classes.DBConnector;
import classes.Starter;
import classes.UserSession;
import helpers.Alerts;
import helpers.stage_helpers.AdminStageHelper;
import helpers.stage_helpers.AuthenticationStageHelper;
import helpers.stage_helpers.CustomerStageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section1: Class attributes -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private Login login;
    @FXML
    public TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;


    // -------------------------------------------------------------------------------------------------------------------- //
    // ---------------------------------------------- section2: Constructors ---------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    public LoginController() {
        login = new Login(DBConnector.getConnector().getCon());
    }


    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section3: Page button actions ------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    @FXML
    protected void onLoginClick(ActionEvent event) {
        handleLogin(event);
    }

    @FXML
    void onSignupClick(ActionEvent event) {
        AuthenticationStageHelper.showSignup(event);
    }

    @FXML
    protected void onForgetPasswordClick() {
        handleForgetPassword();
    }


    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section4: Helper functions -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private boolean checkUserInformation() {
        String username = usernameTextField.getText().toLowerCase();
        String password = passwordTextField.getText();
        return login.loginUser(username, password);
    }

    private void openNextPage(String userType, ActionEvent event) {
        //TODO
        //complete pages
        if (userType.equals("admin"))
            AdminStageHelper.showAdminProfile(event);
        else if (userType.equals("customer"))
            CustomerStageHelper.showCustomerHomePage(event);
        else
            Starter.logger.info("installer");
    }

    private void handleLogin(ActionEvent event) {
        boolean flag = checkUserInformation();
        Starter.logger.info(login.getStatus());
        if (Boolean.FALSE.equals(flag))
            Alerts.errorAlert("Error", null, login.getStatus());
        else
            openNextPage(UserSession.getCurrentUser().getUserType(), event);
    }

    private void handleForgetPassword() {
        //TODO
    }

}