package helpers.comparators;

import classes.Starter;
import model.products.Product;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductComparator {

    private ProductComparator(){
        Starter.logger.info("Hi from product comparator.");
    }
    public static List<Product> sortProducts(String field, boolean sortingType, List<Product> products) {
        Collections.sort(products, chooseProductComparator(field, sortingType));
        return products;
    }

    private static Comparator<Product> chooseProductComparator(String field, boolean sortingType){
        switch (field) {
            case "price":
                return sortProductsByPriceComparator(sortingType);
            case "numberOfOrders":
                return sortProductsByNumberOfOrdersComparator(sortingType);
            case "availability":
                return sortProductsByAvailabilityComparator(sortingType);
            default:
                return sortProductsByIDComparator(sortingType);
        }
    }
    private static Comparator<Product> sortProductsByIDComparator(boolean sortingType) {
        return (sortingType ? Comparator.comparingInt(Product::getProductID)
                : Comparator.comparingInt(Product::getProductID).reversed());
    }

    private static Comparator<Product> sortProductsByPriceComparator(boolean sortingType) {
        return (sortingType ? Comparator.comparingDouble(Product::getProductPrice)
                : Comparator.comparingDouble(Product::getProductPrice).reversed());
    }

    private static Comparator<Product> sortProductsByNumberOfOrdersComparator(boolean sortingType) {
        return (sortingType ? Comparator.comparingInt(Product::getNumberOfOrders)
                : Comparator.comparingInt(Product::getNumberOfOrders).reversed());
    }

    private static Comparator<Product> sortProductsByAvailabilityComparator(boolean sortingType) {
        return (sortingType ? Comparator.comparingInt(Product::getAvailableAmount)
                : Comparator.comparingInt(Product::getAvailableAmount).reversed());
    }
}
