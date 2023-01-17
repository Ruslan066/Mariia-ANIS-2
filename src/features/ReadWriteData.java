package features;

import shops.Shop;
import users.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReadWriteData {

    /**
     * COMPLETE
     * This method writes users to the file
     *
     * @param users - list objects of User
     */
    public void writeUserData(ArrayList<User> users) {
        try {
            FileOutputStream file = new FileOutputStream("usersData.txt");

            // Creates an ObjectOutputStream
            ObjectOutputStream output = new ObjectOutputStream(file);

            // Writes objects to the output stream
            output.writeObject(users);
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * COMPLETE
     * This method writes shops to the file
     *
     * @param shops - list objects of Shop
     */
    public void writeShopData(ArrayList<Shop> shops) {
        try {
            FileOutputStream file = new FileOutputStream("shopsData.txt");

            // Creates an ObjectOutputStream
            ObjectOutputStream output = new ObjectOutputStream(file);

            // Writes objects to the output stream
            output.writeObject(shops);
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * COMPLETE
     * This method read users from the file
     *
     * @return users - list objects of User
     */
    public static ArrayList<User> readUserData() {
        ArrayList<User> users = new ArrayList<>();
        try {
            FileInputStream fileStream = new FileInputStream("usersData.txt");

            // Creates an ObjectInputStream
            ObjectInputStream input = new ObjectInputStream(fileStream);

            // Reads the objects
            users = (ArrayList<User>) input.readObject();
            input.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return users;
    }

    /**
     * COMPLETE
     * This method read shops from the file
     *
     * @return shops - list objects of Shop
     */
    public ArrayList<Shop> readShopData() {
        ArrayList<Shop> shops = new ArrayList<>();
        try {
            FileInputStream fileStream = new FileInputStream("shopsData.txt");

            // Creates an ObjectInputStream
            ObjectInputStream input = new ObjectInputStream(fileStream);

            // Reads the objects
            shops = (ArrayList<Shop>) input.readObject();
            input.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return shops;
    }
}
