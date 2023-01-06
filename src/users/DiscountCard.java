package users;

public enum DiscountCard {
    NO_CARD(0),
    FIRST_LVL (10),
    SECOND_LVL (15),
    THIRD_LVL (20);

    int percent;
    DiscountCard(int percent) {
        this.percent = percent;
    }

}
