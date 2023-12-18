package cards;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.Product;

import java.io.File;

public class ProductCard{
    private AnchorPane card;
    private VBox container;
    private ImageView image;
    private HBox header;
    private HBox descriptionBox;
    private Button showProductButton;
    public ProductCard(Product product) {
        setCard("#EFEEEC");
        setContainer();
        setImage(product.getImagePath());
        setHeader(product.getProductName(), String.valueOf(product.getProductPrice()));
        setDescriptionBox(product.getShortDescription());
        setShowProductButton();
        addToCard();
    }

    public AnchorPane getCard() {
        return card;
    }

    public void setCard(String color) {
        card = new AnchorPane();
        card.setPrefHeight(270.0);
        card.setPrefWidth(225.0);
        card.setStyle("-fx-background-color: " + color + ";");
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

    private void setHeader(String name, String price) {
        header = new HBox();
        header.setPrefHeight(25.0);
        header.setPrefWidth(205.0);
        header.setStyle("-fx-border-width: 2px 0 0 0; -fx-border-color: #414141;");

        Label productNameLabel = new Label(name);
        productNameLabel.setMaxWidth(150.0);
        productNameLabel.setPrefHeight(25.0);
        productNameLabel.setPrefWidth(140.0);
        productNameLabel.setFont(Font.font("SansSerif Bold", 14.0));

        Label priceLabel = new Label(price + "$");
        priceLabel.setMaxWidth(75.0);
        priceLabel.setPrefHeight(25.0);
        priceLabel.setPrefWidth(75.0);
        priceLabel.setStyle("-fx-alignment: center-right;");
        priceLabel.setTextAlignment(javafx.scene.text.TextAlignment.RIGHT);
        priceLabel.setFont(Font.font("System Bold", 14.0));

        header.getChildren().addAll(productNameLabel, priceLabel);
    }

    private void setDescriptionBox(String shortDescription) {
        descriptionBox = new HBox();
        descriptionBox.setPrefHeight(39.0);
        descriptionBox.setPrefWidth(205.0);

        Label descriptionLabel = new Label(shortDescription);
        descriptionLabel.setMaxWidth(205.0);
        descriptionLabel.setPrefHeight(48.0);
        descriptionLabel.setPrefWidth(205.0);
        descriptionLabel.setTextFill(javafx.scene.paint.Color.web("#757474"));
        descriptionLabel.setFont(Font.font(12.0));

        descriptionBox.getChildren().add(descriptionLabel);
    }

    private void setShowProductButton() {
        showProductButton = new Button("show product");
        showProductButton.setMnemonicParsing(false);
        showProductButton.getStyleClass().add("color4-button");
        showProductButton.setFont(Font.font(12.0));
    }

    private void addToCard(){
        container.getChildren().addAll(image, header, descriptionBox, showProductButton);
        container.setPadding(new Insets(10.0));
        AnchorPane.setTopAnchor(container, 0.0);
        AnchorPane.setBottomAnchor(container, 0.0);
        AnchorPane.setLeftAnchor(container, 0.0);
        AnchorPane.setRightAnchor(container, 0.0);
        card.getChildren().add(container);
    }
}
