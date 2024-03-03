package assignments.assignment1;

import java.util.Scanner;

public class OrderGenerator {
    private static final Scanner input = new Scanner(System.in);
    private static final String dateRegex = "\\d{2}\\/\\d{2}\\/\\d{4}";

    /*
     * Anda boleh membuat method baru sesuai kebutuhan Anda
     * Namun, Anda tidak boleh menghapus ataupun memodifikasi return type method
     * yang sudah ada.
     */

    /*
     * Method ini untuk menampilkan menu
     */
    public static void showMenu() {
        System.out.println(">>=======================================<<");
        System.out.println("|| ___                 ___             _ ||");
        System.out.println("||| . \\ ___  ___  ___ | __>___  ___  _| |||");
        System.out.println("||| | |/ ._>| . \\/ ._>| _>/ . \\/ . \\/ . |||");
        System.out.println("|||___/\\___.|  _/\\___.|_| \\___/\\___/\\___|||");
        System.out.println("||          |_|                          ||");
        System.out.println(">>=======================================<<");
        System.out.println();
        System.out.println("Pilih menu:");
        System.out.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");
    }

    public static void printPilihMenu() {
        System.out.println("---------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");
    }

    public static int menuInput() {
        System.out.println("---------------------------------------------");
        System.out.print("Pilihan menu: ");
        return input.nextInt();
    }

    public static String generateKodeRestoran(String namaRestoran, int length) {
        if (length <= 0 || namaRestoran.equals("")) {
            return "";
        }

        int newLength = length;
        char firstChar = namaRestoran.charAt(0);
        char result = 0;

        if (Character.isLetter(firstChar)) {
            result = Character.toUpperCase(firstChar);
            newLength = length - 1;
        }

        return result + generateKodeRestoran(namaRestoran.substring(1), newLength);
    }

    public static String calculateChecksum(String orderID) {
        int checksum1 = 0;
        int checksum2 = 0;

        for (int i = 0; i < orderID.length(); i++) {
            String str = Character.toString(orderID.charAt(i));
            int value = Integer.parseInt(str, 36);

            if (i % 2 == 0) {
                checksum1 += value;
            } else {
                checksum2 += value;
            }
        }

        return Integer.toString(checksum1 % 36, 36).toUpperCase() + Integer.toString(checksum2 % 36, 36).toUpperCase();
    }

    /**
     * Method ini digunakan untuk membuat ID
     * dari nama restoran, tanggal order, dan nomor telepon
     * 
     * @return String Order ID dengan format sesuai pada dokumen soal
     */
    public static String generateOrderID(String namaRestoran, String tanggalOrder, String noTelepon) {
        String result = "";

        result += generateKodeRestoran(namaRestoran, 4);
        result += tanggalOrder.replaceAll("/", "");

        int jumlahNoTelepon = 0;
        for (char digit : noTelepon.toCharArray()) {
            jumlahNoTelepon += digit - '0';
        }

        jumlahNoTelepon %= 100;
        result += jumlahNoTelepon;

        int checksum1 = 0;
        int checksum2 = 0;

        for (int i = 0; i < result.length(); i++) {
            String str = Character.toString(result.charAt(i));
            int value = Integer.parseInt(str, 36);

            if (i % 2 == 0) {
                checksum1 += value;
            } else {
                checksum2 += value;
            }
        }

        result += Integer.toString(checksum1 % 36, 36).toUpperCase();
        result += Integer.toString(checksum2 % 36, 36).toUpperCase();

        return result;
    }

    public static String getOngkir(String lokasi) {
        switch (lokasi) {
            case "P":
            case "p":
                return "Rp 10.000";
            case "U":
            case "u":
                return "Rp 20.000";
            case "T":
            case "t":
                return "Rp 35.000";
            case "S":
            case "s":
                return "Rp 40.000";
            case "B":
            case "b":
                return "Rp 60.000";
            default:
                return "";
        }
    }

    /**
     * Method ini digunakan untuk membuat bill
     * dari order id dan lokasi
     * 
     * @return String Bill dengan format sesuai di bawah:
     *         Bill:
     *         Order ID: [Order ID]
     *         Tanggal Pemesanan: [Tanggal Pemesanan]
     *         Lokasi Pengiriman: [Kode Lokasi]
     *         Biaya Ongkos Kirim: [Total Ongkos Kirim]
     */
    public static String generateBill(String orderID, String lokasi) {
        String ongkir = getOngkir(lokasi);
        String tanggal = (new StringBuilder(orderID.substring(4, 12)))
                .insert(2, '/')
                .insert(5, '/')
                .toString();

        String result = "Bill:"
                + "\nOrder ID: " + orderID
                + "\nTanggal Pemesanan: " + tanggal
                + "\nLokasi Pengiriman: " + lokasi.toUpperCase()
                + "\nBiaya Ongkos Kirim: " + ongkir
                + "\n";

        return result;
    }

    public static void main(String[] args) {
        boolean running = true;

        showMenu();
        while (running) {
            int pilihan = menuInput();

            switch (pilihan) {
                case 1: {
                    String namaRestoran;
                    String tanggalOrder;
                    String noTelepon;

                    while (true) {
                        System.out.print("Nama Restoran: ");
                        namaRestoran = input.next();

                        if (namaRestoran.length() < 4) {
                            System.out.println("Nama Restoran tidak valid!\n");
                            continue;
                        }

                        System.out.print("Tanggal Pemesanan: ");
                        tanggalOrder = input.next();

                        if (!tanggalOrder.matches(dateRegex)) {
                            System.out.println("Tanggal pemesanan dalam format DD/MM/YYYY!\n");
                            continue;
                        }

                        System.out.print("No. Telpon: ");
                        noTelepon = input.next();

                        if (!noTelepon.matches("\\d*")) {
                            System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif.\n");
                            continue;
                        }

                        break;
                    }

                    String orderID = generateOrderID(namaRestoran, tanggalOrder, noTelepon);
                    System.out.println("Order ID " + orderID + " diterima!");

                    break;
                }

                case 2: {
                    String orderID;
                    String lokasi;

                    while (true) {
                        System.out.print("Order ID: ");
                        orderID = input.next();

                        if (orderID.length() < 16) {
                            System.out.println("OrderID minimal 16 karakter\n");
                            continue;
                        }

                        String checksum = calculateChecksum(orderID.substring(0, 14));
                        if (!checksum.equals(orderID.substring(14, 16))) {
                            System.out.println(orderID.substring(14, 16));
                            System.out.println("Silahkan masukkan Order ID yang valid!\n");
                            continue;
                        }

                        System.out.print("Lokasi Pengiriman: ");
                        lokasi = input.next().toUpperCase().strip();

                        if (!(lokasi.equals("P")
                                || lokasi.equals("U")
                                || lokasi.equals("T")
                                || lokasi.equals("S")
                                || lokasi.equals("B"))) {
                            System.out.println("Harap masukkan lokasi pengiriman yang ada pada jangkauan!\n");
                            continue;
                        }

                        break;
                    }

                    String bill = generateBill(orderID, lokasi);
                    System.out.println("\n" + bill);

                    break;
                }

                case 3: {
                    System.out.println("Terima kasih telah menggunakan DepeFood!");
                    running = false;
                    continue;
                }
            }

            printPilihMenu();
        }
    }
}
