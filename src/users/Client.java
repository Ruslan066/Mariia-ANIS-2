package users;

import shops.Item;
import shops.ShoppingCart;

import java.text.DecimalFormat;

public class Client extends User {
    private double money;
    private String discountCard;
    private int discountPercent;
    private final ShoppingCart shoppingCart = ShoppingCart.getInstance("MyCart");

    public Client(String name, String phoneNumber, String login, String password) {
        super(name, phoneNumber, login, password, false);
        this.money = 100;
        this.discountCard = DiscountCard.NO_CARD.toString();
        this.discountPercent = DiscountCard.valueOf(discountCard).ordinal();
    }

    public Client(){

    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    @Override
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void AddItemToShoppingCart(Item item, int count) {
        shoppingCart.addItem(item, count);
    }

    @Override
    public void setMoney(double money) {
        this.money -= money;
        this.money = (double) Math.round(this.money * 100) / 100;

    }

    @Override
    public double getMoney() {
        return money;
    }

    @Override
    public void ShowUserInfo() {
            System.out.println(
                    "=-- Client info --="+
                    "\nName: " + super.getName() +
                    "\nPhone: " + super.getPhoneNumber() +
                    "\nLogin: " + super.getLogin() +
                    "\nPassword: " + super.getPassword() +
                    "\nMoney: " + this.money+
                    "\nDiscount Card: " + this.discountCard+
                    " - " + this.discountPercent+ "%"+
                    "\nisEmployee: " + super.isEmployee());
    }

    @Override
    public void MakeDeposit(int deposit) {
        this.money += deposit;
    }

    @Override
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
}
