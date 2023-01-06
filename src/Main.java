import shops.Item;
import shops.Shop;
import shops.ShopFactory;

public class Main extends Start{
    public static void main(String[] args) {

        // USER
        // new Start().CreateBasicListUsers();
        //new Start();


//SHOP ITEM
// патерн клонирования магазина
        Item Peony = new Item(0, "Peony", 3);
        Item Rose = new Item(1, "Rose", 5.5);
        Item Fir = new Item(2, "Fir", 15);
        Item Cactus = new Item(3, "Cactus", 7.98);

        Shop HappyChappy = new Shop(0, "HappyChappy", "Kosice Jedlikova 9");
        HappyChappy.AddItem(Peony);
        HappyChappy.AddItem(Rose);
        HappyChappy.AddItem(Fir);
        HappyChappy.AddItem(Cactus);

        ShopFactory factory = new ShopFactory(HappyChappy);
        Shop FlowerCat = factory.CloneShop();
        FlowerCat.setName("FlowerCat");
        FlowerCat.setAddress("Hlavna 11");

        HappyChappy.ShowShopInfo();
        System.out.println("\n===================\n");
        FlowerCat.ShowShopInfo();
    }
}