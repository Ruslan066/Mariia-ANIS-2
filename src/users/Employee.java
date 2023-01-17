package users;

public class Employee extends User {

    public Employee(int id, String name, String phoneNumber, String login, String password) {
        super(id, name, phoneNumber, login, password, true);
    }

    /**
     * COMPLETE
     * This method implement from User class and displays information about the Employee
     */
    @Override
    public void showUserInfo() {
        System.out.println(
                set("YELLOW") + "=--" + set("PURPLE") +" Employee "+ set("GREEN") + "info" + set("YELLOW") + " --=" +
                set("CYAN") + "\nName: " +  set("RESET") + super.getName() +
                set("CYAN") + "\nPhone: " + set("RESET") + super.getPhoneNumber() +
                set("CYAN") + "\nLogin: " + set("RESET") + super.getLogin() +
                set("CYAN") + "\nPassword: " + set("RESET") + super.getPassword() +
                set("CYAN") + "\nisEmployee: " + set("RESET") + super.isEmployee());
    }
}
