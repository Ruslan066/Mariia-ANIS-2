package users;

import features.Feature;
import shops.Item;
import shops.Shop;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UserFeatures extends Feature {
    private final ArrayList<User> users;
    private User logInUser = null;
    private Client logInClient = null;
    private Employee logInEmployee = null;

    public UserFeatures() {
        this.users = readUserData();
    }

    /**
     * COMPLETE
     * This method displays the account login page.
     *
     * @return - logInUser, User object
     */
    public User logIn() {
        Scanner in = new Scanner(System.in);
        boolean log = false;
        boolean pass = false;

        System.out.println(set("YELLOW") + "=--" + set("PURPLE") + " LogIn " + set("YELLOW") + "--=" + set("RESET"));
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
            if (logInUser.isEmployee()) {
                logInEmployee = (Employee) logInUser;
            } else {
                logInClient = (Client) logInUser;
            }
        } else {
            System.out.println(set("RED") + "Incorrect login or password" + set("RESET"));
            logIn();
        }
        return logInUser;
    }

    /**
     * COMPLETE
     * This method displays the account creation page.
     *
     * @return - logInUser, User object
     */
    public User createNewUser() {

        int id = users.size();
        Scanner in = new Scanner(System.in);

        System.out.println(set("YELLOW") + "=--" + set("PURPLE") + " Create Account " + set("YELLOW") + "--=" + set("RESET"));
        System.out.println("Enter your name: ");
        String name = in.nextLine();
        System.out.println("Enter your phone number: ");
        String phoneNumber = in.nextLine();
        System.out.println("Enter your login: ");
        String login = in.nextLine();
        System.out.println("Enter your password: ");
        String password = in.nextLine();
        System.out.println("Are you employee: " + set("BLUE") + "true" +
                set("RESET") + " or " + set("BLUE") + "false");
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
            System.out.println(set("RED") + "You type the wrong value: " + set("BLUE") + "true" +
                    set("RED") + " or " + set("BLUE") + "false" + set("RESET"));
            createNewUser();
        }
        writeUserData(users);
        return logInUser;
    }

    /**
     * COMPLETE
     * This method displays the page with the items that the user bought
     */
    public void showMyProducts() {
        System.out.println(set("YELLOW") + "=--" + set("PURPLE") + " My Shopping Cart " + set("YELLOW") + "--=" + set("RESET"));
        logInClient.getShoppingCart().showShoppingCart();
    }

    /**
     * COMPLETE
     * This method displays the top-up page
     */
    public void topUpAccount() {
        System.out.println(set("YELLOW") + "=--" + set("PURPLE") + " Top up account " + set("YELLOW") + "--=" + set("RESET"));
        System.out.println("Deposit amount:");
        int amount = myScanner();
        logInClient.makeDeposit(amount);
        users.set(logInClient.getId(), logInClient);
        writeUserData(users);
    }

    /**
     * COMPLETE
     * This method allows the user to select a discount card
     */
    public void chooseDiscountCard() {
        boolean flag;
        System.out.println(set("YELLOW") + "=--" + set("PURPLE") + " Choose a discount card " + set("YELLOW") + "--=" + set("RESET"));

        System.out.print(set("CYAN") + "Discount card: ");
        for (DiscountCard card : DiscountCard.values()) {
            System.out.print(set("BLUE") + card + set("RESET") + ", ");
        }
        do {
            System.out.println();
            Scanner in = new Scanner(System.in);
            String card = in.nextLine();

            flag = !logInClient.setDiscountCard(card);
        } while (flag);
        users.set(logInClient.getId(), logInClient);
        writeUserData(users);
    }

    /**
     * COMPLETE
     * This method returns what % the user has to buy the product
     *
     * @return - Discount Percent
     */
    public int retPercent() {
        return logInClient.getDiscountPercent();
    }

    /**
     * COMPLETE
     * This method returns how much money the user has in his account
     *
     * @return - Money
     */
    public double retMoney() {
        return logInClient.getMoney();
    }

    /**
     * COMPLETE
     * This method allows Client to buy products in stores
     *
     * @param choice - string name and count. Example (Peony 1)
     * @param shop   - store from the list of stores in which we will buy goods
     * @return - true: if successfully bought the product. false - If not bought
     */
    public boolean buyItem(String choice, Shop shop) {

        String[] words = choice.trim().split("\\s+");
        if (words.length != 2) {
            System.out.println(set("RED") + "You type the wrong request!" + set("RESET"));
            return false;
        }
        try {
            for (Item item : shop.getItems()) {
                if (Objects.equals(words[0], item.getName())) {
                    if (item.getCount() < Integer.parseInt(words[1])) {
                        System.out.println(set("RED") + "The amount of product in the store is less than you want to buy!" + set("RESET"));
                        return false;
                    }
                    if (logInClient.getMoney() < item.getCost() * Integer.parseInt(words[1])) {
                        System.out.println(set("RED") + "You don't have enough money to buy!" + set("RESET"));
                        return false;
                    }
                    item.decreaseCount(Integer.parseInt(words[1]));

                    double costPercent = item.getCost() - (logInClient.getDiscountPercent() / 100.0) * item.getCost();
                    logInClient.pay(costPercent * Integer.parseInt(words[1]));
                    logInClient.addItemToShoppingCart(item, Integer.parseInt(words[1]));

                    users.set(logInClient.getId(), logInClient);
                    writeUserData(users);
                    return true;
                }
            }

        } catch (Exception exception) {
            System.out.println(exception);
            return false;
        }
        return false;
    }

    /**
     * COMPLETE
     * This method allows Employee to add or udp products in stores
     * @param choice - string name, count, cost. Example (Peony 15 3.55)
     * @param shop - store from the list of stores in which we will add or udp goods
     * @return - true: if successfully add or udp the product. false - If have error
     */
    public boolean addUdpItem(String choice, Shop shop) {
        String[] words = choice.trim().split("\\s+");
        int count;
        double cost;
        if (words.length != 3) {
            System.out.println(set("RED") + "You type the wrong request!" + set("RESET"));
            return false;
        }
        try {
            count = Integer.parseInt(words[1]);
            cost  = Double.parseDouble(words[2]);
        } catch (Exception exception) {
            System.out.println(set("RED") + "You type the wrong request! TYPE NUMBER" + set("RESET"));
            return false;
        }
        for (Item item : shop.getItems()) {
            if (Objects.equals(words[0], item.getName())) {
                item.increaseCount(count);
                item.setCost(cost);
                return true;
            }
        }

        Item item = new Item(0, words[0], cost, count);
        shop.addItem(item);
        return true;
    }

    // TODO: COMPLETE (UserFeatures, ProgramFacade, Feature, Item) НАЗВАНИЯ МЕТОДОВ С МАЛЕНЬКОЙ
}
