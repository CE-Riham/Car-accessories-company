package helpers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageHelper {

    private static void showPage(ActionEvent event, String path, int width, int height) throws Exception{
        if (event.getSource() instanceof Node) {
            Parent root = FXMLLoader.load(StageHelper.class.getResource(path));
            Node source = (Node) event.getSource();
            Scene scene = source.getScene();
            Stage stage = (Stage) scene.getWindow();
            scene.setRoot(root);
            stage.setHeight(height);
            stage.setWidth(width);
            stage.centerOnScreen();
        }
    }
    public static void showAdmin(ActionEvent event){
        try{
            String path = "/FXMLFiles/admin.fxml";
            showPage(event, path, 1600, 900);
        }catch (Exception e){
            System.out.println("Can't open admin page");
        }
    }
    public static void showHomePage(ActionEvent event){
        try{
            String path = "/FXMLFiles/homePage.fxml";
            showPage(event, path, 700, 800);
        }catch (Exception e){
            System.out.println("Can't open admin page");
        }
    }
    public static void showLogin(ActionEvent event){
        try{
            String path = "/FXMLFiles/login.fxml";
            showPage(event, path, 608, 837);
        }catch (Exception e){
            System.out.println("Can't open login page");
        }
    }

    public static void showSignup(ActionEvent event){
        try{
            String path = "/FXMLFiles/signup.fxml";
            showPage(event, path, 608, 837);
        }catch (Exception e){
            System.out.println("Can't open sign-up page");
        }
    }

    public static void showUser(ActionEvent event){
        try{
            String path = "/FXMLFiles/user.fxml";
            showPage(event, path, 1600, 900);
        }catch (Exception e){
            System.out.println("Can't open User page");
        }
    }
}
