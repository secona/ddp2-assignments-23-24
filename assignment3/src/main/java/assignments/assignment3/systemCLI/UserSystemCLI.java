package assignments.assignment3.systemCLI;

import java.util.Scanner;

import assignments.assignment3.User;

public abstract class UserSystemCLI {
    protected Scanner input;
    private User loggedInUser;

    public void run() {
        this.input = new Scanner(System.in);
        boolean isLoggedIn = true;
        while (isLoggedIn) {
            displayMenu();
            int command = input.nextInt();
            input.nextLine();
            isLoggedIn = handleMenu(command);
        }
    }

    public void setLoggedInUser(User user) {
        this.loggedInUser = user;
    }

    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    abstract void displayMenu();
    abstract boolean handleMenu(int command);
}
