package cards;
import classes.DBConnector;
import database.deleting.CategoryDeleter;
import helpers.Alerts;
import helpers.stage_helpers.AdminStageHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class CategoryCard {
    private HBox card;
    private Label categoryName;
    private Button deleteButton;
    private Button editButton;
    private Button doneButton;
    private boolean editing = false;
    private void setCard() {
        card = new HBox(25);
        card.setAlignment(Pos.CENTER);
        card.setPrefHeight(100);
        card.setPrefWidth(960);
        card.setStyle("-fx-background-color: #D6C8C1;");
        card.setPadding(new Insets(0, 50, 0, 50));
    }
    public HBox getCard(){return card;}
    private void addToCard(){
        card.getChildren().addAll(categoryName, doneButton, editButton, deleteButton);
        card.setPadding(new Insets(10.0));
    }

    private void setCategoryName(String categoryName) {
        this.categoryName = new Label(categoryName);
        this.categoryName.setMinWidth(300);
        this.categoryName.setPrefSize(835, 47);
        this.categoryName.setFont(new Font(32));
    }

    private void setDeleteButton() {
        deleteButton = new Button("Delete");
        deleteButton.setMinWidth(100);
        deleteButton.setPrefWidth(100);
        deleteButton.setMaxWidth(100);
        deleteButton.getStyleClass().add("delete-button");
        deleteButton.setFont(new Font(18));
        deleteButton.setOnAction(e->{
            CategoryDeleter categoryDeleter = new CategoryDeleter(DBConnector.getConnector().getCon());
            boolean flag = categoryDeleter.deleteCategory(categoryName.getText());
            if(flag) {
                Alerts.informationAlert("Deleting category", null, categoryDeleter.getStatus());
                AdminStageHelper.showAdminCategories(e);
            }
            else
                Alerts.errorAlert("Deleting category", null, categoryDeleter.getStatus());
        });
    }

    private void setEditButton() {
        editButton = new Button("Edit");
        editButton.setMinWidth(100);
        editButton.setPrefWidth(100);
        editButton.setMaxWidth(100);
        editButton.getStyleClass().add("color1-button");
        editButton.setFont(new Font(18));
    }

    private void setDoneButton() {
        doneButton = new Button("Done");
        doneButton.setMinWidth(100);
        doneButton.setPrefWidth(100);
        doneButton.setMaxWidth(100);
        doneButton.getStyleClass().add("color1-button");
        doneButton.setFont(new Font(18));
        doneButton.setVisible(false);
    }

    public CategoryCard(String category){
        setCard();
        setCategoryName(category);
        setDoneButton();
        setEditButton();
        setDeleteButton();
        addToCard();
    }

}
