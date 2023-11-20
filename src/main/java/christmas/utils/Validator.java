package christmas.utils;

import christmas.domain.Menu;
import christmas.domain.MenuBoard;
import christmas.type.MenuType;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Validator {

    private Validator() {
    }

    private static final Menu menu = MenuBoard.create();

    public static boolean validateDate(String input) {
        try {
            int num = Integer.parseInt(input.trim());
            return (1 <= num && num <= 31);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String[] orderItemParser(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toArray(String[]::new);
    }

    public static boolean validateOrder(String input) {
        String[] orderItems = orderItemParser(input);
        Set<String> uniqueItems = new HashSet<>();
        try {

            for (String orderItem : orderItems) {
                if (!validateOrderItem(orderItem, uniqueItems)) {
                    throw new IllegalArgumentException("잘못된 입력");
                }
            }

            return validateBeverage(orderItems);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean validateOrderItem(String orderItem, Set<String> uniqueItems) {
        String[] order = orderItem.split("-");
        try {

            if (orderItem.length() > 20) {
                throw new IllegalArgumentException("주문 개수 20개 초과");
            }
            if (order.length != 2) {
                throw new IllegalArgumentException("잘못된 입력");
            }
            if (!menu.containsItem(order[0])) {
                throw new IllegalArgumentException("중복 메뉴 입력");
            }
            if (!validateCount(order[1])) {
                throw new IllegalArgumentException("잘못된 입력");
            }
            return uniqueItems.add(order[0]);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean validateCount(String input) {
        try {
            int count = Integer.parseInt(input);
            return count > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean validateBeverage(String[] orderItems) {
        Menu menuBoard = MenuBoard.create();
        Set<MenuType> type = new HashSet<>();
        try {

            for (String orderItem : orderItems) {
                String[] order = orderItem.split("-");
                MenuType menuType = menuBoard.findMenuType(order[0]);
                type.add(menuType);
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return type.size() != 1 || !type.contains(MenuType.BEVERAGE);

    }
}
