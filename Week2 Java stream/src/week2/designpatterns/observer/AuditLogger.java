package week2.designpatterns.observer;

import week2.model.Order;

public class AuditLogger implements OrderObserver {
    @Override
    public void onEvent(Order order, OrderEvent event) {
        System.out.println("📝 [Security Ledger] Security log timestamp recorded for event: " + event + " on Order ID: " + order.getOrderId());
    }
}