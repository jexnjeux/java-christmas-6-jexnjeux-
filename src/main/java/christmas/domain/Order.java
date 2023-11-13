package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class Order {

    Map<MenuItem, Integer> orderItems;

    public Order() {
        this.orderItems = new HashMap<>();
    }

    public void addOrderItem(MenuItem item, int quantity) {
        orderItems.put(item, quantity);
    }

    public Map<MenuItem, Integer> getOrderItems() {
        return orderItems;
    }
}
