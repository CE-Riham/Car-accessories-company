package Classes;

import database.DBConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DBConnector connector = new DBConnector();
        if(connector.isStatus())
            System.out.println("Connected to the database successfully");
        else
            System.out.println("Couldn't connect to the database");

        FXMLLoader fxmlLoader = new FXMLLoader(Starter.class.getResource("/FXMLFiles/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}