package helpers.common;

import helpers.stage_helpers.AdminStageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class StageHelper {
    protected static void showPage(ActionEvent event, String path, int width, int height) throws IOException {
        if (event.getSource() instanceof Node source) {
            Parent root = FXMLLoader.load(AdminStageHelper.class.getResource(path));
            Scene scene = source.getScene();
            Stage stage = (Stage) scene.getWindow();
            scene.setRoot(root);
            stage.setHeight(height);
            stage.setWidth(width);
            stage.centerOnScreen();
        }
    }
}
