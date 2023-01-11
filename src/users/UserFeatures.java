package users;

import features.ReadWriteData;
import shops.Item;
import shops.Shop;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UserFeatures extends ReadWriteData {
    private final ArrayList<User> users;
    private User logInUser = null;
    private Client logInClient = null;
    private Employee logInEmployee = null;

    public UserFeatures() {
        this.users = ReadUserData();
    }

    //Complete
    public User LogIn() {
        Scanner in = new Scanner(System.in);
        boolean log = false;
        boolean pass = false;

        System.out.println("=-- LogIn --=");
        System.out.println("Enter your login: ");
        String login = in.nextLine();
        System.out.println("Enter your password: ");
        String password = in.nextLine();
        for (User user : users) {
            log = Objects.equals(login, user.getLogin());
            pass = Objects.equals(password, user.getPassword());
            if (log && pass) {
                logInUser = user;
                break;
            }
        }
        if (log && pass) {
            System.out.println("Access good");
            if (logInUser.isEmployee()) {
                logInEmployee = (Employee) logInUser;
            } else {
                logInClient = (Client) logInUser;
            }
        } else {
            System.out.println("Incorrect login or password");
            LogIn();
        }
        return logInUser;
    }

    public User CreateNewUser() {

        int id = users.size();
        Scanner in = new Scanner(System.in);

        System.out.println("=-- Create Account --=" + id);
        System.out.println("Enter your name: ");
        String name = in.nextLine();
        System.out.println("Enter your phone number: ");
        String phoneNumber = in.nextLine();
        System.out.println("Enter your login: ");
        String login = in.nextLine();
        System.out.println("Enter your password: ");
        String password = in.nextLine();
        System.out.println("Are you employee: true or false");
        String isEmployee = in.nextLine();
        if (Objects.equals(isEmployee, "false")) {
            Client newUser = new Client(id, name, phoneNumber, login, password);
            users.add(newUser);
            logInUser = users.get(id);
        } else if (Objects.equals(isEmployee, "true")) {
            Employee newUser = new Employee(id, name, phoneNumber, login, password);
            users.add(newUser);
            logInUser = users.get(id);
        } else {
            System.out.println("You type the wrong value: true or false");
            CreateNewUser();
        }
        WriteUserData(users);
        return logInUser;
    }

    //Client
    public void ShowMyProducts() {
        System.out.println("=-- My Shopping Cart --=");
        logInClient.getShoppingCart().ShowShoppingCart();

    }

    //Client
    public void TopUpAccount() {
        System.out.println("=-- Top up account --=");
        System.out.println("Deposit amount:");
        Scanner in = new Scanner(System.in);
        int amount = in.nextInt();
        logInClient.MakeDeposit(amount);
        users.set(logInClient.getId(), logInClient);
        WriteUserData(users);
    }

    //Client
    public void ChooseDiscountCard() {
        System.out.println("=-- Choose a discount card --=");

        System.out.print("Discount card: ");
        for (DiscountCard card : DiscountCard.values()) {
            System.out.print(card + ", ");
        }
        System.out.println();
        Scanner in = new Scanner(System.in);
        String card = in.nextLine();

        logInClient.SetDiscountCard(card);

        users.set(logInClient.getId(), logInClient);
        WriteUserData(users);
    }

    //Client
    public int retPercent(){
        System.out.println("Your current money: " + logInClient.getMoney());
        return logInClient.getDiscountPercent();
    }
    //Client
    public void BuyItem(String choice, int id, ArrayList<Shop> shops) {

        String[] words = choice.trim().split("\\s+");
        try {
            for (Item item : shops.get(id).getItems()) {
                if (Objects.equals(words[0], item.getName())) {
                    if (words.length != 2) {
                        System.out.println("You type the wrong request!");
                        BuyItem(choice, id, shops);
                    }
                    if (item.getCount() < Integer.parseInt(words[1])) {
                        System.out.println("The amount of product in the store is less than you want to buy!");
                        BuyItem(choice, id, shops);
                    }
                    if (logInClient.getMoney() < item.getCost() * Integer.parseInt(words[1])) {
                        System.out.println("You don't have enough money to buy!");
                        BuyItem(choice, id, shops);
                    }
                    item.ChangeCount(Integer.parseInt(words[1]));

                    double costPercent = item.getCost() - (logInClient.getDiscountPercent() / 100.0) * item.getCost();
                    logInClient.setMoney(costPercent * Integer.parseInt(words[1]));
                    logInClient.AddItemToShoppingCart(item, Integer.parseInt(words[1]));
                    WriteShopData(shops);
                    users.set(logInClient.getId(), logInClient);
                    WriteUserData(users);
                }
            }

        } catch (Exception exception) {
            System.out.println(exception);
            System.out.println("You type the wrong value: NAME");
            BuyItem(choice, id, shops);
        }
    }
}
