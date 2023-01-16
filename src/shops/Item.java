package shops;

import features.Color;

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
                set("CYAN") +"Name: " +set("RESET")+ this.name +
                        set("CYAN") +"\nCost: " +set("RESET")+ newCost+
                        set("CYAN") +"\nCount: " +set("RESET")+ this.count+
                        set("YELLOW") +"\n----------"+set("RESET"));
    }
    public void ChangeCount(int count){
        this.count -= count;
    }
    /**
     * COMPLETE
     * This method gets the name of the color and returns his code.
     *
     * @param color - name color. Example (RED, BLUE, RESET)
     * @return code color. Example "\u001B[0m"
     */
    private String set(String color) {
        return Color.valueOf(color).colorCode;
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
