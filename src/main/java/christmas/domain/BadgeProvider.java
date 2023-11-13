package christmas.domain;

public class BadgeProvider {

    private static final int SANTA_THRESHOLD = 20000;
    private static final int TREE_THRESHOLD = 10000;
    private static final int STAR_THRESHOLD = 5000;

    public String grantBadge(int totalDiscount) {
        if (totalDiscount >= SANTA_THRESHOLD) {
            return "산타";
        } else if (totalDiscount >= TREE_THRESHOLD) {
            return "트리";
        } else if (totalDiscount >= STAR_THRESHOLD) {
            return "별";
        }
        return "없음";
    }
}
