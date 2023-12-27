package helpers.filters;

import model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductFilter {

    public static List<Product> filterProductsBy(String field, String fieldValue, List<Product>products){
        switch (field){
            case "productCategory":
                return filterByCategory(products, fieldValue);
            default:
                return products;
        }
    }
    private static List<Product> filterByCategory(List<Product> products, String category) {
        return products.stream()
                .filter(product -> category.equals(product.getProductCategory()))
                .collect(Collectors.toList());
    }
}
