package users;

public class Client extends User{
    private int money;
    private String discountCard;
    private int discountPercent;

    public Client(String name, String phoneNumber, String login, String password) {
        super(name, phoneNumber, login, password, false);
        this.money = 100;
        this.discountCard = DiscountCard.NO_CARD.toString();
        this.discountPercent = DiscountCard.valueOf(discountCard).ordinal();
    }

    public Client(){

    }

    @Override
    public void ShowUserInfo() {
            System.out.println(
                    "=-- users.Client info --="+
                    "\nName: " + super.getName() +
                    "\nPhone: " + super.getPhoneNumber() +
                    "\nLogin: " + super.getLogin() +
                    "\nPassword: " + super.getPassword() +
                    "\nMoney: " + this.money+
                    "\nDiscount Card: " + this.discountCard+
                    " - " + this.discountPercent+ "%"+
                    "\nisEmployee: " + super.isEmployee());
    }

    @Override
    public void MakeDeposit(int deposit) {
        this.money += deposit;
    }

    @Override
    public void SetDiscountCard(String card) {
        for (DiscountCard dc : DiscountCard.values()) {
            if (dc.name().equals(card)) {
                this.discountCard = card;
                this.discountPercent = DiscountCard.valueOf(discountCard).percent;
            }
            else {
                //System.out.println("Cant find card!");
            }
        }
    }
}
