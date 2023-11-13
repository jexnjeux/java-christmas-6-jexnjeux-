package christmas.domain;

public class MenuFactory {

    private MenuFactory() {

    }

    public static Menu create() {
        Menu menu = new Menu();

        MenuCategory appetizers = new MenuCategory(MenuType.APPETIZER);
        appetizers.addMenuItem(MenuItem.MUSHROOM_SOUP);
        appetizers.addMenuItem(MenuItem.TAPAS);
        appetizers.addMenuItem(MenuItem.CAESAR_SALAD);
        menu.addMenu(appetizers);

        MenuCategory mains = new MenuCategory(MenuType.APPETIZER);
        mains.addMenuItem(MenuItem.T_BONE_STEAK);
        mains.addMenuItem(MenuItem.BARBEQUE_RIB);
        mains.addMenuItem(MenuItem.SEAFOOD_PASTA);
        mains.addMenuItem(MenuItem.X_MAS_PASTA);
        menu.addMenu(mains);

        MenuCategory desert = new MenuCategory(MenuType.APPETIZER);
        desert.addMenuItem(MenuItem.CHOCOLATE_CAKE);
        desert.addMenuItem(MenuItem.ICE_CREAM);
        menu.addMenu(desert);

        MenuCategory beverage = new MenuCategory(MenuType.APPETIZER);
        beverage.addMenuItem(MenuItem.ZERO_COKE);
        beverage.addMenuItem(MenuItem.RED_WINE);
        beverage.addMenuItem(MenuItem.CHAMPAGNE);
        menu.addMenu(beverage);
        return menu;
    }
}
