package Controllers;//import animatefx.animation.FadeIn;

import Classes.Starter;
import authentication.Login;
import helpers.StageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;

public class LoginController {
    private Login login;
    public LoginController(){
        login = new Login(Starter.connector.getCon());
    }

    @FXML
    public TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    protected void onLoginClick(ActionEvent event) {
        //TODO
        //next page
        String username = usernameTextField.getText().toLowerCase(), password = passwordTextField.getText();
        boolean flag = login.loginUser(username, password);
       // System.out.println(flag);
        System.out.println(login.getStatus());

        if(!flag)
            JOptionPane.showMessageDialog(null, login.getStatus(), "ERROR!", JOptionPane.ERROR_MESSAGE);
        else {
            StageHelper.showHomePage(event);
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