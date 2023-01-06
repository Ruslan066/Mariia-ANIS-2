public class Employee extends User{

    public Employee(String name, String phoneNumber, String login, String password) {
        super(name, phoneNumber, login, password, true);
    }

    @Override
    public void ShowUserInfo() {
        System.out.println(
                "Name: " + super.getName() +
                        "\nPhone: " + super.getPhoneNumber() +
                        "\nLogin: " + super.getLogin() +
                        "\nPassword: " + super.getPassword() +
                        "\nisEmployee: " + super.isEmployee());
    }

    @Override
    public void MakeDeposit(int deposit) {
    }

    @Override
    public void SetDiscountCard(String card) {

    }

}
