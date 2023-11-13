package christmas.domain;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashMap;
import java.util.Map;

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

    public Menu readMenu() {
        System.out.println("주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        while (!Validator.validateOrder(input)) {
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            input = Console.readLine();
        }

        return createOrders(input);
    }

    private Menu createOrders(String input) {
        Menu menuBoard = MenuBoard.create();
        Menu menu = new Menu();
        String[] orderItems = input.split(",");
        Map<MenuType, MenuCategory> categoryMap = new HashMap<>();
        for (String orderItem : orderItems) {
            String[] order = orderItem.split("-");
            MenuItem item = new MenuItem(order[0], Integer.parseInt(order[1]));
            MenuType menuType = menuBoard.findMenuType(item.getName());
            MenuCategory category = categoryMap.computeIfAbsent(menuType, MenuCategory::new);
            category.addMenuItem(item);
        }
        addCategory(menu, categoryMap);
        return menu;
    }

    private void addCategory(Menu menu, Map<MenuType, MenuCategory> categoryMap) {
        for (MenuCategory category : categoryMap.values()) {
            menu.addMenu(category);
        }

    }
}
