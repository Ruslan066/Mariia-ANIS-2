package shops;

public class ShopFactory {
    Shop shop;
    int id =0;

    public ShopFactory(Shop shop) {
        this.shop = shop;
    }
    public void SetShop(Shop shop) {
        this.shop = shop;
    }
    public Shop CloneShop(){
        id++;
        return (Shop) shop.copy(id);
    }
}
