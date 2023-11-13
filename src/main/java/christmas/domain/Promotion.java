package christmas.domain;

import java.time.LocalDate;

public class Promotion {

    private static final int DAILY_DISCOUNT_AMOUNT = 100;
    private static final int UNIT_DISCOUNT_AMOUNT = 2023;
    private static final int DISCOUNT_STARTING_AMOUNT = 1000;
    private static final int MINIMUM_AMOUNT = 120000;
    private static final int YEAR = 2023;
    private static final int MONTH = 12;
    private static final int CHRISTMAS_DAY = 25;
    private static final int MONDAY = 1;
    private static final int THURSDAY = 4;
    private static final int FRIDAY = 5;
    private static final int SATURDAY = 6;
    private static final int SUNDAY = 7;

    public int calculateChristmasDiscount(int date) {
        if (date > CHRISTMAS_DAY) {
            return 0;
        } else {
            return DISCOUNT_STARTING_AMOUNT + (date * DAILY_DISCOUNT_AMOUNT);
        }
    }

    public int calculateWeekdaysDiscount(int date, int menuSize) {
        int value = getValueOfWeek(date);
        if (isWeekday(value)) {
            return menuSize * UNIT_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    private static int getValueOfWeek(int date) {
        LocalDate reservationDate = LocalDate.of(YEAR, MONTH, date);
        return reservationDate.getDayOfWeek().getValue();
    }

    private static boolean isWeekday(int value) {
        return MONDAY <= value && value <= THURSDAY || value == SUNDAY;
    }

    public int calculateWeekendsDiscount(int date, int menuSize) {
        int value = getValueOfWeek(date);
        if (isWeekends(value)) {
            return menuSize * UNIT_DISCOUNT_AMOUNT;
        }
        return 0;
    }

    private static boolean isWeekends(int value) {
        return value == FRIDAY || value == SATURDAY;
    }

    public boolean isFreeGiftEligible(int beforeTotalPrice) {
        return beforeTotalPrice >= MINIMUM_AMOUNT;
    }
}
