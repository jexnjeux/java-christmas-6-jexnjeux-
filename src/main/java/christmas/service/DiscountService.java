package christmas.service;

import christmas.domain.Order;
import java.util.HashMap;
import java.util.Map;

public class DiscountService {

    private final Map<String, Integer> discountDetails;
    private final PromotionService promotionService;

    public DiscountService() {
        this.discountDetails = new HashMap<>();
        this.promotionService = new PromotionService();
    }

    public Map<String, Integer> calculateDiscount(Order order) {
        int totalPrice = Order.sumPrice(order);
        if (totalPrice >= 10000) {
            calculateChristmasDiscount(order, discountDetails);
            calculateWeekdaysDiscount(order, discountDetails);
            calculateWeekendsDiscount(order, discountDetails);
            calculateSpecialDiscount(order, discountDetails);
        }
        return discountDetails;
    }


    private int calculateChristmasDiscount(Order order, Map<String, Integer> discountDetails) {
        int christmasDiscount = promotionService.calculateChristmasDiscount(order.getReservedDate());
        discountDetails.put("christmas", christmasDiscount);
        return christmasDiscount;
    }

    private int calculateWeekdaysDiscount(Order order, Map<String, Integer> discountDetails) {
        int weekdaysDiscount = promotionService.calculateWeekdaysDiscount(order.getReservedDate(), order.getOrderItems().size());
        discountDetails.put("weekdays", weekdaysDiscount);
        return weekdaysDiscount;
    }

    private int calculateWeekendsDiscount(Order order, Map<String, Integer> discountDetails) {
        int weekendsDiscount = promotionService.calculateWeekendsDiscount(order.getReservedDate(), order.getOrderItems().size());
        discountDetails.put("weekends", weekendsDiscount);
        return weekendsDiscount;
    }

    private int calculateSpecialDiscount(Order order, Map<String, Integer> discountDetails) {
        int specialDiscount = promotionService.calculateSpecialDiscount(order.getReservedDate());
        discountDetails.put("special", specialDiscount);
        return specialDiscount;
    }

}
