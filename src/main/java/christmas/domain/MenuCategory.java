package christmas.domain;

import christmas.type.MenuType;
import java.util.ArrayList;
import java.util.List;

public class MenuCategory {

    private MenuType type;
    private List<MenuItem> items;

    public MenuCategory(MenuType type) {
        this.type = type;
        this.items = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        this.items.add(item);
    }

    public boolean containsMenuItem(String itemName) {
        for (MenuItem item : items) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public MenuType findMenuType(String itemName) {
        for (MenuItem item : items) {
            if (item.getName().equals(itemName)) {
                return type;
            }
        }
        return null;
    }

    public int findMenuPrice(String itemName) {
        for (MenuItem item : items) {
            if (item.getName().equals(itemName)) {
                return item.getPrice();
            }
        }
        return 0;
    }

    public MenuType getType() {
        return type;
    }

    public List<MenuItem> getItems() {
        return items;
    }

}
