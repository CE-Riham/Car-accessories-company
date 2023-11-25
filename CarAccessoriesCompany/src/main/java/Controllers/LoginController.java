package Controllers;//import animatefx.animation.FadeIn;

import Classes.Starter;
import authentication.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
        //TODO
        try{
            Parent root;
            System.out.println("D0");
            root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            System.out.println("D1");
//            Stage stage = (Stage) signUp.getScene().getWindow();
//            System.out.println("D2");
//            stage.setScene(new Scene(root));
//            System.out.println("D3");
//            stage.show();
            System.out.println("D4");
//            new FadeIn(root).play();
            System.out.println("D5");
        }catch (Exception e){
        }
    }
    @FXML
    protected void onForgetPasswordClick(){
        //TODO
    }

}