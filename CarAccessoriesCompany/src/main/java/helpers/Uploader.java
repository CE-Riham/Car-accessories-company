package helpers;

import classes.Starter;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;

public class Uploader {
    private byte[] data;
    private String fileName;

    public String getFileName() {
        return (fileName == null) ? "" : fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean saveToFile(String path, boolean showAlert) {
        Optional<ButtonType> result = null;
        if (showAlert) result = Alerts.confirmationAlert("Changing photo", "Are you sure you want to change it?");
        if (!showAlert || (result.isPresent() && result.get() == ButtonType.OK)) {
            File file = new File(path);
            try (FileOutputStream fileOutputStream = new FileOutputStream(file);) {
                fileOutputStream.write(new byte[0]);
                fileOutputStream.write(data);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean uploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            String imagePath = selectedFile.getAbsolutePath();
            setFileName(selectedFile.getName());
            File sourceFile = new File(imagePath);
            try (FileInputStream fileInputStream = new FileInputStream(sourceFile)) {
                data = new byte[(int) sourceFile.length()];
                fileInputStream.read(data);
                fileInputStream.read(data);
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            Starter.logger.warning("No file selected.");
            setFileName("");
            return false;
        }
    }
}
