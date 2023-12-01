package controllers;//import animatefx.animation.FadeIn;

import classes.DBConnector;
import classes.Starter;
import authentication.Login;
import helpers.StageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.SQLException;

public class LoginController {
    private Login login;
    public LoginController(){
        login = new Login(DBConnector.getConnector().getCon());
    }

    @FXML
    public TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    protected void onLoginClick(ActionEvent event) throws SQLException {
        //TODO
        //next page
        String username = usernameTextField.getText().toLowerCase();
        String password = passwordTextField.getText();
        boolean flag = login.loginUser(username, password);
        Starter.logger.info(login.getStatus());

        if(!flag)
            JOptionPane.showMessageDialog(null, login.getStatus(), "ERROR!", JOptionPane.ERROR_MESSAGE);
        else {
            StageHelper.showAdmin(event);
        }

    }

    @FXML
    void onSignupClick(ActionEvent event) {
        StageHelper.showSignup(event);
    }
    @FXML
    protected void onForgetPasswordClick(){
        //TODO
    }

}