package Controllers;

import javafx.fxml.FXML;
import javax.swing.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    protected void onLoginClick() {
        //TODO
        JOptionPane.showMessageDialog(null, "Hello!");

    }
    @FXML
    protected void onSignupClick(){
        //TODO
    }
    @FXML
    protected void onForgetPasswordClick(){
        //TODO
    }
}
