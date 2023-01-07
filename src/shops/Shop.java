package shops;

import java.io.Serializable;
import java.util.ArrayList;

public class Shop implements Copyable, Serializable {
    private int id;
    private String name;
    private String address;
    ArrayList<Item> items = new ArrayList<Item>();

    public Shop(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public Shop(int id, String name, String address, ArrayList<Item> items) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.items = items;
    }

    public void AddItem(Item item) {
        items.add(item);
    }

    public void ShowShopInfoWithItems() {
        System.out.println(
                "Name: " + this.name + ", Address: " + this.address + "\n----------");
        for (Item item : items) {
            item.ShowItemInfo();
        }
    }
    public void ShowShopInfo() {

        System.out.println(
                "id: " + this.id +
                ", Name: " + this.name +
                ", Address: " + this.address);
    }

    public void setNameAddress(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public Object copy(int id) {
        Shop shop = new Shop(id, this.name, this.address, this.items);
        return shop;
    }
}
