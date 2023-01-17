package shops;

import features.Feature;

import java.io.Serializable;
import java.util.ArrayList;

public class Shop extends Feature implements Copyable, Serializable {
    private int id;
    private String name;
    private String address;
    ArrayList<Item> items = new ArrayList<>();

    public Shop(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }


    public Shop(int id, String name, String address, ArrayList<Item> itemss) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.items = itemss;
    }

    /**
     * COMPLETE
     * This method adds item to the Shop
     * @param item - object Item
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * COMPLETE
     * This method displays information about the Shop
     */
    public void showShopInfo() {
        System.out.println(
                set("CYAN") + "id: " + set("RESET") + this.id +
                        set("CYAN") + " Name: " + set("RESET") + this.name +
                        set("CYAN") + " Address: " + set("RESET") + this.address);
    }

    /**
     * COMPLETE
     * This method implements a design pattern Prototype
     * @param id - for new copy object
     * @return - a copy of this object
     */
    @Override
    public Object copy(int id) {
        ArrayList<Item> new_items = new ArrayList<>();
        for (Item item :
                items) {
            new_items.add((Item) item.copy(0));
        }
        return new Shop(id, this.name, this.address, new_items);
    }

    /**
     * Getters
     * give a value from private variables
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Setters
     * set a value to private variables
     */
    public void setNameAddress(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
