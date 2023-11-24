package Controllers;

import Classes.Starter;
import authentication.Login;
import javafx.fxml.FXML;
import javax.swing.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Locale;

public class LoginController {
    private Login login;
    public LoginController(){
        login = new Login(Starter.connector.getCon());
    }

    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    protected void onLoginClick() {
        //TODO

        String username = usernameTextField.getText().toLowerCase(), password = passwordTextField.getText();
        boolean flag = login.loginUser(username, password);
        System.out.println(login.getStatus());

        if(!flag)
            JOptionPane.showMessageDialog(null, login.getStatus(), "ERROR!", JOptionPane.ERROR_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, login.getStatus(), "Ok", JOptionPane.DEFAULT_OPTION);

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
