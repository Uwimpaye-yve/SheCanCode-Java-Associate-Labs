package week2.designpatterns.observer;

import week2.model.Order;

public interface OrderObserver {
    void onEvent(Order order, OrderEvent event);
}