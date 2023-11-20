package christmas;

import christmas.controller.EventController;
import christmas.controller.InputView;
import christmas.controller.OutputView;

public class Application {

    public static void main(String[] args) {
        EventController eventController = new EventController(new InputView(), new OutputView());
        eventController.processOrder();

    }
}
