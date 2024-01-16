package controllers.customer_controllers;

import cards.OrderCard;
import classes.DBConnector;
import classes.UserSession;
import database.retrieving.RetrievingOrders;
import helpers.comparators.OrderComparator;
import helpers.filters.OrderFilter;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.Order;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerOrdersController extends CustomerNavBarActions implements Initializable {
    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section1: Class attributes -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private List<Order> allOrders;
    private List<Order> toViewOrders;
    private List<Order> pendingOrders;
    private List<Order> sentOrders;
    private List<Order> receivedOrders;
    private int pendingOrdersPageNumber = 0;
    private int sentOrdersPageNumber = 0;
    private int receivedOrdersPageNumber = 0;

    // --------- Buttons
    @FXML
    private Button myOrders;
    @FXML
    private Button nextPendingOrdersButton;
    @FXML
    private Button nextReceivedOrdersButton;
    @FXML
    private Button nextSentOrdersButton;
    @FXML
    private Button previousPendingOrdersButton;
    @FXML
    private Button previousReceivedOrdersButton;
    @FXML
    private Button previousSentOrdersButton;

    // --------- Containers
    @FXML
    private VBox pendingOrdersContainer;
    @FXML
    private VBox receivedOrdersContainer;
    @FXML
    private VBox sentOrdersContainer;

    // --------- Searching & Filtering
    @FXML
    private ComboBox<String> searchByCombo;
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox<String> sortByCombo;
    @FXML
    private ComboBox<String> sortTypeCombo;


    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section2: Page button actions ------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    @FXML
    void filterOrders(Event event) {
        handleFilterOrders();
    }

    @FXML
    void onPreviousPageClick(ActionEvent event) {
        handlePreviousPage(event);
    }

    @FXML
    void onNextPageClick(ActionEvent event) {
        handleNextPage(event);
    }


    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section4: Initialising actions ------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pendingOrders = new ArrayList<>();
        sentOrders = new ArrayList<>();
        receivedOrders = new ArrayList<>();
        activateMenuButton(myOrders);
        fillAllComboBoxes();
        getAllOrdersFromDB();
        fillFilteredData();
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // -------------------------------------------- section5: Fill combo-Boxes -------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void fillAllComboBoxes() {
        fillSearchByCombo();
        fillSortTypeCombo();
        fillSortByCombo();
    }

    private void fillSearchByCombo() {
        List<String> allFields = List.of("orderID", "productID", "orderDate", "sendingDate", "receivingDate");
        searchByCombo.setItems(FXCollections.observableArrayList(allFields));
        searchByCombo.setValue(allFields.get(0));
    }

    private void fillSortTypeCombo() {
        List<String> allFields = List.of("ASC", "DSC");
        sortTypeCombo.setItems(FXCollections.observableArrayList(allFields));
        sortTypeCombo.setValue(allFields.get(0));
    }

    private void fillSortByCombo() {
        List<String> allFields = List.of("orderID", "productID", "orderDate", "sendingDate", "receivingDate");
        sortByCombo.setItems(FXCollections.observableArrayList(allFields));
        sortByCombo.setValue(allFields.get(0));
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------ section6: Display toViewOrders ------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void fillVBox(VBox container, List<Order> orders, int index) {
        container.getChildren().clear();
        for (int i = index; i < (index + 6) && i < orders.size() && i >= 0; i++) {
            container.getChildren().add(new OrderCard(orders.get(i)).getCard());
        }
    }

    private void fillOrdersContainer(int... status) {
        for (int i : status) {
            if (i == 0) {
                fillVBox(pendingOrdersContainer, pendingOrders, pendingOrdersPageNumber * 6);
                disableOrdersButtons(0);
            } else if (i == 1) {
                fillVBox(sentOrdersContainer, sentOrders, sentOrdersPageNumber * 6);
                disableOrdersButtons(1);
            } else {
                fillVBox(receivedOrdersContainer, receivedOrders, receivedOrdersPageNumber * 6);
                disableOrdersButtons(2);
            }
        }
    }

    private List<Order> getOrdersByStatus(int status) {
        return OrderFilter.filterOrdersBy("orderStatus", String.valueOf(status), toViewOrders);
    }

    private void fillFilteredData() {
        pendingOrders = getOrdersByStatus(0);
        sentOrders = getOrdersByStatus(1);
        receivedOrders = getOrdersByStatus(2);
        fillOrdersContainer(0, 1, 2);
    }


    // -------------------------------------------------------------------------------------------------------------------- //
    // --------------------------------------------- section7: helper methods --------------------------------------------- //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void getAllOrdersFromDB() {
        allOrders = OrderFilter.filterOrdersBy("customerUsername", UserSession.getCurrentUser().getUsername(), new RetrievingOrders(DBConnector.getConnector().getCon()).selectAllOrders());
        toViewOrders = new ArrayList<>(allOrders);
    }

    private void sortOrders() {
        boolean sortingType = sortTypeCombo.getValue().equals("ASC");
        String sortBy = sortByCombo.getValue();
        toViewOrders = OrderComparator.sortOrders(sortBy, sortingType, new ArrayList<>(toViewOrders));
    }

    private void searchOrders() {
        toViewOrders = OrderFilter.filterOrdersBy(searchByCombo.getValue(), searchTextField.getText(), allOrders);
    }

    private void disableOrdersButtons(int status) {
        if (status == 0) {
            disableButton(previousPendingOrdersButton, (pendingOrdersPageNumber == 0));
            disableButton(nextPendingOrdersButton, ((pendingOrdersPageNumber * 6 + 6) >= pendingOrders.size()));
        } else if (status == 1) {
            disableButton(previousSentOrdersButton, (sentOrdersPageNumber == 0));
            disableButton(nextSentOrdersButton, ((sentOrdersPageNumber * 6 + 6) >= sentOrders.size()));
        } else {
            disableButton(previousReceivedOrdersButton, (receivedOrdersPageNumber == 0));
            disableButton(nextReceivedOrdersButton, ((receivedOrdersPageNumber * 6 + 6) >= receivedOrders.size()));
        }
    }

    // -------------------------------------------------------------------------------------------------------------------- //
    // ------------------------------------------------ section7: handlers ------------------------------------------------ //
    // -------------------------------------------------------------------------------------------------------------------- //
    private void handleFilterOrders() {
        searchOrders();
        sortOrders();
        fillFilteredData();
    }

    private void handlePendingOrdersButtons(boolean b) {
        //true for nextPage
        pendingOrdersPageNumber += (b ? 1 : -1);
        fillOrdersContainer(0);
    }

    private void handleSentOrdersButtons(boolean b) {
        sentOrdersPageNumber += (b ? 1 : -1);
        fillOrdersContainer(1);
    }

    private void handleReceivedOrdersButtons(boolean b) {
        receivedOrdersPageNumber += (b ? 1 : -1);
        fillOrdersContainer(2);
    }

    private void handlePreviousPage(ActionEvent event) {
        if (event.getSource() == previousPendingOrdersButton)
            handlePendingOrdersButtons(false);
        else if (event.getSource() == previousSentOrdersButton)
            handleSentOrdersButtons(false);
        else
            handleReceivedOrdersButtons(false);
    }

    private void handleNextPage(ActionEvent event) {
        if (event.getSource() == nextPendingOrdersButton)
            handlePendingOrdersButtons(true);
        else if (event.getSource() == nextSentOrdersButton)
            handleSentOrdersButtons(true);
        else
            handleReceivedOrdersButtons(true);
    }
}