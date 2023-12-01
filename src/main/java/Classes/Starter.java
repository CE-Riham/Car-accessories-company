package Classes;

import authentication.UserSessionManager;
import database.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter extends Application {
    public static DatabaseConnection connector;
    public static UserSessionManager sessionManager;
    public static UserSession userSession;
    @Override
    public void start(Stage stage) throws IOException {
        sessionManager = new UserSessionManager();
        connector = new DatabaseConnection();
        System.out.println(connector.getStatus());


        FXMLLoader fxmlLoader = new FXMLLoader(Starter.class.getResource("/FXMLFiles/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 608, 837);
        stage.setTitle("Car Zone Company");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}