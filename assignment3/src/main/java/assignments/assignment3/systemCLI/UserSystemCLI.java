package assignments.assignment3.systemCLI;

import java.util.Scanner;

import assignments.assignment3.User;

public abstract class UserSystemCLI {
    protected Scanner input;
    protected User user;

    public void run(User user, Scanner scanner) {
        this.user = user;
        this.input = scanner;

        // Main loop
        boolean isLoggedIn = true;
        while (isLoggedIn) {
            displayMenu();

            // ambil command
            int command = input.nextInt();
            input.nextLine();

            // menentukan apakah user meminta exit
            isLoggedIn = this.handleMenu(command);
        }
    }

    protected abstract void displayMenu();
    protected abstract boolean handleMenu(int command);
}
