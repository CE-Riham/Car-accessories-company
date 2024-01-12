package cards;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import model.products.ProductReview;

public class ProductReviewCard {

    private HBox card;

    public ProductReviewCard(ProductReview review) {
        setCard();
        addIdAndCommentToContainer(review.getReviewID(), review.getCustomerComment());
    }

    public HBox getCard() {
        return card;
    }

    public void setCard() {
        card = new HBox();
        card.setPrefHeight(60);
        card.setPrefWidth(235);
        card.setSpacing(10);
        card.setStyle("-fx-background-color: #e9e1de; -fx-border-color: black;");
    }

    public void addIdAndCommentToContainer(int id, String comment){
        Label idLabel = new Label(id + ": ");
        idLabel.setPrefHeight(58);
        idLabel.setPrefWidth(93);
        idLabel.setFont(Font.font("System Bold", 18));

        Label productReviewLabel = new Label(comment);
        productReviewLabel.setPrefHeight(60);
        productReviewLabel.setPrefWidth(617);
        productReviewLabel.setFont(Font.font(18));

        card.getChildren().addAll(idLabel, productReviewLabel);
        card.setPadding(new Insets(0, 10, 0, 10));
    }
}
