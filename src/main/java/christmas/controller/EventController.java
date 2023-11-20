package christmas.controller;

import christmas.domain.Order;

public class EventController {

    private final InputView inputView;
    private final OutputView outputView;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void processOrder() {
        Order order = inputView.readOrder();
        outputView.printOrder(order);
        outputView.printTotalAmountBeforeDiscount(order);
        outputView.printFreeGift(order);
        outputView.printBenefit(order);
    }
}
