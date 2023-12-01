package helpers;

import classes.Starter;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;

public class Uploader {
    private byte[]data;
    public boolean saveToFile(String path){
        try {
            Optional<ButtonType> result = Alerts.confirmationAlert("Changing photo", "Are you sure you want to change it?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                File file = new File(path);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(new byte[0]);
                fileOutputStream.write(data);
                fileOutputStream.close();
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
    public boolean uploadImage(){
        try{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image File");
            File selectedFile = fileChooser.showOpenDialog(new Stage());
            if (selectedFile != null) {
                String imagePath = selectedFile.getAbsolutePath();
                File sourceFile = new File(imagePath);
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                data = new byte[(int) sourceFile.length()];
                fileInputStream.read(data);
                fileInputStream.close();
                return true;
            } else {
                Starter.logger.warning("No file selected.");
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
