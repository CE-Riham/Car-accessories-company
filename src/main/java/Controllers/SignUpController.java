package Controllers;

import Classes.Starter;
import authentication.Register;
import helpers.DataValidation;
import helpers.StageHelper;
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
import java.sql.*;


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
        StageHelper.showLogin(event);
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
        JOptionPane.showMessageDialog(null, status, "ERROR!", JOptionPane.INFORMATION_MESSAGE);
StageHelper.showHomePage(event);
    }
}

