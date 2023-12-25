package controllers;

import authentication.Register;
import classes.DBConnector;
import classes.Starter;
import helpers.Alerts;
import helpers.stage_helpers.AuthenticationStageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;

import java.sql.SQLException;


public class SignUpController {

    private Register userRegisterer;

    public SignUpController() {
        userRegisterer = new Register(DBConnector.getConnector().getCon());
    }

    @FXML
    private TextField emailTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;


    @FXML
    void onLoginClick(ActionEvent event) {
        AuthenticationStageHelper.showLogin(event);
    }

    @FXML
    void onRegisterClick(ActionEvent event) throws SQLException {
        //TODO
        String email = emailTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String phone = phoneTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        User user = new User(username, firstName, lastName, phone, password, email, "", "customer", null);
        userRegisterer.registerUser(user);
        String status = userRegisterer.getStatus();
        Starter.logger.info(status);
        if (status.equals("User was registered successfully")) {
            Alerts.informationAlert("Register user", null, status);
            AuthenticationStageHelper.showLogin(event);
        } else {
            Alerts.errorAlert("Register user", null, status);
        }

    }
}
