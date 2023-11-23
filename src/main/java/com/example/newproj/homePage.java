package com.example.newproj;

import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;
public class homePage {



    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<?> categoryComboBox;

    @FXML
    private TextArea productTextArea;

    @FXML
    void showProducts(ActionEvent event) {
        String searchText = searchField.getText();
        String selectedCategory = (String) categoryComboBox.getValue();

        if (searchText == null || searchText.trim().isEmpty()) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql","root","Vqo@954719")) {
                // Prepare the SQL query
                String sql = "SELECT * FROM Product WHERE Category = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, selectedCategory);

                    // Execute the query
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Process the results
                        StringBuilder resultText = new StringBuilder();
                        while (resultSet.next()) {
                            int productId = resultSet.getInt("ProductId");
                            String name = resultSet.getString("Name");
                            String description = resultSet.getString("Description");
                            double price = resultSet.getDouble("Price");
                            boolean availability = resultSet.getBoolean("Availability");

                            resultText.append(String.format("Product ID: %d\nName: %s\nDescription: %s\nPrice: %.2f\nAvailability: %b\n\n",
                                    productId, name, description, price, availability));
                        }

                        // Update the productTextArea with the results
                        productTextArea.setText(resultText.toString());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql","root","Vqo@954719")) {
                // Prepare the SQL query
                String sql = "SELECT * FROM Product WHERE Category = ? AND (Name LIKE ? OR Description LIKE ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, selectedCategory);
                    preparedStatement.setString(2, "%" + searchText + "%");  // Search by name
                    preparedStatement.setString(3, "%" + searchText + "%");  // Search by description

                    // Execute the query
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        // Process the results
                        StringBuilder resultText = new StringBuilder();
                        while (resultSet.next()) {
                            int productId = resultSet.getInt("ProductId");
                            String name = resultSet.getString("Name");
                            String description = resultSet.getString("Description");
                            double price = resultSet.getDouble("Price");
                            boolean availability = resultSet.getBoolean("Availability");

                            resultText.append(String.format("Product ID: %d\nName: %s\nDescription: %s\nPrice: %.2f\nAvailability: %b\n\n",
                                    productId, name, description, price, availability));
                        }

                        // Update the productTextArea with the results
                        productTextArea.setText(resultText.toString());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
