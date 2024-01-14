package helpers.stage_helpers;

import classes.Starter;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthenticationStageHelper {

    private AuthenticationStageHelper() {
        throw new IllegalStateException("Utility class");
    }

    private static void showPage(ActionEvent event, String path, int width, int height) throws IOException {
        if (event.getSource() instanceof Node source) {
            Parent root = FXMLLoader.load(AuthenticationStageHelper.class.getResource(path));
            Scene scene = source.getScene();
            Stage stage = (Stage) scene.getWindow();
            scene.setRoot(root);
            stage.setHeight(height);
            stage.setWidth(width);
            stage.centerOnScreen();
        }
    }

    public static void showLogin(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/authinticationPages/loginPage.fxml", 608, 837);
        } catch (Exception e) {
            Starter.logger.warning("Can't open login page");
        }
    }

    public static void showSignup(ActionEvent event) {
        try {
            showPage(event, "/FXMLFiles/authinticationPages/signupPage.fxml", 608, 837);
        } catch (Exception e) {
            Starter.logger.warning("Can't open sign-up page");
        }
    }
}
