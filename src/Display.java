import java.util.Scanner;

public abstract class Display {
    public static void HomePage() {
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
            default:
                HomePage();
                break;
        }
    }
    public static void LogIn(){
        Scanner in = new Scanner(System.in);
        System.out.println("=-- LogIn --=");
        System.out.println("Enter your login: ");
        String login = in.nextLine();
        System.out.println("Enter your password: ");
        String password = in.nextLine();


    }
}
