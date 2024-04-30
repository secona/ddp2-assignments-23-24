package assignments.assignment3.systemCLI;

import java.util.ArrayList;
import assignments.assignment1.OrderGenerator;
import assignments.assignment2.Menu;
import assignments.assignment2.Order;
import assignments.assignment2.Restaurant;
import assignments.assignment3.MainMenu;
import assignments.assignment3.User;

public class CustomerSystemCLI extends UserSystemCLI {
    /**
     * @return apakah user masih logged in
     */
    public boolean handleMenu(int choice) {
        switch (choice) {
            case 1 -> handleBuatPesanan();
            case 2 -> handleCetakBill();
            case 3 -> handleLihatMenu();
            case 4 -> handleBayarBill();
            case 5 -> handleCekSaldo();
            case 6 -> {
                return false;
            }
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

    public void displayMenu() {
        System.out.println("\n--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Buat Pesanan");
        System.out.println("2. Cetak Bill");
        System.out.println("3. Lihat Menu");
        System.out.println("4. Bayar Bill");
        System.out.println("5. Cek Saldo");
        System.out.println("6. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    private void handleBuatPesanan() {
        System.out.println("--------------Buat Pesanan----------------");

        while (true) {
            // ambil input nama restoran
            System.out.print("Nama Restoran: ");
            String namaRestoran = super.input.nextLine();

            // cek jika restoran ada
            Restaurant restoran = MainMenu.findRestaurant(namaRestoran);

            // jika restoran tidak ada
            if (restoran == null) {
                System.out.println("Restoran tidak terdaftar pada sistem.\n");
                continue;
            }

            // ambil input tanggal pemesanan
            System.out.print("Tanggal Pemesanan (DD/MM/YYYY): ");
            String tanggalPemesanan = super.input.nextLine();

            // jika tanggal tidak memenuhi format
            if (!tanggalPemesanan.matches(OrderGenerator.dateRegex)) {
                System.out.println("Masukkan tanggal sesuai format (DD/MM/YYYY)!\n");
                continue;
            }

            // ambil input jumlah pesanan
            System.out.print("Jumlah Pesanan: ");
            int jumlahPesanan = super.input.nextInt();
            super.input.nextLine();

            // loop sebanyak jumlah pesanan
            System.out.println("Order:");
            ArrayList<Menu> items = new ArrayList<Menu>();
            for (int i = 0; i < jumlahPesanan; i++) {
                // mencari nama makanan
                String namaMakanan = super.input.nextLine();
                Menu menu = restoran.getOneMenu(namaMakanan);

                if (menu != null) { // jika menu ada
                    items.add(menu);
                } else { // jika menu tidak ada
                    items = null;
                    break;
                }
            }

            // jika nama menu tidak ada
            if (items == null) {
                System.out.println("Menu tidak tersedia.\n");
                continue;
            }

            // inisiasi Order
            User user = super.getUser();
            String orderID = OrderGenerator.generateOrderID(namaRestoran, tanggalPemesanan, user.getNomorTelepon());
            int ongkosKirim = Order.calculateOngkosKirim(user.getLokasi());
            Order order = new Order(orderID, tanggalPemesanan, ongkosKirim, restoran, items);
            user.addOrder(order);

            // done
            System.out.println("Pesanan dengan ID " + orderID + " diterima!");
            break;
        }
    }

    private void handleCetakBill() {
        System.out.println("--------------Cetak Bill----------------");

        // Ambil input order id
        System.out.print("Masukkan Order ID: ");
        String orderID = super.input.nextLine();

        // Mencari order
        Order order = MainMenu.findOrder(orderID);

        // Jika tidak ada order
        if (order == null) {
            System.out.println("Order ID tidak dapat ditemukan.");
            return;
        }

        System.out.println();
        System.out.println(order.toString(super.getUser().getLokasi()));
    }

    private void handleLihatMenu() {
        System.out.println("--------------Lihat Menu----------------");

        while (true) {
            // ambil input nama restoran
            System.out.print("Nama Restoran: ");
            String namaRestoran = super.input.nextLine();

            // mencari restoran dengan nama yang sesuai
            Restaurant restoran = MainMenu.findRestaurant(namaRestoran);

            // berdasarkan apakah restoran dengan nama tersebut ada
            if (restoran != null) {
                // print menu
                System.out.println("Menu:");
                for (int i = 0; i < restoran.menuLength(); i++) {
                    Menu menu = restoran.getOneMenu(i);
                    System.out.printf("%d. %s\n", i + 1, menu.toString());
                }

                // break loop
                break;
            } else {
                // print error
                System.out.println("Restoran tidak terdaftar pada sistem.");
            }
        }
    }

    private void handleBayarBill() {
        System.out.println("--------------Bayar Bill----------------");

        // Ambil input order id
        System.out.print("Masukkan Order ID: ");
        String orderID = super.input.nextLine();

        // Mencari order
        Order order = MainMenu.findOrder(orderID);

        // Jika tidak ada order
        if (order == null) {
            System.out.println("Order ID tidak dapat ditemukan.");
            return;
        }

        System.out.println();
        System.out.println(order.toString(super.getUser().getLokasi()));

        System.out.println();
        System.out.println("Opsi Pembayaran:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit");

        // Ambil pilihan metode
        System.out.print("Pilihan Metode Pembayaran:");
        int metode = super.input.nextInt();
        super.input.nextLine();

        // Bayar
        super.getUser().payOrder(metode, order);
    }

    // private void handleUpdateStatusPesanan() {
    // // TODO: Implementasi method untuk handle ketika customer ingin update status
    // // pesanan
    // }

    private void handleCekSaldo() {
        long saldo = super.getUser().getSaldo();
        System.out.printf("Sisa saldo sebesar Rp %d\n", saldo);
    }
}
