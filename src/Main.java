import shops.Item;
import shops.Shop;
import shops.ShopFactory;
import users.User;

import java.util.ArrayList;

public class Main extends Start{
    public static void main(String[] args) {

        // USER
        CreateBasicListUsers();
        // SHOP ITEM
        CreateBasicListShops();
        new Start();

    }

    public static void CreateBasicListUsers(){
        ArrayList<User> users = new ArrayList<>();
        users.Client Ruslan = new users.Client("Ruslan", "421951305305", "LuxLux", "123456");
        users.Client Maria = new users.Client("Maria", "421951306306", "MariMari", "001122");
        users.Client Tom = new users.Client("Tom", "421951123123", "KobiKo", "159753");
        users.Employee Daniela = new users.Employee("Daniela", "421951741258", "DELL", "020202");

        users.add(Ruslan);
        users.add(Maria);
        users.add(Tom);
        users.add(Daniela);
        WriteUserData(users);
        System.out.println("Create Users!!!");
//        ArrayList<users.User> users2 = ReadUsersData();
//        users2.get(0).ShowUserInfo();
//        System.out.println();
//        users2.get(3).ShowUserInfo();
    }
    public static void CreateBasicListShops(){
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