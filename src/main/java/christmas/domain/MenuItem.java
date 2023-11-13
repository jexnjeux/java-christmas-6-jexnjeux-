package christmas.domain;

public class MenuItem {

    private final String name;
    private final int amount;

    MenuItem(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }


}
