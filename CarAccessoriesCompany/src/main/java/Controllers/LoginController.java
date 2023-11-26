package Controllers;//import animatefx.animation.FadeIn;

import Classes.Starter;
import authentication.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    protected void onLoginClick() {
        //TODO
        //next page
        String username = usernameTextField.getText().toLowerCase(), password = passwordTextField.getText();
        boolean flag = login.loginUser(username, password);
        System.out.println(login.getStatus());

        if(!flag)
            JOptionPane.showMessageDialog(null, login.getStatus(), "ERROR!", JOptionPane.ERROR_MESSAGE);
        else
            JOptionPane.showMessageDialog(null, login.getStatus(), "Ok", JOptionPane.DEFAULT_OPTION);

    }

    @FXML
    void onSignupClick(ActionEvent event) {
        try{
            if (event.getSource() instanceof Node) {
                Parent root = FXMLLoader.load(getClass().getResource("/FXMLFiles/signup.fxml"));
                Node source = (Node) event.getSource();
                source.getScene().setRoot(root);
            }
        }catch (Exception e){
            System.out.println("Can't open sign-up page");
        }
    }
    @FXML
    protected void onForgetPasswordClick(){
        //TODO
    }

}