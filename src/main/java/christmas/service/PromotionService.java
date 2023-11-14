package christmas.service;

import christmas.domain.Menu;
import christmas.domain.MenuBoard;
import christmas.domain.MenuItem;
import christmas.type.MenuType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class PromotionService {

    private static final int DAILY_DISCOUNT_PRICE = 100;
    private static final int UNIT_DISCOUNT_PRICE = 2023;
    private static final int DISCOUNT_STARTING_PRICE = 1000;
    private static final int MINIMUM_PRICE = 120000;
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int CHRISTMAS_DAY = 25;
    private static final int MONDAY = 1;
    private static final int THURSDAY = 4;
    private static final int FRIDAY = 5;
    private static final int SATURDAY = 6;
    private static final int SUNDAY = 7;
    private static final List<Integer> SPECIAL_DAYS = new ArrayList<>(Arrays.asList(3, 10, 17, 24, 25, 31));
    private Menu menuBoard;

    public PromotionService() {
        menuBoard = MenuBoard.create();
    }

    public int calculateChristmasDiscount(int date) {
        if (date > CHRISTMAS_DAY) {
            return 0;
        } else {
            return DISCOUNT_STARTING_PRICE + ((date - 1) * DAILY_DISCOUNT_PRICE);
        }
    }

    public int calculateWeekdaysDiscount(int date, Map<MenuItem, Integer> orderItems) {
        int value = getValueOfWeek(date);
        int count = 0;
        if (isWeekday(value)) {
            for (Entry<MenuItem, Integer> entry : orderItems.entrySet()) {
                String itemName = entry.getKey().getName();
                MenuType itemType = menuBoard.findMenuType(itemName);
                if (itemType == MenuType.DESERT) {
                    count += entry.getValue();
                }
            }
        }
        return count * UNIT_DISCOUNT_PRICE;
    }

    private int getValueOfWeek(int date) {
        LocalDate reservationDate = LocalDate.of(YEAR, MONTH, date);
        return reservationDate.getDayOfWeek().getValue();
    }

    private boolean isWeekday(int value) {
        return MONDAY <= value && value <= THURSDAY || value == SUNDAY;
    }

    public int calculateWeekendsDiscount(int date, Map<MenuItem, Integer> orderItems) {
        int value = getValueOfWeek(date);
        int count = 0;
        if (isWeekends(value)) {
            for (Entry<MenuItem, Integer> entry : orderItems.entrySet()) {
                String itemName = entry.getKey().getName();
                MenuType itemType = menuBoard.findMenuType(itemName);
                if (itemType == MenuType.MAIN) {
                    count += entry.getValue();
                }
            }
        }
        return count * UNIT_DISCOUNT_PRICE;
    }

    public int calculateSpecialDiscount(int date) {
        if (SPECIAL_DAYS.contains(date)) {
            return DISCOUNT_STARTING_PRICE;
        }
        return 0;
    }

    private boolean isWeekends(int value) {
        return value == FRIDAY || value == SATURDAY;
    }

    public boolean isFreeGiftEligible(int beforeTotalPrice) {
        return beforeTotalPrice >= MINIMUM_PRICE;
    }
}
