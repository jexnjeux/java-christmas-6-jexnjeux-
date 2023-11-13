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
}
