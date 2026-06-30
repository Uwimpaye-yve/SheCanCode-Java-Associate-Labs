package week2.designpatterns.observer;

import week2.model.Order;

public class EmailNotifier implements OrderObserver {
    @Override
    public void onEvent(Order order, OrderEvent event) {
        System.out.println("✉️ [Email System] Alert sent: Order " + order.getOrderId() + " status updated to " + event);
    }
}