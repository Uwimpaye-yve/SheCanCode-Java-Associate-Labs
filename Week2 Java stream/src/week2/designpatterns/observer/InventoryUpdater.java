package week2.designpatterns.observer;

import week2.model.Order;

public class InventoryUpdater implements OrderObserver {
    @Override
    public void onEvent(Order order, OrderEvent event) {
        System.out.println("📦 [Warehouse System] Inventory updated for " + order.getItems().size() + " unique items on " + event);
    }
}