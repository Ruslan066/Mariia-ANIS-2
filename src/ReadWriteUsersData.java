import users.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ReadWriteUsersData {

    public static void WriteUsersData(ArrayList<User> users){
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
    public static ArrayList<User> ReadUsersData(){
        ArrayList<User> users = new ArrayList<User>();
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

}
