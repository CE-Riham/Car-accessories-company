package cards;

import classes.UserSession;
import helpers.stage_helpers.AdminStageHelper;
import helpers.stage_helpers.CustomerStageHelper;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import model.Order;

public class OrderCard {
    private HBox card;
    private Label orderID;
    private Label dateLabel;
    private Button showOrderButton;
    private Order order;

    public OrderCard(Order order) {
        this.order = order;
        setCard("#a1bbb6");
        setObjects();
        createShowOrderButton();
        addToCard();
    }

    public HBox getCard() {
        return card;
    }

    private void setObjects() {
        orderID = createLabel(String.valueOf(order.getOrderID()), 80.0);
        if (order.getOrderStatus() == 0)
            dateLabel = createLabel(String.valueOf(order.getOrderDate()), 75.0);
        else if (order.getOrderStatus() == 1)
            dateLabel = createLabel(String.valueOf(order.getSendingDate()), 75.0);
        else
            dateLabel = createLabel(String.valueOf(order.getReceivingDate()), 75.0);
    }

    private void createShowOrderButton() {
        showOrderButton = new Button("show order");
        showOrderButton.getStyleClass().add("color4-2-button");
        showOrderButton.setOnAction(e -> {
            UserSession.setOrderToDisplay(order);
            if (UserSession.getCurrentUser().getUserType().equalsIgnoreCase("admin"))
                AdminStageHelper.showAdminDisplayOrderPage(e);
            else if (UserSession.getCurrentUser().getUserType().equalsIgnoreCase("customer"))
                CustomerStageHelper.showCustomerDisplayOrderPage(e);
        });
    }

    private Label createLabel(String text, double width) {
        Label label = new Label(text);
        label.setPrefWidth(width);
        label.setMinWidth(width);
        label.setMaxWidth(width);
        label.setPrefHeight(19.0);
        label.setFont(new Font("System Bold", 13.0));
        return label;
    }

    private void addToCard() {
        card.getChildren().addAll(orderID, dateLabel, showOrderButton);
    }

    private void setCard(String color) {
        card = new HBox(20);
        card.setAlignment(Pos.CENTER);
        card.setPrefHeight(47.0);
        card.setPrefWidth(350.0);
        card.setStyle("-fx-background-color: " + color + ";");
    }
}
