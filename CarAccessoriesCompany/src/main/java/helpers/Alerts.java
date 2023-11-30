package helpers;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerts {

    public static Optional<ButtonType> confirmationMessage(String header, String content){
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(header);
        confirmationAlert.setContentText(content);
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        return result;
    }
}

