import java.util.List;
import java.util.ArrayList;

public class Shop {
    private int id;
    private String name;
    private String address;
    ArrayList<Item> items = new ArrayList<Item>();

    public Shop(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

}
