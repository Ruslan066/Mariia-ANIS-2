package shops;

import features.Feature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public final class ShoppingCart extends Feature implements Serializable {
    private static ShoppingCart instance;
    public final String name;
    ArrayList<Item> itemArrayList = new ArrayList<>();

    private ShoppingCart(String name) {
        this.name = name;
    }

    /**
     * COMPLETE
     * This method adds the bought item to the ShoppingCart
     *
     * @param flowers  - bought item
     * @param count - number of count bought item
     */
    public void addItem(Item flowers, int count) {
        boolean flag = true;
        if (this.itemArrayList.size() != 0)
            for (Item items :
                    this.itemArrayList) {
                if (Objects.equals(items.getName(), flowers.getName())) {
                    items.setCount(items.getCount() + count);
                    flag = false;
                }
            }
        if (flag) {
            Flowers flowersToShoppingCart = (Flowers) flowers.copy(0);
            flowersToShoppingCart.setCount(count);
            this.itemArrayList.add(flowersToShoppingCart);
        }
    }

    /**
     * COMPLETE
     * This method displays a list of bought items
     */
    public void showShoppingCart() {
        if (itemArrayList.size() == 0)
            System.out.println(set("RED") + "Your Shopping Cart is empty" + set("RESET"));
        else
            for (Item items : this.itemArrayList) {
                items.showItemInfo(0);
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
