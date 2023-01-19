package shops;

import features.Feature;

import java.io.Serializable;

public class Item extends Feature implements Copyable, Serializable {
    private final int id;
    private final String name;
    private int count;
    private double cost;

    public Item(int id, String name, double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.count = 10;
    }
    public Item(int id, String name, double cost, int count) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.count = count;
    }

    /**
     * COMPLETE
     * This method displays information about the product
     * @param percent - discount percentage, by how much % less expensive to display products
     */
    public void showItemInfo(int percent){
        double costPercent = this.cost - (percent/100.0) * this.cost;
        double newCost = (double) Math.round(costPercent * 100) / 100;
        System.out.println(
                set("CYAN") +"Name: " +set("RESET")+ this.name +
                        set("CYAN") +"\nCost: " +set("RESET")+ newCost+
                        set("CYAN") +"\nCount: " +set("RESET")+ this.count+
                        set("YELLOW") +"\n----------"+set("RESET"));
    }

    /**
     * COMPLETE
     * This method decreases the amount of product
     * @param count - number by how much to decrease the number of items
     */
    public void decreaseCount(int count){
        this.count -= count;
    }

    /**
     * COMPLETE
     * This method Increase the amount of product
     * @param count - number by how much to Increase the number of items
     */
    public void increaseCount(int count){
        this.count += count;
    }

    /**
     * Getters
     * give a value from private variables
     */
    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getCount() {
        return count;
    }

    public int getId() {
        return id;
    }

    /**
     * Setters
     * set a value to private variables
     */
    public void setCount(int count) {
        this.count = count;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * COMPLETE
     * This method implements a design pattern Prototype
     * @param id - for new copy object
     * @return - a copy of this object
     */
    @Override
    public Object copy(int id) {
        Item item = new Item(id, this.name, this.cost);
        return item;
    }
}
