package Controllers;

import Classes.Mail;
import Classes.Starter;
import Classes.UserSession;
import animatefx.animation.FadeIn;
import authentication.Register;
import database.InsertingData;
import database.RetrievingData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.installReq;


import javax.mail.MessagingException;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
public class homePage {
    private installReq request;
    private InsertingData reqInserter;
    private RetrievingData reqRetriever;
    Register reg;
   public homePage()
   {
       reg=new Register(Starter.connector.getCon());
       reqInserter = reg.userInserter;
      // request = new installReq(Starter.connector.getCon());
   }
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public homePage(Connection connection){
        reqInserter = new InsertingData(connection);
        reqRetriever = new RetrievingData(connection);
    }
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
    private Button orderBtt;


    @FXML
    void OrdrerReq(ActionEvent event) throws IOException, SQLException, MessagingException {
        String selectedProduct = productTextArea.getSelectedText();
        String[] productInfo = selectedProduct.split("\n");

        int userId = Integer.parseInt(UserSession.getSessionId());
        int productId = Integer.parseInt(productInfo[0].split(":")[1].trim());
        insertOrderIntoDatabase(userId, productId);

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql", "root", "Vqo@954719")) {
            // Create a prepared statement with a parameterized query
            String query = "SELECT email FROM users_new WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, userId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String userEmail = resultSet.getString("email");
                        Mail m = new Mail();
                        System.out.println(userEmail+"     ; ;;;;;;;;;;;;;;");
                        m.rasheedEmail(userEmail); // Use userEmail instead of resultSet.getString(1)
                    } else {
                        System.out.println("User not found.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database-related errors
        }
    }
    private void insertOrderIntoDatabase(int userId, int productId) {
        String insertQuery = "INSERT INTO Orders (user_id, product_id, order_date) VALUES (?, ?, ?)";

        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql", "root", "Vqo@954719");
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)
        ) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            Timestamp orderDate = new Timestamp(System.currentTimeMillis());
            preparedStatement.setTimestamp(3, orderDate);
            System.out.println("Executing SQL Query: " + preparedStatement.toString());

          // System.out.println(userId+"         "+productId+"    "+orderDate);
            preparedStatement.executeUpdate();
          //  System.out.println("Executing SQL Query: " + preparedStatement.toString());

            System.out.println("Order inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showProducts(ActionEvent event) {
        String searchText = searchField.getText();
        String selectedCategory = (String) categoryComboBox.getValue();

        StringBuilder resultText = new StringBuilder();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql", "root", "Vqo@954719")) {
            String sql;
            if (searchText == null || searchText.trim().isEmpty()) {
                sql = "SELECT * FROM Product WHERE Category = ?";
            } else {
                sql = "SELECT * FROM Product WHERE Category = ? AND (Name LIKE ? OR Description LIKE ?)";
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, selectedCategory);
                if (searchText != null && !searchText.trim().isEmpty()) {
                    preparedStatement.setString(2, "%" + searchText + "%");  // Search by name
                    preparedStatement.setString(3, "%" + searchText + "%");  // Search by description
                }
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int productId = resultSet.getInt("ProductId");
                        String name = resultSet.getString("Name");
                        String description = resultSet.getString("Description");
                        double price = resultSet.getDouble("Price");
                        boolean availability = resultSet.getBoolean("Availability");

                        resultText.append(String.format("Product ID: %d\nName: %s\nDescription: %s\nPrice: %.2f\nAvailability: %b\n\n",
                                productId, name, description, price, availability));
                    }
                    productTextArea.setText(resultText.toString());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @FXML
    void insertInstallationRequest(ActionEvent event) {
        String selectedProduct = productTextArea.getSelectedText();
        String[] productInfo = selectedProduct.split("\n");

        if (selectedProduct == null || selectedProduct.isEmpty()) {
            showAlert("Please select a product.");
            return;
        }


        if (productInfo.length < 2) {
            showAlert("Invalid product information.");
            return;
        }

        try {
            int userId = Integer.parseInt(UserSession.getSessionId());
            int productId = Integer.parseInt(productInfo[0].split(":")[1].trim());

            String carMakeModel = carMakeModelField.getText();
            LocalDate preferredDate = preferredDateField.getValue();
            request = new installReq(userId, productId, Integer.parseInt(carMakeModel), preferredDate);
            request.toString();
            insertInstallationRequestToDatabase(request);
            showAlert("Installation request successfully inserted!");
        } catch (NumberFormatException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            showAlert("Error parsing input. Please check your input.");
            e.printStackTrace(); // Log the exception for debugging purposes
        } catch (Exception e) {
            showAlert("An error occurred while inserting the installation request.");
            e.printStackTrace(); // Log the exception for debugging purposes
        }
    }


    private void insertInstallationRequestToDatabase(installReq req) {

            if(reqInserter.insertInstallReq(req)) {
                setStatus("Insert Request was Inserting successfully");
            }
            else {
                setStatus("Couldn't Insert Request");

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
