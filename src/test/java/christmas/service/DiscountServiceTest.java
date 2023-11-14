package christmas.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import christmas.domain.MenuItem;
import christmas.domain.Order;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountServiceTest {

    private Order order;

    @BeforeEach
    public void createTestOrder() {
        order = new Order();
        order.addOrderItem(new MenuItem("해산물파스타", 35000), 2);
        order.addOrderItem(new MenuItem("초코케이크", 15000), 1);
        order.addOrderItem(new MenuItem("샴페인", 25000), 1);
        order.setReservedDate(25);
    }

    @DisplayName("평일 할인 + 특별 할인 + 크리스마스 할인 적용")
    @Test
    public void testCalculateDiscount() {

        DiscountService discountService = new DiscountService();

        Map<String, Integer> discountDetails = discountService.calculateDiscount(order);

        assertEquals(3400, discountDetails.get("christmas"));
        assertEquals(2023, discountDetails.get("weekdays"));
        assertEquals(0, discountDetails.get("weekends"));
        assertEquals(1000, discountDetails.get("special"));
    }

    @DisplayName("크리스마스 이후 평일 할인 + 특별 할인")
    @Test
    public void testCalculateDiscount_AfterChristmas() {
        order.setReservedDate(31);
        DiscountService discountService = new DiscountService();

        Map<String, Integer> discountDetails = discountService.calculateDiscount(order);

        assertEquals(0, discountDetails.get("christmas"));
        assertEquals(2023, discountDetails.get("weekdays"));
        assertEquals(0, discountDetails.get("weekends"));
        assertEquals(1000, discountDetails.get("special"));
    }

    @DisplayName("1000원 이하 할인 적용 안됨")
    @Test
    public void testCalculateDiscount_UnderPrice() {
        order = new Order();
        order.addOrderItem(new MenuItem("타파스", 5500), 1);
        order.setReservedDate(25);

        DiscountService discountService = new DiscountService();

        Map<String, Integer> discountDetails = discountService.calculateDiscount(order);

        assertEquals(0, discountDetails.get("christmas"));
        assertEquals(0, discountDetails.get("weekdays"));
        assertEquals(0, discountDetails.get("weekends"));
        assertEquals(0, discountDetails.get("special"));
    }

}