package christmas.domain;

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

    public MenuType getType() {
        return type;
    }

    public List<MenuItem> getItems() {
        return items;
    }

}
