package week2.testing;

import week2.model.LineItem;
import week2.model.Order;
import week2.designpatterns.model.NotificationMessage;
import week2.designpatterns.strategy.*;
import week2.designpatterns.observer.*; // 🌟 Import all observer files

import java.util.ArrayList; // 🌟 Added missing import
import java.util.List;      // 🌟 Added missing import

public class PatternTests {
    public static void runPatternTests() {
        System.out.println("=== 🏗️ RUNNING BUILDER PATTERN TEST ===");
        try {
            NotificationMessage message = new NotificationMessage.Builder()
                    .to("developer@shecancode.org")
                    .subject("System Diagnostic Alert")
                    .body("The Java Analytics execution completed perfectly.")
                    .priority(NotificationMessage.Priority.HIGH)
                    .attach("LogReport.txt")
                    .build();
            System.out.println("✅ Successfully built: " + message);
        } catch (Exception e) {
            System.out.println("❌ Failed to build: " + e.getMessage());
        }

        System.out.println("\n=== 🎯 RUNNING STRATEGY PATTERN TEST ===");
        PaymentProcessor processor = new PaymentProcessor();
        PaymentRequest request = new PaymentRequest(50000.0, "0788123456");

        // Swap to Mobile Money dynamically at runtime
        processor.setPaymentStrategy(new MobileMoneyStrategy());
        PaymentResult momoResult = processor.executePayment(request);
        System.out.println(momoResult);

        System.out.println("\n=== 📢 RUNNING OBSERVER PATTERN TEST ===");

        // 1. Setup mock order for the event bus to use
        List<LineItem> dummyItems = new ArrayList<>();
        dummyItems.add(new LineItem("Wireless Mouse", 25000.0, 1, "PROD-MOU-02", "Electronics"));
        Order mockOrder = new Order("ORD-999", dummyItems, false);

        // 2. Instantiate our Event Bus Station
        OrderEventBus eventBus = new OrderEventBus();

        // 3. Register our systems (Subscribers)
        OrderObserver emailSystem = new EmailNotifier();
        OrderObserver warehouseSystem = new InventoryUpdater();
        OrderObserver securityLedger = new AuditLogger();

        eventBus.subscribe(emailSystem);
        eventBus.subscribe(warehouseSystem);
        eventBus.subscribe(securityLedger);

        // 4. Fire the event! Watch all 3 trigger in order
        eventBus.publish(mockOrder, OrderEvent.ORDER_PLACED);

        // 5. Demonstrate unregistering a subscriber dynamically
        System.out.println("\n--- Opting out of notifications ---");
        eventBus.unsubscribe(emailSystem);

        // Publish again: Email notifier won't fire this time!
        eventBus.publish(mockOrder, OrderEvent.ORDER_SHIPPED);
    } // Fixed: Removed the extra trailing brace that was here
}