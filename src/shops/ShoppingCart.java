package shops;

public final class ShoppingCart {
    private static ShoppingCart instance;
    public String value;

    private ShoppingCart(String value){
        // Этот код эмулирует медленную инициализацию.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    public static ShoppingCart getInstance(String value) {
        if (instance == null) {
            instance = new ShoppingCart(value);
        }
        return instance;
    }
}
