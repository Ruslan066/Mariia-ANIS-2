import features.Feature;
import shops.Flowers;
import shops.Item;
import shops.Shop;
import users.User;
import users.UserFeatures;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ProgramFacade extends Feature {

    private User logInUser;
    private ArrayList<Shop> shops;
    private final UserFeatures userFeatures = new UserFeatures();

    public ProgramFacade() {
    }

    /**
     * COMPLETE
     * This method display the start page of the application.
     */
    public void displayHomePage() {
        shops = readShopData();
        System.out.println(set("YELLOW") + "=--" + set("PURPLE") + " Welcome " + set("YELLOW") + "--=");
        System.out.println(set("BLUE") + "1" + set("RESET") + ": LogIn");
        System.out.println(set("BLUE") + "2" + set("RESET") + ": Create account");
        System.out.println(set("BLUE") + "3" + set("RESET") + ": View information about the store and the range of products");
        System.out.println(set("BLUE") + "4" + set("RESET") + ": Exit");
        int choice = myScanner();
        switch (choice) {
            case 1 -> {
                logInUser = userFeatures.logIn();
                displayAccountPage();
            }
            case 2 -> {
                logInUser = userFeatures.createNewUser();
                displayAccountPage();
            }
            case 3 -> displayShopsPage();
            case 4 -> System.exit(0);
            default -> {
                System.out.println(set("RED") + "Please enter a number " +
                        set("BLUE") + "1 - 4" +
                        set("RED") + "!" + set("RESET"));
                displayHomePage();
            }
        }
    }

    /**
     * COMPLETE
     * This method display the main user's page.
     * After he/she has logged in, or created an account.
     */
    private void displayAccountPage() {
        System.out.println(set("YELLOW") + "=--" + set("PURPLE") + " Welcome " + set("GREEN") + logInUser.getName() + set("YELLOW") + " --=");
        System.out.println(set("BLUE") + "1" + set("RESET") + ": View info about my account");
        System.out.println(set("BLUE") + "2" + set("RESET") + ": Choose a store");
        if (logInUser.isEmployee()) {
            System.out.println(set("BLUE") + "3" + set("RESET") + ": Exit");
        } else {
            System.out.println(set("BLUE") + "3" + set("RESET") + ": View the list of purchased products");
            System.out.println(set("BLUE") + "4" + set("RESET") + ": Exit");
        }

        int choice;
        boolean flag;
        do {
            choice = myScanner();
            flag = false;
            if (logInUser.isEmployee() && (choice > 3 || choice < 1)) {
                System.out.println(set("RED") + "Please enter a number " +
                        set("BLUE") + "1 - 3" +
                        set("RED") + "!" + set("RESET"));
                flag = true;
            }
            if (!logInUser.isEmployee() && (choice > 4 || choice < 1)) {
                System.out.println(set("RED") + "Please enter a number " +
                        set("BLUE") + "1 - 4" +
                        set("RED") + "!" + set("RESET"));
                flag = true;
            }
        } while (flag);

        switch (choice) {
            case 1 -> displayUserInfoPage();
            case 2 -> displayShopsPage();
            case 3 -> {
                if (logInUser.isEmployee()) {
                    logInUser = null;
                    displayHomePage();
                }
                userFeatures.showMyProducts();
                goBack("AccountPage");
            }
            case 4 -> {
                logInUser = null;
                displayHomePage();
            }
        }
    }

    /**
     * COMPLETE
     * This method displays a page with information about the user.
     */
    private void displayUserInfoPage() {
        logInUser.showUserInfo();

        if (logInUser.isEmployee())
            goBack("AccountPage");

        System.out.println(set("BLUE") + "1" + set("RESET") + ": Top up your account");
        System.out.println(set("BLUE") + "2" + set("RESET") + ": Choose a discount card");
        System.out.println(set("BLUE") + "3" + set("RESET") + ": Exit");

        int choice;
        boolean flag;
        do {
            choice = myScanner();
            flag = false;
            if (choice > 3 || choice < 1) {
                System.out.println(set("RED") + "Please enter a number " +
                        set("BLUE") + "1 - 3" +
                        set("RED") + "!" + set("RESET"));
                flag = true;
            }
        } while (flag);


        switch (choice) {
            case 1 -> {
                userFeatures.topUpAccount();
                displayUserInfoPage();
            }
            case 2 -> {
                userFeatures.chooseDiscountCard();
                displayUserInfoPage();
            }
            case 3 -> displayAccountPage();
        }
    }

    /**
     * COMPLETE
     * This method displays a page with a list of all shops
     * and information about them.
     */
    private void displayShopsPage() {
        System.out.println(set("YELLOW") + "=--" + set("PURPLE") + " Shops " + set("YELLOW") + "--=" + set("RESET"));
        for (Shop shop : shops) {
            shop.showShopInfo();
            System.out.println(set("YELLOW") + "----------" + set("RESET"));
        }
        System.out.println("Type " + set("BLUE") + "id" + set("RESET") + " of shop for more detail or type " + set("BLUE") + "0" + set("RESET") + " to go back: ");
        int choice;
        boolean flag;
        do {
            choice = myScanner();
            if (choice == 0)
                if (logInUser == null)
                    displayHomePage();
                else
                    displayAccountPage();
            flag = false;
            if (choice > shops.size() || choice < 0) {
                System.out.println(set("RED") + "Please enter a number " +
                        set("BLUE") + "0 - " + shops.size() +
                        set("RED") + "!" + set("RESET"));
                flag = true;
            }
        } while (flag);
        displayShopPage(choice - 1);
    }

    /**
     * COMPLETE
     * This method displays the page with the selected store.
     * If the user is logged into the account he has the possibility to buy the product
     *
     * @param id - store number in the array (shops)
     */
    private void displayShopPage(int id) {
        int percent;
        if(logInUser == null)
            percent = 0;
        else if(logInUser.isEmployee())
            percent = 0;
        else
            percent = userFeatures.retPercent();

        boolean flag = true;
        System.out.println(set("YELLOW") + "=--" + set("PURPLE") + " Shop " + set("GREEN") +
                shops.get(id).getName() + set("YELLOW") + " --=");
        System.out.println(set("CYAN") + "id: " + set("RESET") + shops.get(id).getId() +
                set("CYAN") + " Address: " + set("RESET") + shops.get(id).getAddress() +
                set("YELLOW") + "\n----------");


        for (Item items : shops.get(id).getItems()) {
            items.showItemInfo(percent);
        }

        if (logInUser != null) {
            if(!logInUser.isEmployee())
                System.out.println(set("CYAN") + "Your current money: " + set("RESET") + userFeatures.retMoney() +
                    set("YELLOW") + "\n----------" + set("RESET"));

            //TODO сделать для employee свою реализацию

            do {
                if(!logInUser.isEmployee())
                    System.out.println("Type " + set("BLUE") + "name " + set("RESET") +
                        "of item and " + set("BLUE") + "count " + set("RESET") + "for buy, example:" +
                        set("BLUE") + " Peony 1" + set("RESET"));
                else
                    System.out.println("Type " + set("BLUE") + "name " + set("RESET") +
                            "of item " + set("BLUE") + "count " + set("RESET") + "items " +
                            set("BLUE") + "cost "+set("RESET")+"for add or update, example:" +
                            set("BLUE") + " Peony 10 4.3" + set("RESET"));
                System.out.println("Type " + set("BLUE") + "exit" + set("RESET") + " to go back:");

                Scanner in = new Scanner(System.in);
                String choice = in.nextLine();

                if (Objects.equals(choice, "exit")) {
                    displayAccountPage();
                }
                if(!logInUser.isEmployee())
                    flag = !userFeatures.buyItem(choice, shops.get(id));
                else
                    flag = !userFeatures.addUdpItem(choice, shops.get(id));
            } while (flag);
            writeShopData(shops);

            displayShopPage(id);
        }
        goBack("ShowShops");
    }

    /**
     * COMPLETE
     * This method is used to go backward between pages
     *
     * @param forward - the name of the page to be opened
     */
    private void goBack(String forward) {
        System.out.println("Type " + set("BLUE") + "exit " + set("RESET") + "to go back: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        if (Objects.equals(choice, "exit")) {
            if (Objects.equals(forward, "ShowShops"))
                displayShopsPage();
            if (Objects.equals(forward, "AccountPage"))
                displayAccountPage();
        }
        goBack(forward);
    }

}
