package christmas.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Order {

    private int reservedDate;

    Map<MenuItem, Integer> orderItems;

    public Order() {
        this.orderItems = new HashMap<>();
        this.reservedDate = -1;
    }

    public void addOrderItem(MenuItem item, int quantity) {
        orderItems.put(item, quantity);
    }

    public void setReservedDate(int date) {
        this.reservedDate = date;
    }

    public static int sumPrice(Order order) {
        int totalPrice = 0;
        Map<MenuItem, Integer> orderItems = order.getOrderItems();
        for (Entry<MenuItem, Integer> entry : orderItems.entrySet()) {
            MenuItem item = entry.getKey();
            int quantity = entry.getValue();
            if (item != null) {
                totalPrice += item.getPrice() * quantity;
            }
        }
        return totalPrice;
    }

    public Map<MenuItem, Integer> getOrderItems() {
        return orderItems;
    }

    public int getReservedDate() {
        return reservedDate;
    }
}
