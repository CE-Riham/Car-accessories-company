package helpers.comparators;

import classes.Starter;
import model.Order;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrderComparator {

    private OrderComparator() {
        Starter.logger.info("Hi from order comparator.");
    }

    public static List<Order> sortOrders(String field, boolean sortingType, List<Order> orders) {
        Collections.sort(orders, chooseOrderComparator(field, sortingType));
        return orders;
    }

    private static Comparator<Order> chooseOrderComparator(String field, boolean sortingType) {
        switch (field) {
            case "productID":
                return sortOrdersByProductIDComparator(sortingType);
            case "orderDate":
                return sortProductsByOrderDateComparator(sortingType);
            case "sendingDate":
                return sortProductsBySendingDateComparator(sortingType);
            case "receivingDate":
                return sortProductsByReceivingDateComparator(sortingType);
            default:
                return sortOrdersByIDComparator(sortingType);
        }
    }

    private static Comparator<Order> sortOrdersByIDComparator(boolean sortingType) {
        return (sortingType ?
                Comparator.comparingInt(Order::getOrderID) :
                Comparator.comparingInt(Order::getOrderID).reversed());
    }

    private static Comparator<Order> sortOrdersByProductIDComparator(boolean sortingType) {
        return (sortingType ?
                Comparator.comparingDouble(Order::getProductID) :
                Comparator.comparingDouble(Order::getProductID).reversed());
    }

    private static Comparator<Order> sortProductsByOrderDateComparator(boolean sortingType) {
        return (sortingType ?
                Comparator.comparing(Order::getOrderDate) :
                Comparator.comparing(Order::getOrderDate).reversed());
    }

    private static Comparator<Order> sortProductsBySendingDateComparator(boolean sortingType) {
        return (sortingType ?
                Comparator.comparing(Order::getSendingDate) :
                Comparator.comparing(Order::getSendingDate).reversed());
    }

    private static Comparator<Order> sortProductsByReceivingDateComparator(boolean sortingType) {
        return (sortingType ?
                Comparator.comparing(Order::getReceivingDate) :
                Comparator.comparing(Order::getReceivingDate).reversed());
    }

}
