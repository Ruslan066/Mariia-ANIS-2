package users;

import shops.Item;
import shops.ShoppingCart;

public class Employee extends User{

    public Employee(int id, String name, String phoneNumber, String login, String password) {
        super(id, name, phoneNumber, login, password, true);
    }

    @Override
    public void ShowUserInfo() {
        System.out.println(
                "Name: " + super.getName() +
                        "\nPhone: " + super.getPhoneNumber() +
                        "\nLogin: " + super.getLogin() +
                        "\nPassword: " + super.getPassword() +
                        "\nisEmployee: " + super.isEmployee());
    }

    @Override
    public ShoppingCart getShoppingCart() {
        return null;
    }

    @Override
    public void AddItemToShoppingCart(Item item, int count) {

    }

    @Override
    public void MakeDeposit(int deposit) {
    }

    @Override
    public void SetDiscountCard(String card) {

    }

    @Override
    public int getDiscountPercent() {
        return 0;
    }

    @Override
    public void setMoney(double money) {

    }

    @Override
    public double getMoney() {
        return 0;
    }

}
