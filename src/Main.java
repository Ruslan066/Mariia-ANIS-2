public class Main{
    public static void main(String[] args) {

        ProgramFacade programFacade = new ProgramFacade();
        // USER
        programFacade.createBasicListUsers();
        // SHOP ITEM
        programFacade.createBasicListShops();

        programFacade.displayHomePage();
    }
}