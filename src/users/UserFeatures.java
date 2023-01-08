package users;

import features.ReadWriteData;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UserFeatures extends ReadWriteData {
    private final ArrayList<User> users;
    User logInUser = null;
    public UserFeatures(ArrayList<User> users) {
        this.users = users;
    }
    public User LogIn() {
        users.get(0).ShowUserInfo();
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
        } else {
            System.out.println("Incorrect login or password");
            LogIn();
        }
        return logInUser;
    }
    public User CreateNewUser(){

        int id = users.size();
        Scanner in = new Scanner(System.in);

        System.out.println("=-- Create Account --="+id);
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
    public void ShowMyProducts() {
        System.out.println("=-- My Shopping Cart --=");
        logInUser.getShoppingCart().ShowShoppingCart();

    }
}
