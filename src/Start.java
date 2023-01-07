import shops.Shop;
import users.DiscountCard;
import users.Client;
import users.Employee;
import users.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Start extends ReadWriteData {

    private User logInUser;
    private ArrayList<User> users;
    private ArrayList<Shop> shops;
    private int idUser;

    public Start() {
        HomePage();
    }

    private void HomePage() {
        shops = ReadShopData();
        System.out.println("=-- Welcome --=");
        System.out.println("1: LogIn");
        System.out.println("2: Create account");
        System.out.println("3: View information about the store and the range of products");
        System.out.println("4: Exit");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                LogIn();
                break;
            case 2:
                CreateAccount();
                break;
            case 3:
                ShowShops();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                HomePage();
                break;
        }
    }

    private void LogIn() {
        users = ReadUserData();

        //users.get(0).ShowUserInfo();
        Scanner in = new Scanner(System.in);
        boolean log = false;
        boolean pass = false;

        System.out.println("=-- LogIn --=");
        System.out.println("Enter your login: ");
        String login = in.nextLine();
        System.out.println("Enter your password: ");
        String password = in.nextLine();
        int i = 0;
        for (User user : users) {
            log = Objects.equals(login, user.getLogin());
            pass = Objects.equals(password, user.getPassword());
            if (log && pass) {
                this.logInUser = user;
                idUser = i;
                break;
            }
            i++;
        }
        if (log && pass) {
            System.out.println("Access good");
            Account();
        } else {
            System.out.println("Incorrect login or password");
            LogIn();
        }

    }

    private void CreateAccount() {
        users = ReadUserData();
        Scanner in = new Scanner(System.in);

        System.out.println("=-- Create Account --=");
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
            Client newUser = new Client(name, phoneNumber, login, password);
            users.add(newUser);
            idUser = users.size() - 1;
            logInUser = users.get(idUser);
        } else if (Objects.equals(isEmployee, "true")) {
            Employee newUser = new Employee(name, phoneNumber, login, password);
            users.add(newUser);
            idUser = users.size() - 1;
            logInUser = users.get(idUser);
        } else {
            System.out.println("You type the wrong value: true or false");
            CreateAccount();
        }
        WriteUserData(users);
        Account();
    }

    private void Account() {
        System.out.println("=-- Welcome " + logInUser.getName() + " --=");
        System.out.println("1: View info about my account");
        System.out.println("2: View the list of purchased products");
        System.out.println("3: Choose a store");
        System.out.println("4: Exit");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                UserInfo();
                break;
            case 2:
                CreateAccount();
                break;
            case 3:
                ShowShops();
                break;
            case 4:
                logInUser = null;
                HomePage();
                break;
            default:
                Account();
                break;
        }
    }

    private void UserInfo() {
        logInUser.ShowUserInfo();
        System.out.println("1: Top up your account");
        System.out.println("2: Choose a discount card");
        System.out.println("3: Exit");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                System.out.println("=-- Top up account --=");
                System.out.println("Deposit amount:");
                int amount = in.nextInt();
                logInUser.MakeDeposit(amount);
                users.set(idUser, logInUser);
                WriteUserData(users);
                UserInfo();
                break;
            case 2:
                System.out.println("=-- Choose a discount card --=");

                System.out.print("Discount card: ");
                for (DiscountCard card : DiscountCard.values()) {
                    System.out.print(card + ", ");
                }
                System.out.println();
                Scanner in2 = new Scanner(System.in);
                String card = in2.nextLine();

                logInUser.SetDiscountCard(card);

                users.set(idUser, logInUser);
                WriteUserData(users);
                UserInfo();
                break;
            case 3:
                Account();
                break;
            default:
                UserInfo();
                break;
        }
    }

    private void ShowShops() {
        System.out.println("=-- Shops --=");
        for (Shop shop : shops) {
            shop.ShowShopInfo();
            System.out.println("----------");
        }
        if (logInUser != null) {
            ChoiceShop();
        }
        GoBack();

        if (logInUser.isEmployee()) {
            System.out.println("You are users.Employee");

        } else {
            System.out.println("You are users.Client");
        }

        System.out.println("Good");
    }
    private void ChoiceShop(){
        System.out.println("Type id of shop for more detail or type exit to go back: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        if(Objects.equals(choice, "exit")){
            Account();
        }
        try {
            shops.get(Integer.parseInt(choice)).ShowShopInfoWithItems();
        } catch (Exception ex) {
            System.out.println("You type the wrong value: id");
            ChoiceShop();
        }
    }
    private void GoBack(){
        System.out.println("Type exit to go back: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        if(Objects.equals(choice, "exit")){
            if (logInUser != null) {
                Account();
            } else {
                HomePage();
            }
        }
        GoBack();
    }


}
