package christmas.domain;

public enum MenuItem {
    // 애피타이저
    MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000),

    // 메인
    T_BONE_STEAK("티본스테이크", 55000),
    BARBEQUE_RIB("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    X_MAS_PASTA("크리스마스파스타", 25000),

    // 디저트
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000),

    // 음료
    ZERO_COKE("제로콜라", 3000),
    RED_WINE("레드와인", 6000),
    CHAMPAGNE("샴페인", 25000);

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
