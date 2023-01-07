import shops.Shop;
import users.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReadWriteData {

    public static void WriteUserData(ArrayList<User> users){
        try {
            FileOutputStream file = new FileOutputStream("usersData.txt");

            // Creates an ObjectOutputStream
            ObjectOutputStream output = new ObjectOutputStream(file);

            // Writes objects to the output stream
            output.writeObject(users);
            output.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void WriteShopData(ArrayList<Shop> shops){
        try {
            FileOutputStream file = new FileOutputStream("shopsData.txt");

            // Creates an ObjectOutputStream
            ObjectOutputStream output = new ObjectOutputStream(file);

            // Writes objects to the output stream
            output.writeObject(shops);
            output.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    public ArrayList<User> ReadUserData(){
        ArrayList<User> users = new ArrayList<>();
        try {
            FileInputStream fileStream = new FileInputStream("usersData.txt");

            // Creates an ObjectInputStream
            ObjectInputStream input = new ObjectInputStream(fileStream);

            // Reads the objects
            users = (ArrayList<User>) input.readObject();
            input.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }
        return users;
    }

    public ArrayList<Shop> ReadShopData(){
        ArrayList<Shop> shops = new ArrayList<>();
        try {
            FileInputStream fileStream = new FileInputStream("shopsData.txt");

            // Creates an ObjectInputStream
            ObjectInputStream input = new ObjectInputStream(fileStream);

            // Reads the objects
            shops = (ArrayList<Shop>) input.readObject();
            input.close();
        }

        catch (Exception e) {
            e.getStackTrace();
        }
        return shops;
    }
}
