package christmas.domain;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        while (!Validator.validateDate(input)) {
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            input = Console.readLine();
        }
        return Integer.parseInt(input);
    }

    public Order readOrder() {
        int reservedDate = readDate();
        System.out.println("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        while (!Validator.validateOrder(input)) {
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            input = Console.readLine();
        }

        return createOrders(input, reservedDate);
    }

    private Order createOrders(String input, int reservedDate) {
        Menu menu = MenuBoard.create();
        Order newOrder = new Order();
        String[] orderItems = input.split(",");
        for (String orderItem : orderItems) {
            String[] order = orderItem.split("-");
            String itemName = order[0].trim();
            int itemPrice = menu.findMenuPrice(itemName);
            int itemCount = Integer.parseInt(order[1].trim());
            MenuItem item = new MenuItem(itemName, itemPrice);
            newOrder.addOrderItem(item, itemCount);
        }
        newOrder.setReservedDate(reservedDate);
        return newOrder;
    }


}
