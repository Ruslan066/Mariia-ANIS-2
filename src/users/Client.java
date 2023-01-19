package users;
import shops.Flowers;
import shops.Item;
import shops.ShoppingCart;

import java.util.Objects;

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

    /**
     * COMPLETE
     * This method add bought product to Shopping Cart
     * @param items - bought product
     * @param count - amount of item
     */
    public void addItemToShoppingCart(Item items, int count) {
        shoppingCart.addItem(items, count);
    }

    /**
     * COMPLETE
     * This method removes money from the account
     * @param money - amount of money
     */
    public void pay(double money) {
        this.money -= money;
        this.money = (double) Math.round(this.money * 100) / 100;

    }

    /**
     * COMPLETE
     * This method add money to the account
     * @param deposit - amount of money
     */
    public void makeDeposit(double deposit) {
        this.money += deposit;
    }

    /**
     * COMPLETE
     * This method sets the discount card and % discount
     * @param card - name card
     * @return true if name card exist, false if not exist
     */
    public boolean setDiscountCard(String card) {
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

    /**
     * COMPLETE
     * This method implement from User class and displays information about the Client
     */
    @Override
    public void showUserInfo() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Double.compare(client.money, money) == 0 && discountPercent == client.discountPercent && discountCard.equals(client.discountCard) && shoppingCart.equals(client.shoppingCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, discountCard, discountPercent, shoppingCart);
    }

    /**
     * Getters
     * give a value from private variables
     */
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
