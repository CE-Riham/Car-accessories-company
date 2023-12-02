package model;

import io.cucumber.messages.types.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog {
    public void selectCategory(String interior) {
    }
    public boolean hasProductListings() {
        return true;
    }
    public void clickOnProduct(String productName) {
        System.out.println("Clicked on product: " + productName);

    }
    public String getProductDetails(String productName) {
        return "Details for product: " + productName;

    }

    public List<Product> searchByQuery(String specificProductQuery) {
        return new ArrayList<>();
    }

    public int getFilteredResultsCount() {
        return 3;
    }

    public void applyFilters(String brandFilter, double minPrice, double maxPrice) {
    }

    public int getSearchResultsCount() {
        return 5;
    }

    public void searchForProduct(String searchTerm) {
    }
}
