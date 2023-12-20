package controllers.admin_controllers.categories;

import cards.CategoryCard;
import cards.UserCard;
import classes.DBConnector;
import classes.UserSession;
import database.inserting.InsertingData;
import database.retrieving.RetrievingCategories;
import database.retrieving.RetrievingUser;
import helpers.Alerts;
import helpers.stage_helpers.AdminStageHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminCategoriesController implements Initializable {
    private List<String> allCategories;
    private int pageNumber = 0;
    @FXML
    private Button prevButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button categoriesButton;
    @FXML
    private VBox categoriesTable;

    private void activateMenuButton(){
        categoriesButton.setStyle("-fx-border-color: #C9B3AD;");
    }

    @FXML
    void onAdminProfileClick(ActionEvent event) {
        AdminStageHelper.showAdminProfile(event);
    }

    @FXML
    void onAdminsClick(ActionEvent event) {
        AdminStageHelper.showAdminAdmins(event);
    }

    @FXML
    void onCustomersClick(ActionEvent event) {
        AdminStageHelper.showAdminCustomers(event);
    }

    @FXML
    void onCategoriesClick(ActionEvent event) {
        AdminStageHelper.showAdminCategories(event);
    }

    @FXML
    void onInstallersClick(ActionEvent event) {

    }

    @FXML
    void onLogoutClick(ActionEvent event) {
        UserSession.logoutUser(event);
    }

    @FXML
    void onNotificationsCLick(ActionEvent event) {

    }

    @FXML
    void onOrdersClick(ActionEvent event) {

    }

    @FXML
    void onProductsClick(ActionEvent event) {
        AdminStageHelper.showAdminProducts(event);
    }

    @FXML
    void onPrevButtonClick(ActionEvent event) {
        pageNumber--;
        fillCategories();
    }

    @FXML
    void onNextButtonClick(ActionEvent event) {
        pageNumber++;
        fillCategories();
    }

    @FXML
    void onAddCategoryClick(ActionEvent event){
        String title = "Add category";
        Optional<String> result = Alerts.withInputAlert(title, null, "enter category name:");
        result.ifPresent(newCategory -> {
            if(new RetrievingCategories(DBConnector.getConnector().getCon()).selectACategory(newCategory).size() != 0)
                Alerts.errorAlert(title, null, "Category is already existed.");
            else{
                InsertingData insertingData = new InsertingData(DBConnector.getConnector().getCon());
                boolean flag = insertingData.insertCategory(newCategory);
                if(flag) {
                    Alerts.informationAlert(title, null, insertingData.getStatus());
                    AdminStageHelper.showAdminCategories(event);
                }
                else
                    Alerts.errorAlert(title, null, insertingData.getStatus());
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activateMenuButton();
        fillCategories();
    }

    private void getAllCategoriesFromDB(){
        RetrievingCategories categoriesRetriever = new RetrievingCategories(DBConnector.getConnector().getCon());
        allCategories = categoriesRetriever.selectAllCategories();
    }
    private void fillTable(int index){
        categoriesTable.getChildren().clear();
        for(int i = index; i<(index + 5) && i< allCategories.size(); i++)
            categoriesTable.getChildren().add(new CategoryCard(allCategories.get(i)).getCard());

        disableButton(prevButton, (index == 0));
        disableButton(nextButton, ((index + 5) > allCategories.size()));
    }
    private void fillCategories(){
        getAllCategoriesFromDB();
        fillTable(pageNumber*5);
    }

    private void disableButton(Button button, boolean flag){
        button.setDisable(flag);
    }

}