package Controllers;//import animatefx.animation.FadeIn;
import Classes.Starter;
import animatefx.animation.FadeIn;
import authentication.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import javax.swing.*;
import java.io.IOException;


public class LoginController {
    private Login login;
    public LoginController(){
        login = new Login(Starter.connector.getCon());
    }
    @FXML
    private Button login1;
    @FXML
    public TextField usernameTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    protected void onLoginClick() throws IOException {
        //TODO
        //next page
        String username = usernameTextField.getText().toLowerCase(), password = passwordTextField.getText();
        boolean flag = login.loginUser(username, password);
        System.out.println(login.getStatus());
Parent root;
        if(!flag)
            JOptionPane.showMessageDialog(null, login.getStatus(), "ERROR!", JOptionPane.ERROR_MESSAGE);
        else
        {
            JOptionPane.showMessageDialog(null, login.getStatus(), "Ok", JOptionPane.DEFAULT_OPTION);
            URL url = getClass().getResource("/FXMLFiles/homePage.fxml");
            root = FXMLLoader.load(url);
            Stage stage = (Stage) login1.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
            return;
        }

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