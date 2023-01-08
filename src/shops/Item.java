package shops;

import java.io.Serializable;

public class Item implements Copyable, Serializable {
    private int id;
    private String name;
    private int count;
    private double cost;

    public Item(int id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.count = 10;
    }

    public void ShowItemInfo(int percent){
        double costPercent = this.cost - (percent/100.0) * this.cost;
        double newCost = (double) Math.round(costPercent * 100) / 100;
        System.out.println(
                        "Name: " + this.name +
                        "\nCost: " + newCost+
                        "\nCount: " + this.count+
                        "\n----------");
    }
    public void ChangeCount(int count){
        this.count -= count;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public Object copy(int id) {
        Item item = new Item(id, this.name, this.cost);
        return item;
    }
}
