package shops;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public final class ShoppingCart implements Serializable {
    private static ShoppingCart instance;
    public String name;
    ArrayList<Item> items = new ArrayList<Item>();

    private ShoppingCart(String name){
        this.name = name;
    }
    public void addItem(Item item, int count) {
        boolean flag = true;
        if (items.size() != 0)
            for (Item item2 :
                    items) {
                if (Objects.equals(item2.getName(), item.getName())) {
                    item2.setCount(item2.getCount() + count);
                    flag= false;
                }
            }
        if (flag) {
            Item itemToShoppingCart = (Item) item.copy(0);
            itemToShoppingCart.setCount(count);
            items.add(itemToShoppingCart);
        }
    }

    public void ShowShoppingCart(){
        if(items.size() == 0)
            System.out.println("Your Shopping Cart is empty");
        else
            for (Item item : items) {
                item.ShowItemInfo(0);
            }
    }

    public static ShoppingCart getInstance(String name) {
        if (instance == null) {
            instance = new ShoppingCart(name);
        }
        return instance;
    }
}
