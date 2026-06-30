package week2.model;

import java.util.List;

public class Order {

    private String orderId;
    private List<LineItem> items;
    private boolean delivered;


    public Order(String orderId, List<LineItem> items, boolean delivered) {
        this.orderId = orderId;
        this.items = items;
        this.delivered = delivered;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<LineItem> getItems() {
        return items;
    }
    public boolean isDelivered() {
        return delivered;
    }
}