public class Main{
    public static void main(String[] args) {

        ProgramFacade programFacade = new ProgramFacade(null);
        // USER
        programFacade.CreateBasicListUsers();
        // SHOP ITEM
        programFacade.CreateBasicListShops();

        programFacade.HomePage();
    }
}