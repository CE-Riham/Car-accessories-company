package helpers;

import classes.Starter;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageHelper {

    private StageHelper() {
        throw new IllegalStateException("Utility class");
    }
    private static void showPage(ActionEvent event, String path, int width, int height) throws IOException {
        if (event.getSource() instanceof Node source) {
            Parent root = FXMLLoader.load(StageHelper.class.getResource(path));
            Scene scene = source.getScene();
            Stage stage = (Stage) scene.getWindow();
            scene.setRoot(root);
            stage.setHeight(height);
            stage.setWidth(width);
            stage.centerOnScreen();
        }
    }
    public static void showAdmin(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/adminPage.fxml", 1600, 900);
        }catch (Exception e){
            Starter.logger.warning("Can't open admin page");
        }
    }

    public static void showLogin(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/loginPage.fxml", 608, 837);
        }catch (Exception e){
            Starter.logger.warning("Can't open login page");
        }
    }

    public static void showSignup(ActionEvent event){
        try{
            showPage(event, "/FXMLFiles/signupPage.fxml", 608, 837);
        }catch (Exception e){
            Starter.logger.warning("Can't open sign-up page");
        }
    }
}
