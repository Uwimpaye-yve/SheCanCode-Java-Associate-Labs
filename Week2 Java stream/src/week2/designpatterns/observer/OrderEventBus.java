package week2.designpatterns.observer;

import week2.model.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderEventBus {
    // The master list tracking all registered subscribers
    private final List<OrderObserver> observers = new ArrayList<>();

    // Add a new system subscriber
    public void subscribe(OrderObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    // Remove a system subscriber
    public void unsubscribe(OrderObserver observer) {
        observers.remove(observer);
    }

    // Broadcast the event out to everyone on the list sequentially
    public void publish(Order order, OrderEvent event) {
        System.out.println("\n📢 [Event Bus] Broadcasting event: " + event + " for Order: " + order.getOrderId());
        for (OrderObserver observer : observers) {
            observer.onEvent(order, event);
        }
    }
}