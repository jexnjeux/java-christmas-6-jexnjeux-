package christmas.domain;

public class MenuItem {

    private final String name;
    private final int price;

    MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
