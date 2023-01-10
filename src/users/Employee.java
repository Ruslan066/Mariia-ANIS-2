package users;

public class Employee extends User {

    public Employee(int id, String name, String phoneNumber, String login, String password) {
        super(id, name, phoneNumber, login, password, true);
    }

    //From Class User
    @Override
    public void ShowUserInfo() {
        System.out.println(
                "=-- Employee info --=" +
                "\nName: " + super.getName() +
                "\nPhone: " + super.getPhoneNumber() +
                "\nLogin: " + super.getLogin() +
                "\nPassword: " + super.getPassword() +
                "\nisEmployee: " + super.isEmployee());
    }
}
