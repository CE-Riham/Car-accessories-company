package Classes;

import authentication.Login;
import authentication.UserSessionManager;
import database.DatabaseConnection;
import database.InsertingData;
import database.RetrievingData;
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
        if(connector.isStatus())
            System.out.println("Connected to the database successfully");
        else {
            System.out.println("Couldn't connect to the database");
            System.exit(0);
        }


        Login login = new Login(connector.getCon());
//        login.loginUser("rihamkatout", "123456");
//        System.out.println(login.getStatus());
//        System.out.println(userSession);
//        dataRetriever.selectUsers("username = 'rihamkatout'");
//        System.out.println(dataRetriever.getStatus());
//        dataRetriever.selectAddresses( "");
//        System.out.println(dataRetriever.getStatus());
//        System.out.println(flag);
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