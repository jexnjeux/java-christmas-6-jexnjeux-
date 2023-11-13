package christmas.domain;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private List<MenuCategory> categories;

    public Menu() {
        this.categories = new ArrayList<>();
    }

    public void addMenu(MenuCategory category) {
        this.categories.add(category);
    }

    public boolean containsItem(String itemName) {
        for (MenuCategory category : categories) {
            if (category.containsMenuItem(itemName)) {
                return true;
            }
        }
        return false;
    }

    public MenuType findMenuType(String itemName) {
        for (MenuCategory category : categories) {
            if (category.findMenuType(itemName) != null) {
                return category.getType();
            }
        }
        return null;
    }

}
