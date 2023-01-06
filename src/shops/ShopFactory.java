package shops;

public class ShopFactory {
    Shop shop;

    public ShopFactory(Shop shop) {
        this.shop = shop;
    }
    public void SetShop(Shop shop) {
        this.shop = shop;
    }
    public Shop CloneShop(){
        return (Shop) shop.copy();
    }
}
