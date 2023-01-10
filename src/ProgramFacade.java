import features.Color;
import features.ReadWriteData;
import shops.Item;
import shops.Shop;
import shops.ShopFactory;
import users.Client;
import users.Employee;
import users.User;
import users.UserFeatures;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ProgramFacade extends ReadWriteData {

    private User logInUser;
    private ArrayList<User> users;
    private ArrayList<Shop> shops;
    private final UserFeatures userFeatures;

    public ProgramFacade(User logInUser) {
        this.logInUser = logInUser;
        this.users = ReadUserData();
        userFeatures = new UserFeatures(this.users);
    }

    //Complete
    public void HomePage() {
        shops = ReadShopData();
        System.out.println(Set("YELLOW") + "=--" + Set("PURPLE") + " Welcome " + Set("YELLOW") + "--=");
        System.out.println(Set("BLUE") + "1:" + Set("RESET") + " LogIn");
        System.out.println(Set("BLUE") + "2:" + Set("RESET") + " Create account");
        System.out.println(Set("BLUE") + "3:" + Set("RESET") + " View information about the store and the range of products");
        System.out.println(Set("BLUE") + "4:" + Set("RESET") + " Exit");
        int choice = MyScanner();
        switch (choice) {
            case 1:
                logInUser = userFeatures.LogIn();
                AccountPage();
                break;
            case 2:
                logInUser = userFeatures.CreateNewUser();
                AccountPage();
                break;
            case 3:
                ShowShopsPage();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println(Set("RED") +"Please enter a number 1 - 4!"+Set("RESET"));
                HomePage();
                break;
        }
    }

    private void AccountPage() {
        System.out.println("=-- Welcome " + logInUser.getName() + " --=");
        System.out.println("1: View info about my account");
        System.out.println("2: Choose a store");
        if (logInUser.isEmployee()) {
            System.out.println("3: Exit");
        } else {
            System.out.println("3: View the list of purchased products");
            System.out.println("4: Exit");
        }
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                UserInfoPage();
                break;
            case 2:
                ShowShopsPage();
                break;
            case 3:
                if (logInUser.isEmployee()) {
                    logInUser = null;
                    HomePage();
                }
                userFeatures.ShowMyProducts();
                GoBack("AccountPage");
                break;
            case 4:
                if (logInUser.isEmployee())
                    AccountPage();
                logInUser = null;
                HomePage();
                break;
            default:
                AccountPage();
                break;
        }
    }

    private void UserInfoPage() {
        logInUser.ShowUserInfo();
        if (logInUser.isEmployee())
            GoBack("AccountPage");

        System.out.println("1: Top up your account");
        System.out.println("2: Choose a discount card");
        System.out.println("3: Exit");

        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        switch (choice) {
            case 1:
                userFeatures.TopUpAccount();
                UserInfoPage();
                break;
            case 2:
                userFeatures.ChooseDiscountCard();
                UserInfoPage();
                break;
            case 3:
                AccountPage();
                break;
            default:
                UserInfoPage();
                break;
        }
    }

    private void ShowShopsPage() {
        System.out.println("=-- Shops --=");
        for (Shop shop : shops) {
            shop.ShowShopInfo();
            System.out.println("----------");
        }
        ChoiceShop();
    }

    private void ChoiceShop() {
        System.out.println("Type id of shop for more detail or type exit to go back: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        if (Objects.equals(choice, "exit")) {
            if (logInUser != null)
                AccountPage();
            else
                HomePage();
        }
        try {
            ShowShopPage(Integer.parseInt(choice));
        } catch (Exception ex) {
            System.out.println("You type the wrong value: id");
            ChoiceShop();
        }
    }

    private void ShowShopPage(int id) {
        int percent = 0;

        shops.get(id).ShowShopInfo();
        if (logInUser != null) {
            percent = userFeatures.retPercent();
        }
        for (Item item : shops.get(id).getItems()) {
            item.ShowItemInfo(percent);
        }
        if (logInUser != null) {
            System.out.println("Type NAME of item and COUNT for buy, example: Peony 1");
            System.out.println("Type exit to go back:");
            Scanner in = new Scanner(System.in);
            String choice = in.nextLine();
            if (Objects.equals(choice, "exit")) {
                AccountPage();
            }
            userFeatures.BuyItem(choice, id, shops);
            ShowShopPage(id);
        }
        GoBack("ShowShops");
    }

    private void GoBack(String forward) {
        System.out.println("Type exit to go back: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        if (Objects.equals(choice, "exit")) {
            if (Objects.equals(forward, "ShowShops"))
                ShowShopsPage();
            if (Objects.equals(forward, "AccountPage"))
                AccountPage();
        }
        GoBack(forward);
    }

    private String Set(String color) {
        return Color.valueOf(color).colorCode;
    }

    private int MyScanner() {
        Scanner in = new Scanner(System.in);
        int choice = 0;
        try {
            choice = in.nextInt();
        } catch (Exception ex) {
            System.out.println(Set("RED") +"Please enter a number!"+Set("RESET"));
            MyScanner();
        }
        return choice;
    }

    public void CreateBasicListUsers() {
        ArrayList<User> users = new ArrayList<>();
        Client Ruslan = new Client(0, "Ruslan", "421951305305", "LuxLux", "123456");
        Client Sam = new Client(1, "Sam", "421951305305", "1", "1");
        Client Maria = new Client(2, "Maria", "421951306306", "MariMari", "001122");
        Client Tom = new Client(3, "Tom", "421951123123", "KobiKo", "159753");
        Employee Daniela = new Employee(4, "Daniela", "421951741258", "2", "2");

        users.add(Ruslan);
        users.add(Sam);
        users.add(Maria);
        users.add(Tom);
        users.add(Daniela);
        WriteUserData(users);
        System.out.println("Create Users!!!");
    }

    public void CreateBasicListShops() {
        ArrayList<Shop> shops = new ArrayList<>();
        Item Peony = new Item(0, "Peony", 3);
        Item Rose = new Item(1, "Rose", 5.5);
        Item Fir = new Item(2, "Fir", 15);
        Item Cactus = new Item(3, "Cactus", 7.98);

        Shop HappyChappy = new Shop(0, "HappyChappy", "Kosice Jedlikova 9");
        HappyChappy.AddItem(Peony);
        HappyChappy.AddItem(Rose);
        HappyChappy.AddItem(Fir);
        HappyChappy.AddItem(Cactus);

// патерн "Prototype" клонирования магазина
        ShopFactory factory = new ShopFactory(HappyChappy);
        Shop FlowerCat = factory.CloneShop();
        Shop AsiaFlower = factory.CloneShop();
        FlowerCat.setNameAddress("FlowerCat", "Hlavna 11");
        AsiaFlower.setNameAddress("AsiaFlower", "Hlavna 3");

        shops.add(HappyChappy);
        shops.add(FlowerCat);
        shops.add(AsiaFlower);

        WriteShopData(shops);
        System.out.println("Create Shops!!!");
    }

}
