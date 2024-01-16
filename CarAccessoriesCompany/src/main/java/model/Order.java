package model;

import java.time.LocalDate;

public class Order {
    private int orderID;
    private int productID;
    private String customerUsername;
    private int orderStatus;
    private LocalDate orderDate;
    private LocalDate sendingDate;
    private LocalDate receivingDate;

    public Order() {
        orderDate = LocalDate.of(1, 1, 1);
        sendingDate = LocalDate.of(1, 1, 1);
        receivingDate = LocalDate.of(1, 1, 1);
    }

    public Order(int orderID, int productID, String customerUsername, int orderStatus, LocalDate orderDate, LocalDate sendingDate, LocalDate receivingDate) {
        setOrderID(orderID);
        setProductID(productID);
        setCustomerUsername(customerUsername);
        setOrderStatus(orderStatus);
        setOrderDate(orderDate);
        setSendingDate(sendingDate);
        setReceivingDate(receivingDate);
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(LocalDate sendingDate) {
        this.sendingDate = sendingDate;
    }

    public LocalDate getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(LocalDate receivingDate) {
        this.receivingDate = receivingDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", productID=" + productID +
                ", customerUsername='" + customerUsername + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderDate=" + orderDate +
                ", sendingDate=" + sendingDate +
                ", receivingDate=" + receivingDate +
                '}';
    }
}
