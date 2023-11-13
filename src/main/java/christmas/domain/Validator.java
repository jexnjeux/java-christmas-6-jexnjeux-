package christmas.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Validator {

    private Validator() {
    }

    private static final Menu menu = MenuBoard.create();

    public static boolean validateDate(String input) {
        try {
            int num = Integer.parseInt(input);
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

        for (String orderItem : orderItems) {
            if (!validateOrderItem(orderItem, uniqueItems)) {
                return false;
            }
        }
        return true;
    }

    private static boolean validateOrderItem(String orderItem, Set<String> uniqueItems) {
        String[] order = orderItem.split("-");

        if (!menu.containsItem(order[0])) {
            return false;
        }

        if (!validateCount(order[1])) {
            return false;
        }

        return uniqueItems.add(order[0]);
    }

    private static boolean validateCount(String input) {
        try {
            int count = Integer.parseInt(input);
            return count > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
