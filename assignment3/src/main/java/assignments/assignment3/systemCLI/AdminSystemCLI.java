package assignments.assignment3.systemCLI;

import assignments.assignment2.Menu;
import assignments.assignment2.Restaurant;
import assignments.assignment3.MainMenu;

public class AdminSystemCLI extends UserSystemCLI {
    public boolean handleMenu(int command) {
        switch (command) {
            case 1 -> handleTambahRestoran();
            case 2 -> handleHapusRestoran();
            case 3 -> {
                return false;
            }
            default -> System.out.println("Perintah tidak diketahui, silakan coba kembali");
        }
        return true;
    }

    public void displayMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("Pilih menu:");
        System.out.println("1. Tambah Restoran");
        System.out.println("2. Hapus Restoran");
        System.out.println("3. Keluar");
        System.out.println("--------------------------------------------");
        System.out.print("Pilihan menu: ");
    }

    private void handleTambahRestoran() {
        System.out.println("--------------Tambah Restoran----------------");

        while (true) {
            // ambil input nama
            System.out.print("Nama: ");
            String namaRestoran = super.input.nextLine();

            if (namaRestoran.length() < 4) {
                System.out.println("Nama Restoran tidak valid!\n");
                continue;
            }

            // cek jika nama restoran sudah diambil
            Restaurant restaurant = MainMenu.findRestaurant(namaRestoran);
            if (restaurant != null) {
                System.out.println("Restoran dengan nama " + namaRestoran
                        + " sudah pernah terdaftar. Mohon masukkan nama yang berbeda!\n");
                continue;
            }

            // ambil input jumlah makanan
            System.out.print("Jumlah Makanan: ");
            int jumlahMakanan = super.input.nextInt();
            super.input.nextLine();

            // ambil input setiap menu
            String[] inputs = new String[jumlahMakanan];
            for (int i = 0; i < jumlahMakanan; i++) {
                inputs[i] = super.input.nextLine();
            }

            // mengubah input strings menjadi Menu object
            Menu[] menus = Menu.fromInputStrings(inputs);

            // jika error
            if (menus == null) {
                System.out.println("Harga menu harus bilangan bulat!\n");
                continue;
            }

            // inisiasi restoran
            restaurant = new Restaurant(namaRestoran);
            restaurant.addMenu(menus);
            MainMenu.restoList.add(restaurant);

            // print success message
            System.out.printf("Restaurant %s Berhasil terdaftar.\n", namaRestoran);

            break;
        }
    }

    private void handleHapusRestoran() {
        System.out.println("--------------Hapus Restoran----------------");

        while (true) {
            // ambil input nama restoran
            System.out.print("Nama Restoran: ");
            String namaRestoran = super.input.nextLine();

            // cari restoran dengan nama yang sesuai
            Restaurant restoran = MainMenu.findRestaurant(namaRestoran);
            if (restoran != null) {
                MainMenu.restoList.remove(restoran);
                System.out.println("Restoran berhasil dihapus.");
                return;
            }

            // jika tidak ditemukan restoran dengan nama yang sama
            System.out.println("Restoran tidak terdaftar pada sistem.\n");
        }
    }
}
