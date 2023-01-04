import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Start extends ReadWriteUsersData{

    private User logInUser = new User();
    public Start() {
        HomePage();
    }
    public void HomePage(){
        System.out.println("=-- Welcome --=");
        System.out.println("1: LogIn");
        System.out.println("2: Create account");
        System.out.println("3: View information about the store and the range of products");
        System.out.println("4: Exit");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice){
            case 1: LogIn();
                break;
            case 2:
                CreateAccount();
                break;
            default:
                HomePage();
                break;
        }
    }
    public void LogIn(){
        ArrayList<User> users = ReadUsersData();
        //users.get(0).ShowUserInfo();
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
            if(log && pass) {
                this.logInUser = user;
                break;
            }
        }
        if(log && pass){
            System.out.println("Access good");
            System.out.println("Welcome: " + this.logInUser.getName());
        }
        else {
            LogIn();
        }
    }
    public void CreateAccount(){
        ArrayList<User> users = ReadUsersData();
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
        User newUser = new User(name,phoneNumber,login,password);
        users.add(newUser);
        WriteUsersData(users);

        System.out.println("Good");
    }
}
