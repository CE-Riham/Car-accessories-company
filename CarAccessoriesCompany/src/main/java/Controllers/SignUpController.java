package Controllers;

import Classes.Starter;
import authentication.Register;
import helpers.DataValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;

import javax.swing.*;


public class SignUpController {

    private Register userRegisterer;
    public SignUpController(){
        userRegisterer = new Register(Starter.connector.getCon());
    }
    @FXML
    private TextField emailTextField, firstNameTextField, lastNameTextField, phoneTextField, usernameTextField;

    @FXML
    private PasswordField passwordTextField;


    @FXML
    void onLoginClick(ActionEvent event) {
        try{
            if (event.getSource() instanceof Node) {
                Parent root = FXMLLoader.load(getClass().getResource("/FXMLFiles/login.fxml"));
                Node source = (Node) event.getSource();
                source.getScene().setRoot(root);
            }
        }catch (Exception e){
            System.out.println("Can't open login page");
        }
    }

    @FXML
    void onRegisterClick(ActionEvent event) {
        //TODO
        String email = emailTextField.getText(), firstName = firstNameTextField.getText(),
                lastName = lastNameTextField.getText(), phone = phoneTextField.getText(),
                username = usernameTextField.getText(), password = passwordTextField.getText();

        User user = new User(username, firstName, lastName, phone, password, email, "", null);
        System.out.println(DataValidation.userValidationTest(user));
        userRegisterer.registerUser(user);
        String status = userRegisterer.getStatus();
        System.out.println(status);
        JOptionPane.showMessageDialog(null, status, "ERROR!", JOptionPane.ERROR_MESSAGE);

//        try{
//            Parent root;
//            root = FXMLLoader.load(getClass().getResource("screen3.fxml"));
//            Stage stage = (Stage) signUp.getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.show();
////            new FadeIn(root).play();
//        }catch (IOException e){
//            logger.log(null," An error occurred while opening a new window:");
//        }
    }
    }

