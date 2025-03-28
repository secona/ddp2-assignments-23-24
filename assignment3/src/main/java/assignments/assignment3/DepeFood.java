package assignments.assignment3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import assignments.assignment1.OrderGenerator;
import assignments.assignment2.Menu;
import assignments.assignment2.Order;
import assignments.assignment2.Restaurant;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DebitPayment;
import assignments.assignment3.payment.DepeFoodPaymentSystem;

public class DepeFood {
    private static ArrayList<User> userList;
    private static List<Restaurant> restoList = new ArrayList<>();
    private static User userLoggedIn;

    public static User getUserLoggedIn() {
        return userLoggedIn;
    }

    public static String getUserLoggedInRole() {
        return userLoggedIn.getRole();
    }

    public static void initUser() {
        userList = new ArrayList<>();

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

    public static User getUser(String nama, String nomorTelepon) {
        for (User user : userList) {
            if (user.getName().equals(nama.trim()) && user.getNomorTelepon().equals(nomorTelepon.trim())) {
                return user;
            }
        }
        return null;
    }

    public static User handleLogin(String nama, String nomorTelepon) {
        User user = getUser(nama, nomorTelepon);

        if (user == null) {
            System.out.println("Pengguna dengan data tersebut tidak ditemukan!");
            return null;
        }

        userLoggedIn = user;
        return user;
    }

    public static String handleTambahRestoran(String nama) throws Exception {
        testValidRestaurantName(nama);

        Restaurant restaurant = new Restaurant(nama);
        restoList.add(restaurant);

        return "Restaurant " + restaurant.getNama() + " Berhasil terdaftar.";
    }

    public static void testValidRestaurantName(String inputName) throws Exception {
        while (true) {
            System.out.print("Nama: ");
            boolean isRestaurantExist = restoList.stream()
                    .anyMatch(restoran -> restoran.getNama().toLowerCase().equals(inputName.toLowerCase()));
            boolean isRestaurantNameLengthValid = inputName.length() >= 4;

            if (isRestaurantExist) {
                throw new Exception(String.format(
                        "Restoran dengan nama %s sudah pernah terdaftar. Mohon masukkan nama yang berbeda!",
                        inputName));
            } else if (!isRestaurantNameLengthValid) {
                throw new Exception("Nama Restoran tidak valid! Minimal 4 karakter diperlukan.");
            } else {
                break;
            }
        }
    }

    public static Restaurant findRestaurant(String nama) {
        for (Restaurant resto : restoList) {
            if (resto.getNama().equals(nama)) {
                return resto;
            }
        }
        return null;
    }

    public static void handleTambahMenuRestoran(Restaurant restoran, String namaMakanan, String harga) throws Exception {
        if (namaMakanan.equals("")) {
            throw new Exception("Nama makanan tidak boleh kosong!");
        }

        if (harga.equals("")) {
            throw new Exception("Harga tidak boleh kosong!");
        }

        try {
            double hargaDouble = Double.parseDouble(harga);
            restoran.addMenu(new Menu(namaMakanan, hargaDouble));
        } catch (NumberFormatException ex) {
            throw new Exception("Harga harus berupa angka!");
        }
    }

    public static List<Restaurant> getRestoList() {
        return restoList;
    }

    public static String[] getRestoNames() {
        String[] names = new String[restoList.size()];

        for (int i = 0; i < restoList.size(); i++) {
            names[i] = restoList.get(i).getNama();
        }

        return names;
    }

    public static Restaurant getRestaurantByName(String name) {
        Optional<Restaurant> restaurantMatched = restoList.stream()
                .filter(restoran -> restoran.getNama().equalsIgnoreCase(name)).findFirst();
        if (restaurantMatched.isPresent()) {
            return restaurantMatched.get();
        }
        return null;
    }

    public static String handleBuatPesanan(
            String namaRestoran,
            String tanggalPemesanan,
            int jumlahPesanan,
            List<String> listMenuPesananRequest) throws Exception {
        System.out.println("--------------Buat Pesanan----------------");

        Restaurant restaurant = getRestaurantByName(namaRestoran);
        if (restaurant == null) {
            throw new Exception("Restoran tidak terdaftar pada sistem.");
        }

        if (!OrderGenerator.validateDate(tanggalPemesanan)) {
            throw new Exception("Masukkan tanggal sesuai format (DD/MM/YYYY).");
        }

        if (!validateRequestPesanan(restaurant, listMenuPesananRequest)) {
            throw new Exception("Mohon memesan menu yang tersedia di Restoran!.");
        }

        Order order = new Order(
                OrderGenerator.generateOrderID(namaRestoran, tanggalPemesanan, userLoggedIn.getNomorTelepon()),
                tanggalPemesanan,
                OrderGenerator.calculateDeliveryCost(userLoggedIn.getLokasi()),
                restaurant,
                getMenuRequest(restaurant, listMenuPesananRequest));

        System.out.printf("Pesanan dengan ID %s diterima!", order.getOrderID());
        userLoggedIn.addOrder(order);
        return order.getOrderID();
    }

    public static String handleBayarBill(String orderId, String paymentOption) throws Exception {
        Order order = getOrderOrNull(orderId);

        if (order == null) {
            throw new Exception("Order ID tidak dapat ditemukan.");
        }

        System.out.println(order.toString());

        if (order.isOrderFinished()) {
            throw new Exception("Pesanan dengan ID ini sudah lunas!");
        }

        if (!paymentOption.equals("Credit Card") && !paymentOption.equals("Debit")) {
            throw new Exception("Pilihan tidak valid, silakan coba kembali.");
        }

        if (!userLoggedIn.hasPaymentMethod(paymentOption)) {
            throw new Exception("User belum memiliki metode pembayaran ini!");
        }

        DepeFoodPaymentSystem paymentSystem = userLoggedIn.getPaymentSystem();

        long totalHarga = order.calculateTotalHarga();
        long amountToPay = paymentSystem.processPayment(userLoggedIn.getSaldo(), totalHarga);

        long saldoLeft = userLoggedIn.getSaldo() - amountToPay;

        userLoggedIn.setSaldo(saldoLeft);
        handleUpdateStatusPesanan(order);

        if (paymentSystem instanceof CreditCardPayment) {
            return String.format("Berhasil Membayar Bill sebesar Rp %d dengan biaya transaksi sebesar Rp %d",
                    totalHarga,
                    ((CreditCardPayment) paymentSystem).countTransactionFee(totalHarga));
        } else {
            return String.format("Berhasil Membayar Bill sebesar Rp %d",
                    amountToPay);
        }
    }

    public static Order getOrderOrNull(String orderId) {
        for (User user : userList) {
            for (Order order : user.getOrderHistory()) {
                if (order.getOrderID().equals(orderId)) {
                    return order;
                }
            }
        }
        return null;
    }

    public static boolean validateRequestPesanan(Restaurant restaurant, List<String> listMenuPesananRequest) {
        return listMenuPesananRequest.stream().allMatch(
                pesanan -> restaurant.getMenu().stream().anyMatch(menu -> menu.getNamaMakanan().equals(pesanan)));
    }

    public static Menu[] getMenuRequest(Restaurant restaurant, List<String> listMenuPesananRequest) {
        Menu[] menu = new Menu[listMenuPesananRequest.size()];
        for (int i = 0; i < menu.length; i++) {
            for (Menu existMenu : restaurant.getMenu()) {
                if (existMenu.getNamaMakanan().equals(listMenuPesananRequest.get(i))) {
                    menu[i] = existMenu;
                }
            }
        }
        return menu;
    }

    public static Order findUserOrderById(String orderId) {
        Order[] orderHistory = userLoggedIn.getOrderHistory();

        for (Order order : orderHistory) {
            if (order.getOrderID() == orderId) {
                return order;
            }
        }
        return null;
    }

    public static void handleUpdateStatusPesanan(Order order) {
        order.setOrderFinished(true);
    }

    public static void setPenggunaLoggedIn(User user) {
        userLoggedIn = user;
    }
}
