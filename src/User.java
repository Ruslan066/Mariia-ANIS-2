import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String phoneNumber;
    private String login;
    private String password;
    private int money;
    private String discountCard;
    private int discountPercent;
    public User(String name, String phoneNumber, String login, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
        this.money = 100;
        this.discountCard = DiscountCard.NO_CARD.toString();
        this.discountPercent = DiscountCard.valueOf(discountCard).ordinal();
    }
    public User() {

    }

    public void ShowUserInfo() {
        System.out.println(
                        "Name: " + this.name +
                        "\nPhone: " + this.phoneNumber +
                        "\nLogin: " + this.login +
                        "\nPassword: " + this.password +
                        "\nMoney: " + this.money+
                        "\nDiscount Card: " + this.discountCard+
                        "\nDiscount Percent: " + this.discountPercent);
    }

    public void MakeDeposit(int deposit) {
        this.money += deposit;
    }
    public void SetDiscountCard(String card) {
        for (DiscountCard dc : DiscountCard.values()) {
            if (dc.name().equals(card)) {
                this.discountCard = card;
                this.discountPercent = DiscountCard.valueOf(discountCard).percent;
            }
            else {
                //System.out.println("Cant find card!");
            }
        }
    }
    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getMoney() {
        return money;
    }

    public String getDiscountCard() {
        return discountCard;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public String getPassword() {
        return password;
    }

}
