package model;

import cards.ProductCard;

public class Product extends ProductCard {
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

    public Product(){
        super();
        setAvailableAmount(0);
        setLongDescription("");
    }
    public Product(int productID, int availableAmount, int numberOfOrders, double productPrice,
                   String productName, String longDescription,
                   String shortDescription, String imagePath, Category productCategory) {
        super(productID, numberOfOrders, productPrice, productName, shortDescription, imagePath, productCategory);
        setAvailableAmount(availableAmount);
        setLongDescription(longDescription);
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
