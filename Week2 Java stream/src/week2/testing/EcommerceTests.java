package week2.testing;

import week2.model.LineItem;
import week2.model.Order;
import week2.service.EcommerceAnalyticsEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EcommerceTests {
    public static void runAllEcommerceTests() {
        EcommerceAnalyticsEngine engine = new EcommerceAnalyticsEngine();

        // 1. Setup Mock Line Items (Now with ProductID and Category)
        // Constructor shape: LineItem(name, price, quantity, productId, category)
        List<LineItem> order1Items = new ArrayList<>();
        order1Items.add(new LineItem("Lenovo Laptop", 800000.0, 6, "PROD-LEN-01", "Electronics"));
        order1Items.add(new LineItem("Wireless Mouse", 25000.0, 2, "PROD-MOU-02", "Electronics"));

        List<LineItem> order2Items = new ArrayList<>();
        // Adding a duplicate item with a different price point to test our map merge function safely
        order2Items.add(new LineItem("Lenovo Laptop", 840000.0, 2, "PROD-LEN-01", "Electronics"));
        order2Items.add(new LineItem("Office Chair", 120000.0, 4, "PROD-CHR-03", "Furniture"));

        // 2. Setup Mock Orders (Now with Delivery Status boolean at the end)
        // Constructor shape: Order(orderId, items, isDelivered)
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("ORD-001", order1Items, true));  // Delivered
        orders.add(new Order("ORD-002", order2Items, false)); // Pending

        System.out.println("=== 🛒 RUNNING ADVANCED E-COMMERCE COLLECTOR TESTS 🛒 ===");

        // Gather a flat list of all items across orders to feed our tasks
        List<LineItem> allItems = engine.getAllLineItems(orders);

        // Test 1: Group and Count by Category
        Map<String, Long> categoryCounts = engine.countItemsByCategory(allItems);
        System.out.println("📦 Items Counted By Category: " + categoryCounts);

        // Test 2: Binary Partitioning by Delivery Status
        Map<Boolean, List<Order>> partitionedOrders = engine.partitionOrdersByDeliveryStatus(orders);
        System.out.println("🚚 Delivered Orders (true): " + partitionedOrders.get(true).size());
        System.out.println("⏳ Pending Orders (false): " + partitionedOrders.get(false).size());

        // Test 3: Unique Map with Duplicate Merge Conflict Resolution
        Map<String, Double> productPriceMap = engine.getProductPriceMap(allItems);
        System.out.println("💳 Product Price Map (Averaged Duplicates): " + productPriceMap);

        System.out.println("========================================================\n");
    }

    public static void runPerformanceBenchmark() {
        System.out.println("⏳ Generating 100,000 random mock orders for stress-testing...");
        List<Order> largeOrderDataset = new ArrayList<>();

        // Generate 100,000 dummy orders programmatically
        for (int i = 0; i < 100000; i++) {
            List<LineItem> items = new ArrayList<>();
            items.add(new LineItem("Laptop", 800000.0, 2, "PROD-01", "Electronics"));
            items.add(new LineItem("Mouse", 25000.0, 5, "PROD-02", "Electronics"));
            largeOrderDataset.add(new Order("ORD-" + i, items, true));
        }

        EcommerceAnalyticsEngine engine = new EcommerceAnalyticsEngine();

        // --- Benchmark 1: Sequential Processing ---
        long startTimeSeq = System.nanoTime();
        List<String> seqResult = engine.topNProductsByRevenue(largeOrderDataset, 2);
        long endTimeSeq = System.nanoTime();
        double seqDurationMs = (endTimeSeq - startTimeSeq) / 1_000_000.0;

        // --- Benchmark 2: Parallel Processing ---
        long startTimePar = System.nanoTime();
        List<String> parResult = engine.topNProductsByRevenueParallel(largeOrderDataset, 2);
        long endTimePar = System.nanoTime();
        double parDurationMs = (endTimePar - startTimePar) / 1_000_000.0;

        // --- Verification & Output ---
        System.out.println("\n=== ⚡ PERFORMANCE BENCHMARK RESULTS ⚡ ===");
        System.out.println("Sequential Stream Time : " + seqDurationMs + " ms");
        System.out.println("Parallel Stream Time   : " + parDurationMs + " ms");
        System.out.println("Results Match Exactly  : " + seqResult.equals(parResult));
        System.out.println("============================================\n");
    }
}