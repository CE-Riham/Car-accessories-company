package model;

import cards.ProductCard;

import java.util.HashMap;
import java.util.Map;

public class Product extends ProductCard {
    private Map<String, Integer> colorQuantity;
    private int availableAmount;
    private String longDescription;

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void addColor(String color, int quantity){
        colorQuantity.put(color, quantity);
    }

    public int getColorQuantity(String color){
        return colorQuantity.get(color);
    }
    public Product(){
        setAvailableAmount(0);
        setLongDescription("");
        colorQuantity = new HashMap<>();
    }
    public Product(int productID, int availableAmount, int numberOfOrders, double productPrice,
                   String productName, String longDescription,
                   String shortDescription, String imagePath, Category productCategory) {
        super(productID, numberOfOrders, productPrice, productName, shortDescription, imagePath, productCategory);
        setAvailableAmount(availableAmount);
        setLongDescription(longDescription);
        colorQuantity = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", availableAmount=" + availableAmount +
                ", numberOfOrders=" + numberOfOrders +
                ", productPrice=" + productPrice +
                ", productName='" + productName + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", productCategory=" + productCategory +
                '}';
    }
}
