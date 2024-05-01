package assignments.assignment3.systemCLI;

import java.util.Scanner;

import assignments.assignment3.User;

public abstract class UserSystemCLI {
    protected Scanner input;
    protected User user;

    public void run(User user) {
        this.user = user;
        this.input = new Scanner(System.in);

        boolean isLoggedIn = true;
        while (isLoggedIn) {
            displayMenu();
            int command = input.nextInt();
            input.nextLine();
            isLoggedIn = this.handleMenu(command);
        }
    }

    protected abstract void displayMenu();
    protected abstract boolean handleMenu(int command);
}
