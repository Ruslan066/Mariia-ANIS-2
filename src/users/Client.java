package users;
import shops.Item;
import shops.ShoppingCart;

public class Client extends User {
    private double money;
    private String discountCard;
    private int discountPercent;
    private final ShoppingCart shoppingCart = ShoppingCart.getInstance("MyCart");

    public Client(int id, String name, String phoneNumber, String login, String password) {
        super(id, name, phoneNumber, login, password, false);
        this.money = 100;
        this.discountCard = DiscountCard.NO_CARD.toString();
        this.discountPercent = DiscountCard.valueOf(discountCard).ordinal();
    }

    public void AddItemToShoppingCart(Item item, int count) {
        shoppingCart.addItem(item, count);
    }

    public void setMoney(double money) {
        this.money -= money;
        this.money = (double) Math.round(this.money * 100) / 100;

    }
    public void MakeDeposit(double deposit) {
        this.money += deposit;
    }

    public boolean SetDiscountCard(String card) {
        for (DiscountCard dc : DiscountCard.values()) {
            if (dc.name().equals(card)) {
                this.discountCard = card;
                this.discountPercent = DiscountCard.valueOf(discountCard).percent;
                return true;
            }
        }
        System.out.print(set("RED") +"Discount card not found!!!");
        return false;
    }

    //From Class User
    @Override
    public void ShowUserInfo() {
        System.out.println(
                set("YELLOW") + "=--" + set("PURPLE") +" Client "+ set("GREEN") + "info" + set("YELLOW") + " --=" +
                        set("CYAN") +"\nName: " + set("RESET") + super.getName() +
                        set("CYAN") +"\nPhone: " + set("RESET")+ super.getPhoneNumber() +
                        set("CYAN") +"\nLogin: " + set("RESET")+ super.getLogin() +
                        set("CYAN") +"\nPassword: " + set("RESET")+ super.getPassword() +
                        set("CYAN") +"\nMoney: " + set("RESET")+ this.money +
                        set("CYAN") +"\nDiscount Card: " + set("RESET")+ this.discountCard +
                        " - " + this.discountPercent + "%" +
                        set("CYAN") +"\nisEmployee: " + set("RESET")+ super.isEmployee());
    }

    //Getter
    public int getDiscountPercent() {
        return discountPercent;
    }
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public double getMoney() {
        return money;
    }

}
