package users;

import shops.Item;
import shops.ShoppingCart;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String name;
    private String phoneNumber;
    private String login;
    private String password;
    private boolean isEmployee;

    public User(String name, String phoneNumber, String login, String password, boolean isEmployee) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;
        this.isEmployee = isEmployee;
    }
    public User() {}

    public abstract void ShowUserInfo();
    public abstract void MakeDeposit(int deposit);
    public abstract void SetDiscountCard(String card);
    public  abstract double getMoney();
    public  abstract int getDiscountPercent();
    public  abstract ShoppingCart getShoppingCart();
    public  abstract void setMoney(double money);
    public abstract void AddItemToShoppingCart(Item item, int count);

    public String getLogin() {
        return login;
    }

    public String getName() { return name; }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEmployee() {
        return isEmployee;
    }
}
