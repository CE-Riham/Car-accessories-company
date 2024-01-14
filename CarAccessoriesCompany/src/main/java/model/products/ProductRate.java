package model.products;

public class ProductRate {
    //-------------------------------------------------- section1: Attributes --------------------------------------------------//
    private int productRateID;
    private int productID;
    private int customerRate;

    //------------------------------------------------- section2: Constructors -------------------------------------------------//
    public ProductRate() {
        this.productRateID = 0;
        this.productID = 0;
        this.customerRate = 0;
    }

    //----------------------------------------------- section3: Setters & Getters ----------------------------------------------//
    public int getProductRateID() {
        return productRateID;
    }

    public void setProductRateID(int productRateID) {
        this.productRateID = productRateID;
    }


    public int getProductID() {
        return productID;
    }


    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCustomerRate() {
        return customerRate;
    }

    public void setCustomerRate(int customerRate) {
        this.customerRate = customerRate;
    }

}
