package shops;

public interface Item {
    void showItemInfo(int percent);
    void decreaseCount(int count);
    void increaseCount(int count);
    Object copy(int id);
    String getName();
    double getCost();
    int getCount();
    void setCount(int count);
    void setCost(double cost);
}
