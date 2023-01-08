import features.ReadWriteData;
import shops.Item;
import shops.Shop;
import shops.ShopFactory;
import users.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ProgramFacade extends ReadWriteData {

    private User logInUser;
    private ArrayList<User> users;
    private ArrayList<Shop> shops;
    private int idUser;
    private final UserFeatures userFeatures;

    public ProgramFacade(User logInUser) {
        this.logInUser = logInUser;
        this.users = ReadUserData();
        userFeatures = new UserFeatures(this.users);
    }

    public void HomePage() {
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
                logInUser = userFeatures.LogIn();
                Account();
                break;
            case 2:
                logInUser = userFeatures.CreateNewUser();
                Account();
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
                userFeatures.ShowMyProducts();
                GoBack("Account");
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
        ChoiceShop();


        if (logInUser.isEmployee()) {
            System.out.println("You are users.Employee");

        } else {
            System.out.println("You are users.Client");
        }

        System.out.println("Good");
    }

    private void ChoiceShop() {
        System.out.println("Type id of shop for more detail or type exit to go back: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        if (Objects.equals(choice, "exit")) {
            if (logInUser != null)
                Account();
            else
                HomePage();
        }
        try {
            ShowShop(Integer.parseInt(choice));
        } catch (Exception ex) {
            System.out.println("You type the wrong value: id");
            ChoiceShop();
        }
    }

    private void ShowShop(int id) {
        int percent = 0;

        shops.get(id).ShowShopInfo();
        if (logInUser != null) {
            System.out.println("Your current money: " + logInUser.getMoney());
            percent = logInUser.getDiscountPercent();
        }
        for (Item item : shops.get(id).getItems()) {
            item.ShowItemInfo(percent);
        }
        if (logInUser != null)
            BuyItem(id);
        GoBack("ShowShops");
    }

    private void BuyItem(int id) {
        System.out.println("Type NAME of item and COUNT for buy, example: Peony 1");
        System.out.println("Type exit to go back:");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        if (Objects.equals(choice, "exit")) {
            Account();
        }
        String[] words = choice.trim().split("\\s+");

        try {
            for (Item item : shops.get(id).getItems()) {
                if (Objects.equals(words[0], item.getName())) {
                    if (words.length != 2) {
                        System.out.println("You type the wrong request!");
                        BuyItem(id);
                    }
                    if (item.getCount() < Integer.parseInt(words[1])) {
                        System.out.println("The amount of product in the store is less than you want to buy!");
                        BuyItem(id);
                    }
                    if (logInUser.getMoney() < item.getCost() * Integer.parseInt(words[1])) {
                        System.out.println("You don't have enough money to buy!");
                        BuyItem(id);
                    }
                    item.ChangeCount(Integer.parseInt(words[1]));

                    double costPercent = item.getCost() - (logInUser.getDiscountPercent() / 100.0) * item.getCost();
                    logInUser.setMoney(costPercent * Integer.parseInt(words[1]));
                    logInUser.AddItemToShoppingCart(item, Integer.parseInt(words[1]));
                    WriteShopData(shops);
                    users.set(idUser, logInUser);
                    WriteUserData(users);
                }
            }
            ShowShop(id);
        } catch (Exception exception) {
            System.out.println(exception);
            System.out.println("You type the wrong value: NAME");
            BuyItem(id);
        }
    }

    private void GoBack(String forward) {
        System.out.println("Type exit to go back: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();
        if (Objects.equals(choice, "exit")) {
            if (Objects.equals(forward, "ShowShops"))
                ShowShops();
            if (Objects.equals(forward, "Account"))
                Account();
        }
        GoBack(forward);
    }

    public void CreateBasicListUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.Client Ruslan = new users.Client(0, "Ruslan", "421951305305", "LuxLux", "123456");
        users.Client Sam = new users.Client(1, "Sam", "421951305305", "1", "1");
        users.Client Maria = new users.Client(2, "Maria", "421951306306", "MariMari", "001122");
        users.Client Tom = new users.Client(3, "Tom", "421951123123", "KobiKo", "159753");
        users.Employee Daniela = new users.Employee(4, "Daniela", "421951741258", "DELL", "020202");

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
