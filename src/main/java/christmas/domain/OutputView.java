package christmas.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private Menu menuBoard;

    public void printOrder(Order order) {
        menuBoard = MenuBoard.create();
        System.out.println("<주문 메뉴>");
        Map<MenuItem, Integer> orderItems = order.getOrderItems();
        Map<MenuType, List<MenuItem>> orderMap = createOrderMap(orderItems);

        // TODO: 애피타이저 -> 메인 -> 디저트 -> 음료 순 출력
        for (Entry<MenuType, List<MenuItem>> entry : orderMap.entrySet()) {
            for (MenuItem item : entry.getValue()) {
                int quantity = orderItems.get(item);
                System.out.println(item.getName() + " " + quantity + "개");
            }

        }
        System.out.println();
    }

    private Map<MenuType, List<MenuItem>> createOrderMap(Map<MenuItem, Integer> orderItems) {
        Map<MenuType, List<MenuItem>> orderMap = new HashMap<>();

        for (Map.Entry<MenuItem, Integer> entry : orderItems.entrySet()) {
            MenuItem menuItem = entry.getKey();
            String itemName = menuItem.getName();
            MenuType menuType = menuBoard.findMenuType(itemName);

            orderMap.computeIfAbsent(menuType, k -> new ArrayList<>());
            orderMap.get(menuType).add(menuItem);
        }

        return orderMap;
    }

    public void printTotalAmountBeforeDiscount(Order order) {
        int totalAmount = 0;
        Map<MenuItem, Integer> orderItems = order.getOrderItems();
        for (Entry<MenuItem, Integer> entry : orderItems.entrySet()) {
            totalAmount += entry.getKey().getPrice() * entry.getValue();
        }
        System.out.println("<할인 전 총주문 금액>");
        String formattedAmount = String.format("%,d", totalAmount);
        System.out.println(formattedAmount + "원");
        System.out.println();

    }
}
