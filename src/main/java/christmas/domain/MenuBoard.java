package christmas.domain;

public class MenuBoard {

    private MenuBoard() {

    }

    public static Menu create() {
        Menu menu = new Menu();

        MenuCategory appetizers = new MenuCategory(MenuType.APPETIZER);
        appetizers.addMenuItem(new MenuItem("양송이수프", 6000));
        appetizers.addMenuItem(new MenuItem("타파스", 5500));
        appetizers.addMenuItem(new MenuItem("시저샐러드", 8000));

        menu.addMenu(appetizers);

        MenuCategory mains = new MenuCategory(MenuType.MAIN);
        mains.addMenuItem(new MenuItem("티본스테이크", 55000));
        mains.addMenuItem(new MenuItem("바비큐립", 54000));
        mains.addMenuItem(new MenuItem("해산물파스타", 35000));
        mains.addMenuItem(new MenuItem("크리스마스파스타", 25000));
        menu.addMenu(mains);

        MenuCategory desert = new MenuCategory(MenuType.DESERT);
        desert.addMenuItem(new MenuItem("초코케이크", 15000));
        desert.addMenuItem(new MenuItem("아이스크림", 5000));
        menu.addMenu(desert);

        MenuCategory beverage = new MenuCategory(MenuType.BEVERAGE);
        beverage.addMenuItem(new MenuItem("제로콜라", 3000));
        beverage.addMenuItem(new MenuItem("레드와인", 6000));
        beverage.addMenuItem(new MenuItem("샴페인", 25000));
        menu.addMenu(beverage);
        return menu;
    }

}
