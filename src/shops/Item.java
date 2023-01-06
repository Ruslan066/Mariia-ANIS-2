package shops;

public class Item {
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

    public void ShowItemInfo(){
        System.out.println(
                        "Name: " + this.name +
                        "\nCost: " + this.cost+
                        "\nCount: " + this.count+
                        "\n----------");
    }
}
