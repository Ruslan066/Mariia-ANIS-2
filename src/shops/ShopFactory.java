package shops;

public class ShopFactory {
    Shop shop;
    int id;

    public ShopFactory(Shop shop) {
        this.shop = shop;
        this.id = shop.getId();
    }
    public Shop CloneShop(){
        id++;
        return (Shop) shop.copy(id);
    }
}
