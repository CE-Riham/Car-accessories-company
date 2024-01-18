package helpers.filters;

import classes.Starter;
import helpers.Generator;
import model.Order;

import java.util.List;

public class OrderFilter {
    private OrderFilter() {
        Starter.logger.info("Hi from order filter.");
    }

    public static List<Order> filterOrdersBy(String field, String fieldValue, List<Order> orders) {
        if (fieldValue.isEmpty()) return orders;
        return switch (field) {
            case "orderID" -> filterByOrderID(orders, fieldValue);
            case "productID" -> searchByProductID(orders, fieldValue);
            case "customerUsername" -> searchByCustomerUsername(orders, fieldValue);
            case "orderStatus" -> searchByOrderStatus(orders, fieldValue);
            case "orderDate" -> searchByOrderDate(orders, fieldValue);
            case "sendingDate" -> searchBySendingDate(orders, fieldValue);
            case "receivingDate" -> searchByReceivingDate(orders, fieldValue);
            default -> orders;
        };
    }

    private static List<Order> filterByOrderID(List<Order> orders, String orderID) {
        return orders.stream()
                .filter(order -> order.getOrderID() == Integer.parseInt(orderID))
                .toList();
    }

    private static List<Order> searchByProductID(List<Order> orders, String productID) {
        return orders.stream()
                .filter(order -> order.getProductID() == Integer.parseInt(productID))
                .toList();
    }

    private static List<Order> searchByCustomerUsername(List<Order> orders, String customerUsername) {
        return orders.stream()
                .filter(order -> order.getCustomerUsername().equalsIgnoreCase(customerUsername))
                .toList();
    }

    private static List<Order> searchByOrderStatus(List<Order> orders, String orderStatus) {
        return orders.stream()
                .filter(order -> order.getOrderStatus() == Integer.parseInt(orderStatus))
                .toList();
    }

    private static List<Order> searchByOrderDate(List<Order> orders, String orderDate) {
        return orders.stream()
                .filter(order -> order.getOrderDate().equals(Generator.stringToDateConverter(orderDate)))
                .toList();
    }

    private static List<Order> searchBySendingDate(List<Order> orders, String sendingDate) {
        return orders.stream()
                .filter(order -> order.getSendingDate().equals(Generator.stringToDateConverter(sendingDate)))
                .toList();
    }

    private static List<Order> searchByReceivingDate(List<Order> orders, String receivingDate) {
        return orders.stream()
                .filter(order -> order.getReceivingDate().equals(Generator.stringToDateConverter(receivingDate)))
                .toList();
    }
}
