package shops;

import features.Feature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public final class ShoppingCart extends Feature implements Serializable {
    private static ShoppingCart instance;
    public final String name;
    ArrayList<Item> items = new ArrayList<>();

    private ShoppingCart(String name) {
        this.name = name;
    }

    /**
     * COMPLETE
     * This method adds the bought item to the ShoppingCart
     *
     * @param item  - bought item
     * @param count - number of count bought item
     */
    public void addItem(Item item, int count) {
        boolean flag = true;
        if (items.size() != 0)
            for (Item item2 :
                    items) {
                if (Objects.equals(item2.getName(), item.getName())) {
                    item2.setCount(item2.getCount() + count);
                    flag = false;
                }
            }
        if (flag) {
            Item itemToShoppingCart = (Item) item.copy(0);
            itemToShoppingCart.setCount(count);
            items.add(itemToShoppingCart);
        }
    }

    /**
     * COMPLETE
     * This method displays a list of bought items
     */
    public void showShoppingCart() {
        if (items.size() == 0)
            System.out.println(set("RED") + "Your Shopping Cart is empty" + set("RESET"));
        else
            for (Item item : items) {
                item.showItemInfo(0);
            }
    }

    /**
     * COMPLETE
     * This method implements a design pattern Singleton
     *
     * @param name - name of object
     * @return this object
     */
    public static ShoppingCart getInstance(String name) {
        if (instance == null) {
            instance = new ShoppingCart(name);
        }
        return instance;
    }
}
