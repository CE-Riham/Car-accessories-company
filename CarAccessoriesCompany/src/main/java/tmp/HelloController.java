package tmp;//import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;

public class HelloController {

    static Logger logger = Logger.getLogger(HelloController.class.getName());
    @FXML
    public TextField gmailLogIn;
    @FXML
    private Button login1;
    @FXML
    private Button signUp;

    @FXML
    private PasswordField passwordLogIn;

    @FXML
    private static String z;

    public static String getZ() {
        return z;
    }

    public static void setZ(String z) {
        HelloController.z = z;
    }




    private int getUserIdFromDatabase(String Gmail, String password) {
        int userId = -1; // Default value indicating failure

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/mysql", "root", "Vqo@954719")) {
            // Prepare the SQL query
            String sql = "SELECT ID FROM customer WHERE Gmail = ? AND Password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, Gmail);
                preparedStatement.setString(2, password);

                // Execute the query
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        userId = resultSet.getInt("ID");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }
    @FXML
    void login1Clicked(ActionEvent event) {


        try {
            ResultSet rs = Database.createDatabase("select Gmail,Password from customer");
            ResultSet ra= Database.createDatabase("select email,UserPassword from admin ");
            while (ra.next()){
                String gmailA = ra.getString(1);
                String passwordA = ra.getString(2);
                Parent root;
                if (gmailLogIn.getText().equals(gmailA) && passwordLogIn.getText().equals(passwordA)) {
                    int userId = getUserIdFromDatabase(gmailA, passwordA);

//                    if (userId != -1) {
                        UserSession.getInstance().setUserId(userId);
                        root = FXMLLoader.load(getClass().getResource("screen3.fxml"));
                        Stage stage = (Stage) login1.getScene().getWindow();
                        stage.setScene(new Scene(root));
                        stage.show();
//                        new FadeIn(root).play();
                        return;
//                    } else {
//                        // Handle invalid credentials
//                        return;
//                    }

                }
            }
            while (rs.next()) {
                String gmail = rs.getString(1);
                String password = rs.getString(2);
                Parent root;
                if (gmailLogIn.getText().equals(gmail) && !passwordLogIn.getText().equals(password)) {
                    JOptionPane.showMessageDialog(null, "Incorrect Password");
                    return;}
                else if (gmailLogIn.getText().equals(gmail) && passwordLogIn.getText().equals(password)) {
                    setZ(gmailLogIn.getText());
                    root = FXMLLoader.load(getClass().getResource("Screen3.fxml"));
                    Stage stage = (Stage) login1.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
//                    new FadeIn(root).play();
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Incorrect Gmail");
        } catch (SQLException e) {
            logger.log(null,"tmp.Database connection error: ");
        }
        catch (IOException e){
            logger.log(null," An error occurred while opening a new window:");        }
    }
    @FXML
    void signUp1Clicked(ActionEvent event) {
        try{
            Parent root;
            System.out.println("D0");
            root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            System.out.println("D1");
            Stage stage = (Stage) signUp.getScene().getWindow();
            System.out.println("D2");
            stage.setScene(new Scene(root));
            System.out.println("D3");
            stage.show();
            System.out.println("D4");
//            new FadeIn(root).play();
            System.out.println("D5");
        }catch (Exception e){
            //System.out.println("DDDDDDDDDDDDDDDDDDDDDDD");
//            logger.log(null," An error occurred while opening a new window:");
        }
    }


}