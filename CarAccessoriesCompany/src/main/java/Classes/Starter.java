package Classes;

import database.InsertingData;
import database.DatabaseConnection;
import database.RetrievingData;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Starter extends Application {
    public static DatabaseConnection connector;
    @Override
    public void start(Stage stage) throws IOException {
        connector = new DatabaseConnection();
        InsertingData dataAdder = new InsertingData(connector.getCon());
        if(connector.isStatus())
            System.out.println("Connected to the database successfully");
        else {
            System.out.println("Couldn't connect to the database");
            System.exit(0);
        }
        RetrievingData dataRetriever = new RetrievingData(connector.getCon());

        dataRetriever.selectUsers("username = 'rihamkatout'");
        System.out.println(dataRetriever.getStatus());
        dataRetriever.selectAddresses( "");
        System.out.println(dataRetriever.getStatus());
//        System.out.println(flag);
//        FXMLLoader fxmlLoader = new FXMLLoader(Starter.class.getResource("/FXMLFiles/login.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
//        stage.setTitle("Login");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}