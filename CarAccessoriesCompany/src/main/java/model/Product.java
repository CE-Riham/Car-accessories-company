package model;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private Map<String, Integer> colorQuantity = new HashMap<>();
    private int productID;
    private int availableAmount;
    private int numberOfOrders;
    private double productPrice;
    private double productOffer;
    private String productName;
    private String longDescription;
    private String shortDescription;
    private String imagePath;
    private Category productCategory;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductOffer() {
        return productOffer;
    }

    public void setProductOffer(double productOffer) {
        this.productOffer = productOffer;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getImagePath() {
        return (imagePath.equals("") ? "/assets/products/noproduct.png":imagePath);
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public void addColor(String color, int quantity){
        colorQuantity.put(color, quantity);
    }

    public int getColorQuantity(String color){
        return colorQuantity.get(color);
    }
    public Product(){
        setProductID(-1);
        setAvailableAmount(0);
        setNumberOfOrders(0);
        setProductPrice(-1);
        setProductOffer(-1);
        setProductName("");
        setLongDescription("");
        setShortDescription("");
        setImagePath("");
        setProductCategory(Category.Other);
    }
    public Product(int productID, int availableAmount, int numberOfOrders, double productPrice,
                   double productOffer, String productName, String longDescription,
                   String shortDescription, String imagePath, Category productCategory) {
        setProductID(productID);
        setAvailableAmount(availableAmount);
        setNumberOfOrders(numberOfOrders);
        setProductPrice(productPrice);
        setProductOffer(productOffer);
        setProductName(productName);
        setLongDescription(longDescription);
        setShortDescription(shortDescription);
        setImagePath(imagePath);
        setProductCategory(productCategory);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", availableAmount=" + availableAmount +
                ", numberOfOrders=" + numberOfOrders +
                ", productPrice=" + productPrice +
                ", productOffer=" + productOffer +
                ", productName='" + productName + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", productCategory=" + productCategory +
                '}';
    }
}
