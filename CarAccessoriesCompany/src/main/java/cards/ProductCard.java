package cards;

import model.Category;

public class ProductCard{
    protected int productID;
    protected int numberOfOrders;
    protected double productPrice;
    protected String productName;
    protected String shortDescription;
    protected String imagePath;
    protected Category productCategory;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public ProductCard(){
        setProductID(-1);
        setNumberOfOrders(0);
        setProductPrice(-1);
        setProductName("");
        setShortDescription("");
        setImagePath("");
        setProductCategory(Category.Other);
    }
    public ProductCard(int productID, int numberOfOrders, double productPrice,
                       String productName, String shortDescription, String imagePath, Category productCategory) {
        setProductID(productID);
        setNumberOfOrders(numberOfOrders);
        setProductPrice(productPrice);
        setProductName(productName);
        setShortDescription(shortDescription);
        setImagePath(imagePath);
        setProductCategory(productCategory);
    }

}
