package week2.service;

import week2.model.LineItem;
import week2.model.Order;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;


public class EcommerceAnalyticsEngine {
    public List<LineItem> getAllLineItems(List<Order> orders){
        return orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.toList());
    }

    public double calculateHighVolumeRevenue(List<LineItem> lineItems) {
        return lineItems.stream()
                .filter(item -> item.getQuantity() > 5)
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .reduce(0.0, (subtotal, element) -> subtotal + element);
    }

    public List<String> topNProductsByRevenue(List<Order> orders, int n) {
        //Flatten orders and group by product name while summing their revenues
        Map<String, Double> ProductRevenueMap = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        LineItem::getProductName,
                        Collectors.summingDouble(item -> item.getPrice() * item.getQuantity())
                ));

        return ProductRevenueMap.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())) // Sort highest to lowest
                .limit(n)                                                                   // Take only top N items
                .map(Map.Entry::getKey)                                                     // Extract only the product name
                .collect(Collectors.toList());
    }

    // Exercise 2.2 - Task 1: Group items by category and count them
    public Map<String, Long> countItemsByCategory(List<LineItem> lineItems) {
        return lineItems.stream()
                .collect(Collectors.groupingBy(
                        LineItem::getCategory, // Key: Category name
                        Collectors.counting()  // Value: Total count of items
                ));
    }

    // Exercise 2.2 - Task 2: Partition orders into delivered (true) and pending (false)
    public Map<Boolean, List<Order>> partitionOrdersByDeliveryStatus(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.partitioningBy(
                        Order::isDelivered // Splits the stream into true/false groups
                ));
    }

    // Exercise 2.2 - Task 3: Map product ID to price, merging duplicates by averaging them
    public Map<String, Double> getProductPriceMap(List<LineItem> lineItems) {
        return lineItems.stream()
                .collect(Collectors.toMap(
                        LineItem::getProductId,                      // Key Mapper
                        LineItem::getPrice,                          // Value Mapper
                        (price1, price2) -> (price1 + price2) / 2.0  // Merge function for duplicates
                ));
    }

    // Exercise 2.3: Parallel version of the Top-N Revenue Calculator
    public List<String> topNProductsByRevenueParallel(List<Order> orders, int n) {
        // 🌟 Notice the change from .stream() to .parallelStream()
        Map<String, Double> productRevenueMap = orders.parallelStream()
                .flatMap(order -> order.getItems().parallelStream())
                .collect(Collectors.groupingBy(
                        LineItem::getProductName,
                        Collectors.summingDouble(item -> item.getPrice() * item.getQuantity())
                ));

        return productRevenueMap.entrySet().parallelStream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
