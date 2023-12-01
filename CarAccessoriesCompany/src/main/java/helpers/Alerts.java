package helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerts {
    private Alerts() {
        throw new IllegalStateException("Utility class");
    }

    public static Optional<ButtonType> confirmationMessage(String header, String content){
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(header);
        confirmationAlert.setContentText(content);
        return confirmationAlert.showAndWait();
    }
}

