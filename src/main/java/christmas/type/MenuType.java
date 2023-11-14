package christmas.type;

public enum MenuType {
    APPETIZER("애피타이저"), MAIN("메인"), DESERT("디저트"), BEVERAGE("음료");

    private final String description;

    MenuType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
