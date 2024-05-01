package assignments.assignment2;

import java.util.ArrayList;
import java.util.Scanner;
import assignments.assignment1.OrderGenerator;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static ArrayList<Restaurant> restoList;
    private static ArrayList<User> userList;

    public static void main(String[] args) {
        // inisiasi program
        restoList = new ArrayList<Restaurant>();
        initUser();
        printHeader();

        boolean programRunning = true;
        while (programRunning) {
            startMenu();

            // mengambil input command
            int command = input.nextInt();
            input.nextLine();

            // jika login
            if (command == 1) {
                System.out.println("\nSilakan Login:");

                // ambil input nama
                System.out.print("Nama: ");
                String nama = input.nextLine();

                // ambil input nomor telepon
                System.out.print("Nomor Telepon: ");
                String nomorTelepon = input.nextLine();

                // coba cari user dengan credentials tersebut
                User userLoggedIn = getUser(nama, nomorTelepon);

                // jika tidak ditemukan ulang dari awal
                if (userLoggedIn == null) {
                    System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
                    continue;
                }

                // jika ditemukan lanjut berdasarkan role
                boolean isLoggedIn = true;

                if (userLoggedIn.getRole().equals("Customer")) {
                    // jika customer
                    System.out.println("Selamat Datang " + userLoggedIn.getName() + "!");
                    while (isLoggedIn) {
                        menuCustomer();

                        // ambil input command
                        int commandCust = input.nextInt();
                        input.nextLine();

                        switch (commandCust) {
                            case 1 -> handleBuatPesanan(userLoggedIn);
                            case 2 -> handleCetakBill(userLoggedIn);
                            case 3 -> handleLihatMenu();
                            case 4 -> handleUpdateStatusPesanan();
                            case 5 -> isLoggedIn = false;
                            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
                        }
                    }
                } else {
                    // jika admin
                    System.out.println("Selamat Datang Admin!");
                    while (isLoggedIn) {
                        menuAdmin();

                        // ambil input command
                        int commandAdmin = input.nextInt();
                        input.nextLine();

                        switch (commandAdmin) {
                            case 1 -> handleTambahRestoran();
                            case 2 -> handleHapusRestoran();
                            case 3 -> isLoggedIn = false;
                            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
                        }
                    }
                }
            } else if (command == 2) {
                programRunning = false;
            } else {
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("\nTerima kasih telah menggunakan DepeFood ^___^");
    }

    /**
     * @param nama         nama dari user yang ingin dicari
     * @param nomorTelepon nomor telepon dari user yang ingin dicari
     * @return User dengan nama dan nomor telepon tersebut
     */
    public static User getUser(String nama, String nomorTelepon) {
        for (User user : userList) {
            if (user.getName().equals(nama)
                    && user.getNomorTelepon().equals(nomorTelepon)) {
                return user;
            }
        }

        return null;
    }

    /**
     * @param orderID orderID dari order yang ingin dicari
     * @return Order dengan orderID tersebut
     */
    public static Order getOrder(String orderID) {
        for (User user : userList) {
            for (Order order : user.getOrderHistory()) {
                if (order.isOrderID(orderID)) {
                    return order;
                }
            }
        }

        return null;
    }

    /**
     * @param namaRestoran nama restoran yang ingin dicari
     * @return Restoran dengan nama tersebut
     */
    public static Restaurant getRestaurant(String namaRestoran) {
        for (Restaurant restaurant : restoList) {
            if (restaurant.isName(namaRestoran)) {
                return restaurant;
            }
        }

        return null;
    }

    public static void handleBuatPesanan(User user) {
        System.out.println("--------------Buat Pesanan----------------");

        while (true) {
            // ambil input nama restoran
            System.out.print("Nama Restoran: ");
            String namaRestoran = input.nextLine();

            // cek jika restoran ada
            Restaurant restoran = null;
            for (Restaurant currentRestoran : restoList) {
                if (currentRestoran.isName(namaRestoran)) {
                    restoran = currentRestoran;
                    break;
                }
            }

            // jika restoran tidak ada
            if (restoran == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }

            // ambil input tanggal pemesanan
            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            String tanggalPemesanan = input.nextLine();

            // jika tanggal tidak memenuhi format
            if (!tanggalPemesanan.matches(OrderGenerator.dateRegex)) {
                System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)!\n");
                continue;
            }

            // ambil input jumlah pesanan
            System.out.print("Jumlah Pesanan: ");
            int jumlahPesanan = input.nextInt();
            input.nextLine();

            // loop sebanyak jumlah pesanan
            System.out.println("Order:");
            String[] namaMakanan = new String[jumlahPesanan];
            for (int i = 0; i < jumlahPesanan; i++) {
                namaMakanan[i] = input.nextLine();
            }

            // cari nama makanan tersebut di restoran
            Menu[] items = Menu.parseMakananRestoran(restoran, namaMakanan);

            // jika nama menu tidak ada
            if (items == null) {
                System.out.println("Menu tidak tersedia.\n");
                continue;
            }

            // inisiasi Order
            String orderID = OrderGenerator.generateOrderID(namaRestoran, tanggalPemesanan, user.getNomorTelepon());
            int ongkosKirim = Order.calculateOngkosKirim(user.getLokasi());
            Order order = new Order(orderID, tanggalPemesanan, ongkosKirim, restoran, items);
            user.addOrder(order);

            // done
            System.out.println("Pesanan dengan ID " + orderID + " diterima!");
            break;
        }
    }

    public static void handleCetakBill(User user) {
        System.out.println("--------------Cetak Bill----------------");

        System.out.print("Masukkan Order ID: ");
        String orderID = input.nextLine();
        Order order = getOrder(orderID);

        if (order == null) {
            System.out.println("Order tidak ada.");
            return;
        }

        System.out.println();
        System.out.println(order.toString(user.getLokasi()));
    }

    public static void handleLihatMenu() {
        System.out.println("--------------Lihat Menu----------------");

        while (true) {
            // ambil input nama restoran
            System.out.print("Nama Restoran: ");
            String namaRestoran = input.nextLine();

            for (Restaurant restoran : restoList) {
                if (restoran.isName(namaRestoran)) {
                    System.out.println("Menu:");
                    for (int i = 0; i < restoran.menuLength(); i++) {
                        Menu menu = restoran.getOneMenu(i);
                        System.out.printf("%d. %s\n", i + 1, menu.toString());
                    }

                    return;
                }
            }

            System.out.println("Restoran tidak terdaftar pada sistem.");
        }
    }

    public static void handleUpdateStatusPesanan() {
        System.out.println("--------------Update Status Pesanan----------------");

        while (true) {
            // ambil input orderID
            System.out.print("Masukkan Order ID: ");
            String orderID = input.nextLine();

            // mencari order dan handle jika order tidak ditemukan
            Order order = getOrder(orderID);
            if (order == null) {
                System.out.println("Order ID tidak dapat ditemukan\n");
                continue;
            }

            // ambil input update status
            System.out.print("Status: ");
            String status = input.nextLine();
            boolean selesai = status.equalsIgnoreCase("Selesai");

            // update status
            if (order.updateStatus(selesai)) {
                System.out.printf("Status pesanan dengan ID %s berhasil diupdate!\n", orderID);
                break;
            } else {
                System.out.printf("Status pesanan dengan ID %v tidak berhasil diupdate!\n", orderID);
            }
        }
    }

    public static void handleTambahRestoran() {
        System.out.println("--------------Tambah Restoran----------------");

        while (true) {
            // ambil input nama
            System.out.print("Nama: ");
            String namaRestoran = input.nextLine();

            if (namaRestoran.length() < 4) {
                System.out.println("Nama Restoran tidak valid!\n");
                continue;
            }

            // cek jika nama restoran sudah diambil
            Restaurant restaurant = getRestaurant(namaRestoran);
            if (restaurant != null) {
                System.out.println("Restoran dengan nama " + namaRestoran
                        + " sudah pernah terdaftar. Mohon masukkan nama yang berbeda!\n");
                continue;
            }

            // ambil input jumlah makanan
            System.out.print("Jumlah Makanan: ");
            int jumlahMakanan = input.nextInt();
            input.nextLine();

            // ambil input setiap menu
            String[] inputs = new String[jumlahMakanan];
            for (int i = 0; i < jumlahMakanan; i++) {
                inputs[i] = input.nextLine();
            }

            // mengubah input strings menjadi Menu object
            Menu[] menus = Menu.parseMakananHarga(inputs);

            // jika error
            if (menus == null) {
                System.out.println("Harga menu harus bilangan bulat!\n");
                continue;
            }

            // inisiasi restoran
            restaurant = new Restaurant(namaRestoran);
            restaurant.addMenu(menus);
            restoList.add(restaurant);

            // print success message
            System.out.printf("Restaurant %s Berhasil terdaftar.\n", namaRestoran);

            break;
        }
    }

    public static void handleHapusRestoran() {
        System.out.println("--------------Hapus Restoran----------------");

        while (true) {
            // ambil input nama restoran
            System.out.print("Nama Restoran: ");
            String namaRestoran = input.nextLine();

            // cari restoran dengan nama yang sama
            for (int i = 0; i < restoList.size(); i++) {
                Restaurant currentRestoran = restoList.get(i);
                // jika ditemukan restoran dengan nama yang sama
                if (currentRestoran.isName(namaRestoran)) {
                    restoList.remove(i);
                    System.out.println("Restoran berhasil dihapus.");
                    return;
                }
            }

            // jika tidak ditemukan restoran dengan nama yang sama
            System.out.println("Restoran tidak terdaftar pada sistem.\n");
        }
    }

    public static void initUser() {
        userList = new ArrayList<User>();
        userList.add(new User("Thomas N", "9928765403", "thomas.n@gmail.com", "P", "Customer"));
        userList.add(new User("Sekar Andita", "089877658190", "dita.sekar@gmail.com", "B", "Customer"));
        userList.add(new User("Sofita Yasusa", "084789607222", "sofita.susa@gmail.com", "T", "Customer"));
        userList.add(new User("Dekdepe G", "080811236789", "ddp2.gampang@gmail.com", "S", "Customer"));
        userList.add(new User("Aurora Anum", "087788129043", "a.anum@gmail.com", "U", "Customer"));

        userList.add(new User("Admin", "123456789", "admin@gmail.com", "-", "Admin"));
        userList.add(new User("Admin Baik", "9123912308", "admin.b@gmail.com", "-", "Admin"));
    }

    public static void printHeader() {
        System.out.println("\n>>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
    }

    public static void startMenu() {
        System.out.println("\nSelamat datang di DepeFood!");
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuAdmin() {
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    public static void menuCustomer() {
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Update Status Pesanan");
        System.out.println("5. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }
}
