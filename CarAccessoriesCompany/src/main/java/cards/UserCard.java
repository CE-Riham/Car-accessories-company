package cards;

import classes.UserSession;
import helpers.stage_helpers.AdminStageHelper;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.User;

import java.io.File;

public class UserCard {

    private AnchorPane card;
    private VBox container;
    private ImageView image;
    private HBox nameBox;
    private HBox usernameBox;
    private Button showProfileButton;
    private User user;

    public UserCard(User user) {
        this.user = new User(user);
        setCard("#a1bbb6");
        setContainer();
        setImage(user.getImagePath());
        setHeader(user.getFirstName(), user.getLastName());
        setUsername(user.getUsername());
        setShowProfileButton();
        addToCard();
    }

    public AnchorPane getCard() {
        return card;
    }

    public void setCard(String color) {
        card = new AnchorPane();
        card.setPrefHeight(270.0);
        card.setPrefWidth(225.0);
        card.setStyle("-fx-background-color: " + color + "; -fx-border-color: black; -fx-border-width: 2;");
    }

    public void setContainer() {
        container = new VBox();
        container.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        container.setPrefHeight(270.0);
        container.setPrefWidth(225.0);
        container.setSpacing(10.0);
    }

    private void setImage(String imagePath) {
        image = new ImageView();
        image.setFitHeight(150.0);
        image.setFitWidth(204.0);
        image.setPickOnBounds(true);
        image.setPreserveRatio(false);
        File file = new File(imagePath);
        String localUrl = file.toURI().toString();
        Image innerImage = new Image(localUrl);
        image.setImage(innerImage);
    }

    private void setHeader(String firstName, String lastName) {
        nameBox = new HBox();
        nameBox.setPrefHeight(25.0);
        nameBox.setPrefWidth(205.0);
        nameBox.setStyle("-fx-border-width: 2px 0 0 0; -fx-border-color: #414141;");

        Label nameLabel = new Label(firstName + " " + lastName);
        nameLabel.setMaxWidth(150.0);
        nameLabel.setPrefHeight(25.0);
        nameLabel.setPrefWidth(140.0);
        nameLabel.setFont(Font.font("SansSerif Bold", 14.0));

        nameBox.getChildren().addAll(nameLabel);
    }

    private void setUsername(String username) {
        usernameBox = new HBox();
        usernameBox.setPrefHeight(39.0);
        usernameBox.setPrefWidth(205.0);

        Label usernameLabel = new Label(username);
        usernameLabel.setMaxWidth(205.0);
        usernameLabel.setPrefHeight(48.0);
        usernameLabel.setPrefWidth(205.0);
        usernameLabel.setTextFill(javafx.scene.paint.Color.web("#757474"));
        usernameLabel.setFont(Font.font(12.0));

        usernameBox.getChildren().add(usernameLabel);
    }

    private void setShowProfileButton() {
        showProfileButton = new Button("show profile");
        showProfileButton.setMnemonicParsing(false);
        showProfileButton.getStyleClass().add("color4-2-button");
        showProfileButton.setFont(Font.font(12.0));
        showProfileButton.setOnAction(e -> {
            UserSession.setUserToDisplay(user);
            AdminStageHelper.showAdminUserProfile(e);
        });
    }

    private void addToCard() {
        container.getChildren().addAll(image, nameBox, usernameBox, showProfileButton);
        container.setPadding(new Insets(10.0));
        AnchorPane.setTopAnchor(container, 0.0);
        AnchorPane.setBottomAnchor(container, 0.0);
        AnchorPane.setLeftAnchor(container, 0.0);
        AnchorPane.setRightAnchor(container, 0.0);
        card.getChildren().add(container);
    }
}