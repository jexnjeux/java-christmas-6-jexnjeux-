package christmas.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.service.PromotionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PromotionServiceTest {

    PromotionService promotionService = new PromotionService();

    @DisplayName("크리스마스 할인 적용")
    @Test
    void testChristmasDiscount() {
        int date = 2;
        int discount = promotionService.calculateChristmasDiscount(date);
        assertEquals(1100, discount);
    }

    @DisplayName("크리스마스 이후는 할인 적용 안됨")
    @Test
    void testChristmasDiscount_AfterChristmas() {
        int date = 30;
        int discount = promotionService.calculateChristmasDiscount(date);
        assertEquals(0, discount);
    }

    @DisplayName("평일 할인")
    @Test
    void testWeekdaysDiscount() {
        int date = 6;
        Order order = new Order();
        order.addOrderItem(new MenuItem("초코케이크", 15000), 2);
        int discount = promotionService.calculateWeekdaysDiscount(date, order.orderItems);
        assertEquals(2023 * 2, discount);
    }

    @DisplayName("주말에는 평일 할인 적용 안됨")
    @Test
    void testWeekdaysDiscount_NotWeekends() {
        int date = 16;
        Order order = new Order();
        order.addOrderItem(new MenuItem("초코케이크", 15000), 2);
        int discount = promotionService.calculateWeekdaysDiscount(date, order.orderItems);
        assertEquals(0, discount);
    }

    @DisplayName("주말 할인")
    @Test
    void testWeekendsDiscount() {
        int date = 9;
        Order order = new Order();
        order.addOrderItem(new MenuItem("바비큐립", 54000), 2);
        int discount = promotionService.calculateWeekendsDiscount(date, order.getOrderItems());
        assertEquals(2023 * 2, discount);
    }

    @DisplayName("평일에는 주말 할인 적용 안됨")
    @Test
    void testWeekendsDiscount_NotWeekdays() {
        int date = 19;
        Order order = new Order();
        order.addOrderItem(new MenuItem("바비큐립", 54000), 2);
        int discount = promotionService.calculateWeekendsDiscount(date, order.getOrderItems());
        assertEquals(0, discount);
    }

    @DisplayName("할인 전 금액이 120000원 이상인 경우 증정 제공")
    @Test
    void testFreeGift() {
        int beforeTotalPrice = 130000;
        boolean result = promotionService.isFreeGiftEligible(beforeTotalPrice);
        assertTrue(result);
    }

    @DisplayName("할인 전 금액이 120000원 미만인 경우 증정 제공 없음")
    @Test
    void testFreeGift_NotUnder() {
        int beforeTotalPrice = 100000;
        boolean result = promotionService.isFreeGiftEligible(beforeTotalPrice);
        assertFalse(result);
    }

}