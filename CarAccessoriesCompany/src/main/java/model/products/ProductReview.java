package model.products;

public class ProductReview {
    private String customerComment;
    private int reviewID;
    private int productID;

    public String getCustomerComment() {
        return customerComment;
    }

    public void setCustomerComment(String customerComment) {
        this.customerComment = customerComment;
    }

    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "ProductReview{" +
                "reviewID=" + reviewID +
                ": customerComment='" + customerComment + '\'' +
                '}';
    }
}
