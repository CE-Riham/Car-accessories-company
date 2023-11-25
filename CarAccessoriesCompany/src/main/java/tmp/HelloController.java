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
        }
    }


}