package helpers.comparators;

import model.Product;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductComparators {
    public static List<Product> sortProductsByID(List<Product> products, boolean sortingType) {
        if (sortingType)
            Collections.sort(products, Comparator.comparingInt(Product::getProductID));
        else
            Collections.sort(products, Comparator.comparingInt(Product::getProductID).reversed());
        return products;
    }

    public static List<Product> sortProductsByPrice(List<Product> products, boolean sortingType) {
        if (sortingType)
            Collections.sort(products, Comparator.comparingDouble(Product::getProductPrice));
        else
            Collections.sort(products, Comparator.comparingDouble(Product::getProductPrice).reversed());
        return products;
    }

    public static List<Product> sortProductsByNumberOfOrders(List<Product> products, boolean sortingType) {
        if (sortingType)
            Collections.sort(products, Comparator.comparingInt(Product::getNumberOfOrders));
        else
            Collections.sort(products, Comparator.comparingInt(Product::getNumberOfOrders).reversed());
        return products;
    }

    public static List<Product> sortProductsByAvailability(List<Product> products, boolean sortingType) {
        if (sortingType)
            Collections.sort(products, Comparator.comparingInt(Product::getAvailableAmount));
        else
            Collections.sort(products, Comparator.comparingInt(Product::getAvailableAmount).reversed());
        return products;
    }
}
