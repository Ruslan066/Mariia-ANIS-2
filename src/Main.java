import users.User;

public class Main{
    public static void main(String[] args) {

        User user = null;
        ProgramFacade programFacade = new ProgramFacade(user);
        // USER
        programFacade.CreateBasicListUsers();
        // SHOP ITEM
        programFacade.CreateBasicListShops();

        programFacade.HomePage();


    }
}