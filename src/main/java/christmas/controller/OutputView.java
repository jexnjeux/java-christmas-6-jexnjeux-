package christmas.controller;

import christmas.domain.Menu;
import christmas.domain.MenuBoard;
import christmas.domain.MenuItem;
import christmas.domain.Order;
import christmas.service.BadgeService;
import christmas.service.DiscountService;
import christmas.service.PromotionService;
import christmas.type.MenuType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private Menu menuBoard;
    private PromotionService promotionService;
    private DiscountService discountService;

    private static final Map<String, String> DISCOUNT_NAMES = Map.of(
            "christmas", "크리스마스 디데이 할인",
            "weekdays", "평일 할인",
            "weekends", "주말 할인",
            "special", "특별 할인"
    );

    public OutputView() {
        promotionService = new PromotionService();
        discountService = new DiscountService();
    }

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
        int totalPrice = Order.sumPrice(order);
        System.out.println("<할인 전 총주문 금액>");
        String formattedAmount = String.format("%,d", totalPrice);
        System.out.println(formattedAmount + "원");
        System.out.println();
    }

    public void printFreeGift(Order order) {
        System.out.println("<증정 메뉴>");
        System.out.println(getGiftMessage(order));
        System.out.println();
    }

    public String getGiftMessage(Order order) {
        int totalPrice = Order.sumPrice(order);
        boolean freeGiftEligible = promotionService.isFreeGiftEligible(totalPrice);
        if (freeGiftEligible) {
            return "샴페인 1개";
        }
        return "없음";
    }

    public void printBenefit(Order order) {
        System.out.println("<혜택 내역>");
        Map<String, Integer> discountDetails = discountService.calculateDiscount(order);
        if (discountDetails.isEmpty()) {
            System.out.println("없음");
        } else {
            for (Map.Entry<String, Integer> entry : discountDetails.entrySet()) {
                printDiscount(entry.getKey(), entry.getValue());
            }
        }
        System.out.println();
        System.out.println("<총혜택 금액>");
        int totalDiscount = discountDetails.values().stream().mapToInt(Integer::intValue).sum();
        System.out.printf("%,d원%n", totalDiscount * -1);
        printDiscountedPrice(order, totalDiscount);
        printBadge(totalDiscount);
    }

    private void printDiscount(String keyword, Integer price) {
        if (price != null && !price.equals(0)) {
            System.out.printf("%s: %,d원%n", DISCOUNT_NAMES.get(keyword), price * -1);
        }
    }

    private void printDiscountedPrice(Order order, int totalDiscount) {
        System.out.println("<할인 후 예상 결제 금액>");
        int discountedPrice = Order.sumPrice(order) - totalDiscount;
        System.out.printf("%,d원%n", discountedPrice);
    }

    public void printBadge(int totalDiscount) {
        String badge = BadgeService.grantBadge(totalDiscount);
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge);
    }
}
