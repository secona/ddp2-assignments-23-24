package assignments.assignment3;

import java.util.ArrayList;
import java.util.Scanner;

import assignments.assignment2.Order;
import assignments.assignment2.Restaurant;
import assignments.assignment3.LoginManager;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DebitPayment;
import assignments.assignment3.systemCLI.AdminSystemCLI;
import assignments.assignment3.systemCLI.CustomerSystemCLI;
import assignments.assignment3.systemCLI.UserSystemCLI;

public class MainMenu {
    private final Scanner input;
    private final LoginManager loginManager;
    public static ArrayList<Restaurant> restoList = new ArrayList<>();
    private static ArrayList<User> userList = new ArrayList<>();

    public MainMenu(Scanner in, LoginManager loginManager) {
        this.input = in;
        this.loginManager = loginManager;
    }

    public static void main(String[] args) {
        initUser();
        MainMenu mainMenu = new MainMenu(
                new Scanner(System.in),
                new LoginManager(
                        new AdminSystemCLI(),
                        new CustomerSystemCLI()));
        mainMenu.run();
    }

    /**
     * Fungsi utama untuk menjalankan program
     */
    public void run() {
        printHeader();

        // Main loop
        boolean exit = false;
        while (!exit) {
            startMenu();

            // Ambil pilihan user
            int choice = input.nextInt();
            input.nextLine();

            // Menentukan pilihan user
            switch (choice) {
                case 1 -> login();
                case 2 -> exit = true;
                default -> System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }

        // Clean up
        input.close();
        System.out.println("Terima kasih telah menggunakan DepeFood!");
    }

    /**
     * Fungsi untuk login bagi user
     */
    private void login() {
        System.out.println("\nSilakan Login:");

        // Ambil nama user
        System.out.print("Nama: ");
        String nama = input.nextLine();

        // Ambil nomor telepon user
        System.out.print("Nomor Telepon: ");
        String noTelp = input.nextLine();

        // Mencari user dengan nama dan nomor telepon yang sesuai
        User userLoggedIn = findUser(nama, noTelp);

        // Jika user ditemukan
        if (userLoggedIn != null) {
            System.out.printf("Selamat Datang %s!\n", userLoggedIn.getName());

            UserSystemCLI system = loginManager.getSystem(userLoggedIn.getRole());
            system.run(userLoggedIn);
        } else {
            System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
        }
    }

    /**
     * Mencari user dengan nama dan nomor telepon yang sesuai
     *
     * @return User
     */
    public static User findUser(String nama, String noTelp) {
        for (User user : userList) {
            if (user.getName().equals(nama) &&
                    user.getNomorTelepon().equals(noTelp)) {
                return user;
            }
        }

        return null;
    }

    /**
     * Mencari restaurant dengan nama yang sesuai
     *
     * @return Restaurant
     */
    public static Restaurant findRestaurant(String nama) {
        for (Restaurant restaurant : restoList) {
            if (restaurant.getNama().equalsIgnoreCase(nama)) {
                return restaurant;
            }
        }

        return null;
    }

    /**
     * @param orderID orderID dari order yang ingin dicari
     * @return Order dengan orderID tersebut
     */
    public static Order findOrder(String orderID) {
        for (User user : userList) {
            for (Order order : user.getOrderHistory()) {
                if (order.isOrderID(orderID)) {
                    return order;
                }
            }
        }

        return null;
    }

    private static void printHeader() {
        System.out.println("\n>>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
    }

    private static void startMenu() {
        System.out.println("\nSelamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void initUser() {
        userList = new ArrayList<User>();

        userList.add(
                new User("Thomas N", "9928765403", "thomas.n@gmail.com", "P", "Customer", new DebitPayment(), 500000));
        userList.add(new User("Sekar Andita", "089877658190", "dita.sekar@gmail.com", "B", "Customer",
                new CreditCardPayment(), 2000000));
        userList.add(new User("Sofita Yasusa", "084789607222", "sofita.susa@gmail.com", "T", "Customer",
                new DebitPayment(), 750000));
        userList.add(new User("Dekdepe G", "080811236789", "ddp2.gampang@gmail.com", "S", "Customer",
                new CreditCardPayment(), 1800000));
        userList.add(new User("Aurora Anum", "087788129043", "a.anum@gmail.com", "U", "Customer", new DebitPayment(),
                650000));

        userList.add(new User("Admin", "123456789", "admin@gmail.com", "-", "Admin", new CreditCardPayment(), 0));
        userList.add(
                new User("Admin Baik", "9123912308", "admin.b@gmail.com", "-", "Admin", new CreditCardPayment(), 0));
    }
}
