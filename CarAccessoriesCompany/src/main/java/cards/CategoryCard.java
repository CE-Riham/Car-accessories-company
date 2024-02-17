package cards;

import classes.DBConnector;
import database.deleting.CategoryDeleter;
import database.updating.CategoryUpdater;
import helpers.Alerts;
import helpers.stage_helpers.AdminStageHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.Optional;

public class CategoryCard {
    private HBox card;
    private Label categoryName;
    private Button deleteButton;
    private Button editButton;

    public CategoryCard(String category) {
        setCard();
        setCategoryName(category);
        setDeleteButton();
        setEditButton();
        addToCard();
    }

    public HBox getCard() {
        return card;
    }

    private void setCard() {
        card = new HBox(25);
        card.setAlignment(Pos.CENTER);
        card.setPrefHeight(100);
        card.setPrefWidth(960);
        card.setStyle("-fx-background-color: #a1bbb6;");
        card.setPadding(new Insets(0, 50, 0, 50));
    }

    private void addToCard() {
        card.getChildren().addAll(categoryName, editButton, deleteButton);
        card.setPadding(new Insets(10.0));
    }

    private void setCategoryName(String categoryName) {
        this.categoryName = new Label(categoryName);
        this.categoryName.setMinWidth(300);
        this.categoryName.setPrefSize(835, 47);
        this.categoryName.setFont(new Font(32));
        this.categoryName.setStyle("-fx-font-weight: bold;");
    }

    private void setDeleteButton() {
        deleteButton = new Button("Delete");
        deleteButton.setMinWidth(100);
        deleteButton.setPrefWidth(100);
        deleteButton.setMaxWidth(100);
        deleteButton.getStyleClass().add("delete-button");
        deleteButton.setFont(new Font(18));
        deleteButton.setOnAction(e -> {
            String alertTitle = "Deleting category";
            Optional<ButtonType> result = Alerts.confirmationAlert(alertTitle, "Are you sure that you want to delete this category?");
            if (result.isEmpty() || result.get() != ButtonType.OK) return;
            CategoryDeleter categoryDeleter = new CategoryDeleter(DBConnector.getConnector().getCon());
            boolean flag = categoryDeleter.deleteCategory(categoryName.getText());
            if (flag) {
                Alerts.informationAlert(alertTitle, null, categoryDeleter.getStatus());
                AdminStageHelper.showAdminCategories(e);
            } else
                Alerts.errorAlert(alertTitle, null, categoryDeleter.getStatus());
        });
    }

    private void setEditButton() {
        editButton = new Button("Edit");
        editButton.setMinWidth(100);
        editButton.setPrefWidth(100);
        editButton.setMaxWidth(100);
        editButton.getStyleClass().add("color4-button");
        editButton.setFont(new Font(18));

        editButton.setOnAction(e -> {
            String title = "Edit category";
            Optional<String> result = Alerts.withInputAlert(title, null, "Enter new category name");
            result.ifPresent(newCategory -> {
                CategoryUpdater categoryUpdater = new CategoryUpdater(DBConnector.getConnector().getCon());
                boolean flag = categoryUpdater.updateCategory(newCategory, categoryName.getText());
                if (!flag)
                    Alerts.errorAlert(title, null, categoryUpdater.getStatus());
                else {
                    Alerts.informationAlert(title, null, categoryUpdater.getStatus());
                    AdminStageHelper.showAdminCategories(e);
                }
            });
        });
    }
}
