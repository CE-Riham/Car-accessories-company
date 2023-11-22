package com.example.newproj;

import animatefx.animation.FadeIn;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.*;
import javafx.scene.control.TextField;


public class SignUpController {
    static Logger logger = Logger.getLogger(SignUpController.class.getName());

    @FXML
    private TextField ID;

    @FXML
    private TextField VIN;

    @FXML
    private TextField address;

    @FXML
    private Button back;

    @FXML
    private TextField creditCard;

    @FXML
    private TextField gmail;

    @FXML
    private TextField message;

    @FXML
    private PasswordField pass;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button signUp;

    @FXML
    private TextField userName;


    @FXML
    void backClicked(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            Stage stage = (Stage) back.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (IOException e){
            logger.log(null," An error occurred while opening a new window:");
        }
    }
    private String em="ERROR";

    @FXML
    void signUp1Clicked(ActionEvent event) {
        try {
            if (!TESTINPUT.idTest(ID.getText())) {
                JOptionPane.showMessageDialog(null, "wrong id !", em, JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!TESTINPUT.passwordTest(pass.getText())) {
                JOptionPane.showMessageDialog(null, "wrong PASSWORD !", em, JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!TESTINPUT.phoneNumberTest(phoneNumber.getText())) {
                JOptionPane.showMessageDialog(null, "wrong PHONE NUMBER !", em, JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!TESTINPUT.gmailTest(gmail.getText())) {
                JOptionPane.showMessageDialog(null, "wrong GMAIL !", em, JOptionPane.ERROR_MESSAGE);
                return;
            } else if (ID.getText().isEmpty() ||userName.getText().isEmpty() || address.getText().isEmpty()|| phoneNumber.getText().isEmpty() || gmail.getText().isEmpty() || VIN.getText().isEmpty() || pass.getText().isEmpty()||message.getText().isEmpty()||creditCard.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Field is Empty", em, JOptionPane.ERROR_MESSAGE);
                return;
            }
            ResultSet rs = Database.createDatabase("select ID,user_name,Password from customer");
            while (rs.next()) {
                String idup = rs.getString(1);
                String usernameup = rs.getString(2);
                if (idup.equals(ID.getText())) {
                    JOptionPane.showMessageDialog(null, "The ID is already contains", em, JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (usernameup.equals(userName.getText())) {
                    JOptionPane.showMessageDialog(null, "The USERNAME is already contains", em, JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            Database.insertIntoDatabase("INSERT INTO customer values('" + phoneNumber.getText() + "','" + VIN.getText() + "','" + creditCard.getText() + "','" + address.getText() + "','" + gmail.getText() + "','" + userName.getText()+ "','" + pass.getText() + "','" + message.getText()+ "','" + ID.getText()+ "')");
            JOptionPane.showMessageDialog(null, "DONE ", "INSERTED", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            logger.log(null,"Database connection error: ");
        }
        try{
            Parent root;
            root = FXMLLoader.load(getClass().getResource("screen3.fxml"));
            Stage stage = (Stage) signUp.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            new FadeIn(root).play();
        }catch (IOException e){
            logger.log(null," An error occurred while opening a new window:");
        }
    }
    }

