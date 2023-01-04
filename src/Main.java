import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.io.*;

public class Main extends Start{

    public static void main(String[] args) {

// USER
        ArrayList<User> users = new ArrayList<>();
        Client Ruslan = new Client("Ruslan", "421951305305", "LuxLux", "123456");
        Client Maria = new Client("Maria", "421951306306", "MariMari", "001122");
        Client Tom = new Client("Tom", "421951123123", "KobiKo", "159753");
        Employee Daniela = new Employee("Daniela", "421951741258", "DELL", "020202");

        users.add(Ruslan);
        users.add(Maria);
        users.add(Tom);
        users.add(Daniela);
        WriteUsersData(users);

        ArrayList<User> users2 = ReadUsersData();
        users2.get(0).ShowUserInfo();
        System.out.println();
        users2.get(3).ShowUserInfo();

      // new Start();


//SHOP ITEM
//        Item Peony = new Item(0, "Peony", 3);
//        Item Rose = new Item(1, "Rose", 5.5);
//        Item Fir = new Item(2, "Fir", 15);
//        Item Cactus = new Item(3, "Cactus", 7.98);
//
//        Shop HappyChappy = new Shop(0, "HappyChappy", "Kosice Jedlikova 9");
//        HappyChappy.AddItem(Peony);
//        HappyChappy.AddItem(Rose);
//        HappyChappy.AddItem(Fir);
//        HappyChappy.AddItem(Cactus);
//
//        HappyChappy.ShowShopInfo();
    }
}