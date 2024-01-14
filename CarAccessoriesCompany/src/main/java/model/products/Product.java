package model.products;

public class Product {
    private int availableAmount;
    private String longDescription;
    protected int productID;
    protected int numberOfOrders;
    protected double productPrice;
    protected String productName;
    protected String shortDescription;
    protected String imagePath;
    protected String productCategory;

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
        return (imagePath.equals("") ? "src/main/resources/assets/products/noproduct.png" : imagePath);
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Product() {
        setProductID(-1);
        setNumberOfOrders(0);
        setProductPrice(-1);
        setProductName("");
        setShortDescription("");
        setImagePath("");
        setProductCategory("OTHERS");
        setAvailableAmount(0);
        setLongDescription("");
    }

    public Product(int productID, int availableAmount, int numberOfOrders, double productPrice,
                   String productName, String longDescription,
                   String shortDescription, String imagePath, String productCategory) {
        setProductID(productID);
        setNumberOfOrders(numberOfOrders);
        setProductPrice(productPrice);
        setProductName(productName);
        setShortDescription(shortDescription);
        setImagePath(imagePath);
        setProductCategory(productCategory);
        setAvailableAmount(availableAmount);
        setLongDescription(longDescription);
    }

    public Product(Product product){
        this(product.productID, product.getAvailableAmount(), product.getNumberOfOrders(), product.getProductPrice(),
                product.getProductName(), product.getLongDescription(), product.getShortDescription(),
                product.getImagePath(), product.productCategory);
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
