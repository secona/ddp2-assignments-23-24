package assignments.assignment1;

import java.util.Scanner;

public class OrderGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * regex ini akan match bentuk DD/MM/YYYY di mana D, M, dan Y adalah digit
     */
    public static final String dateRegex = "\\d{2}\\/\\d{2}\\/\\d{4}";

    /**
     * regex ini akan match dengan string minimal length 1 yang semua karakternya adalah digit
     */
    private static final String phoneNumberRegex = "\\d+";

    /*
     * Anda boleh membuat method baru sesuai kebutuhan Anda
     * Namun, Anda tidak boleh menghapus ataupun memodifikasi return type method
     * yang sudah ada.
     */

    /**
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

    /**
     * Method ini untuk menampilkan pilihan menu yang ada
     */
    public static void showPilihMenu() {
        System.out.println("---------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Generate Order ID");
        System.out.println("2. Generate Bill");
        System.out.println("3. Keluar");
    }

    /**
     * Method ini untuk mendapatkan input pilihan menu
     *
     * @return pilihan menu yang diinginkan user
     */
    public static String getMenuInput() {
        System.out.println("---------------------------------------------");
        System.out.print("Pilihan menu: ");
        return input.nextLine().strip();
    }

    /**
     * Method ini untuk membuat kode restoran dari nama restoran dan panjang yang
     * diinginkan. Method ini menggunakan rekursif untuk mendapatkan kode restoran.
     *
     * @param namaRestoran nama restoran yang ingin dikodekan
     * @param length       panjang dari kode yang diinginkan
     * @return kode restoran
     */
    public static String generateKodeRestoran(String namaRestoran, int length) {
        // base case
        if (length <= 0 || namaRestoran.equals("")) {
            return "";
        }

        // recursive case
        Character firstChar = namaRestoran.charAt(0);
        if (firstChar.equals(' ')) {
            return generateKodeRestoran(namaRestoran.substring(1), length);
        } else {
            return Character.toUpperCase(firstChar)
                    + generateKodeRestoran(namaRestoran.substring(1), length - 1);
        }
    }

    /**
     * Method ini untuk menghitung checksum dari order id. Karena checksum berupa
     * angka dan huruf dengan 1 sebagai bilangan terkecil dan z sebagai bilangan
     * terbesar, kita dapat manfaatkan sistem perhitungan dengan basis 36 (10 huruf
     * + 26 alfabet).
     *
     * @param orderID order id tanpa 2 karakter checksum di akhir
     * @return 2 karakter checksum
     */
    public static String calculateChecksum(String orderID) {
        // 2 digit checksum
        int checksum1 = 0;
        int checksum2 = 0;

        // loop untuk setiap karakter dalam order id
        for (int i = 0; i < orderID.length(); i++) {
            // hitung value karakternya dalam base 36
            String str = Character.toString(orderID.charAt(i));
            int value = Integer.parseInt(str, 36);

            if (i % 2 == 0) {
                // jika genap, tambahkan ke checksum pertama
                checksum1 += value;
            } else {
                // jika ganjil, tambahkan ke checksum kedua
                checksum2 += value;
            }
        }

        // return kedua checksum yang sudah digabungkan
        return Integer.toString(checksum1 % 36, 36).toUpperCase()
                + Integer.toString(checksum2 % 36, 36).toUpperCase();
    }

    /**
     * Method ini digunakan untuk membuat ID
     * dari nama restoran, tanggal order, dan nomor telepon
     * 
     * @return String Order ID dengan format sesuai pada dokumen soal
     */
    public static String generateOrderID(String namaRestoran, String tanggalOrder, String noTelepon) {
        // definisikan result dari fungsi ini
        String result = "";

        // tambahkan kode restoran sebanyak
        result += generateKodeRestoran(namaRestoran, 4);

        // tambahkan tanggal order tanpa `/`
        result += tanggalOrder.replaceAll("/", "");

        // hitung jumlah dari no telepon
        int jumlahNoTelepon = 0;
        for (char digit : noTelepon.toCharArray()) {
            // kita ubah digit dari bentuk char ke integer dengan menguranginya dengan
            // character '0' seperti menghitung seberapa jauh digit kita dari digit 0
            jumlahNoTelepon += digit - '0';
        }

        // modulokan dengan 100 dan tambahkan ke result
        jumlahNoTelepon %= 100;
        result += String.format("%02d", jumlahNoTelepon);

        // tambahkan checksum menggunakan order id yang sudah kita buat yang belum ada
        // checksumnya
        result += calculateChecksum(result);

        return result;
    }

    /**
     * Method ini untuk mendapatkan ongkir dari lokasi.
     * 
     * @param lokasi lokasi yang diinginkan
     * @return ongkir untuk lokasi tersebut
     */
    public static String getOngkir(String lokasi) {
        // harus case insensitive, jadi dua case untuk setiap character, satu uppercase,
        // satu lowercase
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
        // dapatkan ongkir dari lokasi yang diinginkan
        String ongkir = getOngkir(lokasi);

        // tanggal dengan ditambahkan `/`
        String tanggal = orderID.substring(4, 12);
        String formattedTanggal = "";
        // loop untuk setiap karakter dalam tanggal
        for (int i = 0; i < tanggal.length(); i++) {
            formattedTanggal += tanggal.charAt(i);
            // `/` ditambahkan setelah angka kedua (index 1) dan angka keempat (index 3)
            if (i == 1 || i == 3) {
                formattedTanggal += '/';
            }
        }

        // concatenate resultnya menjadi 1
        String result = "Bill:"
                + "\nOrder ID: " + orderID.toUpperCase()
                + "\nTanggal Pemesanan: " + formattedTanggal
                + "\nLokasi Pengiriman: " + lokasi.toUpperCase()
                + "\nBiaya Ongkos Kirim: " + ongkir
                + "\n";

        return result;
    }

    public static void main(String[] args) {
        // variabel untuk track apakah aplikasi masih berjalan
        boolean running = true;

        // menu hanya ada di awal
        showMenu();

        // main loop
        while (running) {
            String pilihan = getMenuInput();

            switch (pilihan) {
                // generate order id
                case "1": {
                    String namaRestoran;
                    String tanggalOrder;
                    String noTelepon;

                    while (true) {
                        // meminta nama restoran
                        System.out.print("Nama Restoran: ");
                        namaRestoran = input.nextLine();

                        // panjang kode restoran harus sama dengan 4
                        if (generateKodeRestoran(namaRestoran, 4).length() != 4) {
                            System.out.println("Nama Restoran tidak valid!\n");
                            continue; // ulang dari awal
                        }

                        // meminta tanggal pemesanan
                        System.out.print("Tanggal Pemesanan: ");
                        tanggalOrder = input.nextLine();

                        // tanggal pemesanan harus dalam format DD/MM/YYYY
                        if (!tanggalOrder.matches(dateRegex)) {
                            System.out.println("Tanggal Pemesanan dalam format DD/MM/YYYY!\n");
                            continue; // ulang dari awal
                        }

                        // meminta nomor telepon
                        System.out.print("No. Telpon: ");
                        noTelepon = input.nextLine();

                        // nomor telepon harus digit semua
                        if (!noTelepon.matches(phoneNumberRegex)) {
                            System.out.println("Harap masukkan nomor telepon dalam bentuk bilangan bulat positif.\n");
                            continue; // ulang dari awal
                        }

                        // jika validasi selesai, break loopnya supaya dapat lanjut ke tahap selanjutnya
                        break;
                    }

                    // generate order id dan print ke output
                    String orderID = generateOrderID(namaRestoran, tanggalOrder, noTelepon);
                    System.out.println("Order ID " + orderID + " diterima!");

                    break;
                }

                // generate bill
                case "2": {
                    String orderID;
                    String lokasi;

                    while (true) {
                        // meminta order id
                        System.out.print("Order ID: ");
                        orderID = input.nextLine().toUpperCase();

                        // panjang order id harus 16
                        if (orderID.length() < 16) {
                            System.out.println("Order ID minimal 16 karakter\n");
                            continue; // ulang dari awal
                        }

                        // checksum harus valid
                        String checksum = calculateChecksum(orderID.substring(0, 14));
                        // jika checksum yang tertera berbeda dengan checksum yang dikalkulasikan
                        if (!checksum.equals(orderID.substring(14, 16))) {
                            System.out.println("Silahkan masukkan Order ID yang valid!\n");
                            continue; // ulang dari awal
                        }

                        // meminta lokasi pengiriman
                        System.out.print("Lokasi Pengiriman: ");
                        lokasi = input.nextLine().toUpperCase().strip();

                        // lokasi pengiriman harus antara P, U, T, S, atau B
                        if (!(lokasi.equals("P")
                                || lokasi.equals("U")
                                || lokasi.equals("T")
                                || lokasi.equals("S")
                                || lokasi.equals("B"))) {
                            System.out.println("Harap masukkan lokasi pengiriman yang ada pada jangkauan!\n");
                            continue; // ulang dari awal
                        }

                        // jika validasi selesai, break untuk lanjut ke tahap selanjutnya
                        break;
                    }

                    // generate bill berdasarkan user input
                    String bill = generateBill(orderID, lokasi);
                    System.out.println("\n" + bill);

                    break;
                }

                // exit
                case "3": {
                    System.out.println("Terima kasih telah menggunakan DepeFood!");
                    // memberi tahu jika program sudah tidak running
                    running = false;
                    // continue supaya tidak memanggil showPilihMenu
                    continue;
                }

                default: {
                    System.out.println("Harap masukkan opsi yang tersedia!");
                }
            }

            showPilihMenu();
        }

        // close scanner
        input.close();
    }
}
