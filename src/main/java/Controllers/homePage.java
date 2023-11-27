package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.time.LocalDate;
public class homePage {

    @FXML
    private TextField carMakeModelField;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<?> categoryComboBox;

    @FXML
    private TextArea productTextArea;
    @FXML
    private DatePicker preferredDateField;
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
    @FXML
    void insertInstallationRequest(ActionEvent event) {
        int userId = 1;
       // UserSession.getInstance().getUserId();
        String selectedProduct = productTextArea.getSelectedText();
        if (selectedProduct != null && !selectedProduct.isEmpty()) {
            String[] productInfo = selectedProduct.split("\n");
            int productId = Integer.parseInt(productInfo[0].split(":")[1].trim());

            // Get the installation request details from the additional fields
            String carMakeModel = carMakeModelField.getText();
            LocalDate preferredDate = preferredDateField.getValue();

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql", "root", "Vqo@954719")) {
                // Prepare the SQL query
                String insertSql = "INSERT INTO installationrequests (UserId, ProductId, CarMakeModel, PreferredDate) VALUES (?, ?, ?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                    insertStatement.setInt(1, userId);
                    insertStatement.setInt(2, productId);
                    insertStatement.setString(3, carMakeModel);
                    insertStatement.setDate(4, Date.valueOf(preferredDate));
                    insertStatement.executeUpdate();

                    showAlert("Installation request successfully inserted!");
                }
            } catch (SQLException e) {
                showAlert("Something Wrong!");

            }
        }
}
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Installation Request");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
