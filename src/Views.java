import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

public interface Views {
    static void Start(){
        HomePage();
    }

    static void HomePage(){
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
            default:
                HomePage();
                break;
        }
    }
    static void LogIn(){
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner in = new Scanner(System.in);
        System.out.println("=-- LogIn --=");
        System.out.println("Enter your login: ");
        String login = in.nextLine();
        System.out.println("Enter your password: ");
        String password = in.nextLine();
    }
}
