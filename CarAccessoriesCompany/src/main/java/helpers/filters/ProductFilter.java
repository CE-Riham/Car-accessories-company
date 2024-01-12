package helpers.filters;

import classes.Starter;
import model.products.Product;

import java.util.List;

public class ProductFilter {
    private ProductFilter(){
        Starter.logger.info("Hi from product filter.");
    }
    public static List<Product> filterProductsBy(String field, String fieldValue, List<Product> products) {
        if (fieldValue.equals("")) return products;
        switch (field) {
            case "productCategory":
                return filterByCategory(products, fieldValue);
            case "productName":
                return searchByProductName(products, fieldValue);
            case "productID":
                return searchByProductID(products, fieldValue);
            case "description":
                return searchByProductDescription(products, fieldValue);
            default:
                return products;
        }
    }

    private static List<Product> filterByCategory(List<Product> products, String category) {
        if(category.equals("All Categories"))return products;
        return products.stream()
                .filter(product -> category.equals(product.getProductCategory()))
                .toList();
    }

    private static List<Product> searchByProductID(List<Product> products, String productID) {
        return products.stream()
                .filter(product -> product.getProductID() == Integer.parseInt(productID))
                .toList();
    }

    private static List<Product> searchByProductName(List<Product> products, String productName) {
        return products.stream()
                .filter(product -> product.getProductName().toLowerCase().contains(productName.toLowerCase()))
                .toList();
    }

    private static List<Product> searchByProductDescription(List<Product> products, String description) {
        return products.stream()
                .filter(product -> product.getLongDescription().toLowerCase().contains(description.toLowerCase()) ||
                        product.getShortDescription().toLowerCase().contains(description.toLowerCase()))
                .toList();
    }
}
