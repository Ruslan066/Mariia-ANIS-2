package shops;

import java.util.ArrayList;

public class Shop implements Copyable{
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

    public void ShowShopInfo() {
        System.out.println(
                "Name: " + this.name + ", Address: " + this.address + "\n----------");
        for (Item item : items) {
            item.ShowItemInfo();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public Object copy() {
        Shop shop = new Shop(this.id, this.name, this.address, this.items);
        return shop;
    }
}
