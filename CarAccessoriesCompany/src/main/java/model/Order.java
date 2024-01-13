package model;

import java.util.Date;

public class Order {
    private int orderID;
    private int productID;
    private String customerUsername;
    private int orderStatus;
    private Date orderDate;
    private Date sendingDate;
    private Date receivingDate;

    public Order(int orderID, int productID, String customerUsername, int orderStatus, Date orderDate, Date sendingDate, Date receivingDate){
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }
}
