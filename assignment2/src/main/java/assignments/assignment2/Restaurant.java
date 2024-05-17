package assignments.assignment2;

import java.util.ArrayList;

public class Restaurant {
	private String nama;
	private ArrayList<Menu> menu;
	private long saldo;

	public Restaurant(String nama) {
		this.nama = nama;
		this.menu = new ArrayList<Menu>();
		this.saldo = 0;
	}

	/**
	 * Getter untuk instance variable nama.
	 * 
	 * @return nama
	 */
	public String getNama() {
		return nama;
	}

	/**
	 * Membandingkan nama dari restaurant.
	 * 
	 * @param nama Nama yang dibandingkan
	 * @return Menyatakan apakah namanya sama
	 */
	public boolean isName(String nama) {
		return this.nama.equalsIgnoreCase(nama);
	}

	/**
	 * Method ini digunakan untuk menambahkan satu menu ke restoran
	 * 
	 * @param newMenu menu yang akan ditambahkan
	 */
	public void addMenu(Menu newMenu) {
		int i = 0;

		// loop untuk mencari posisi untuk menu baru
		for (; i < this.menu.size(); i++) {
			Menu curr = this.menu.get(i);

			// jika harga keduanya sama, kita bandingkan nama makanannya berdasarkan alfabet
			if (newMenu.getHarga() == curr.getHarga()) {
				// jika nama makanan menu baru lebih duluan berdasarkan alfabet, letak menu baru
				// seharusnya index ini
				if (curr.getNamaMakanan().compareTo(newMenu.getNamaMakanan()) >= 0) {
					break;
				}
			}

			// jika harga menu baru lebih murah, nama makanannya lebih akhir dari pada nama
			// makanan lain dengan harga yang sama. artinya posisi menu baru seharusnya
			// index ini
			if (newMenu.getHarga() < curr.getHarga()) {
				break;
			}
		}

		this.menu.add(i, newMenu);
	}

	/**
	 * Menambahkan array menu ke instance variable menu
	 * 
	 * @param menus array menu yang akan ditambah
	 */
	public void addMenu(Menu[] menus) {
		for (Menu menu : menus) {
			this.addMenu(menu);
		}
	}

	/**
	 * Method ini digunakan untuk mendapatkan banuyaknya menu
	 * 
	 * @return panjang dari menu
	 */
	public int menuLength() {
		return menu.size();
	}

	/**
	 * Menambahkan saldo dari restaurant
	 *
	 * @return saldo restaurant yang baru
	 */
	public long addSaldo(long amount) {
		this.saldo += amount;
		return this.saldo;
	}

	/**
	 * Method ini digunakan untuk mencari menu dalam restoran berdasarkan nama
	 * makanan
	 * 
	 * @param namaMakanan nama dari menu makanan yang dicari
	 * @return menu atau null
	 */
	public Menu getOneMenu(String namaMakanan) {
		for (Menu menu : this.menu) {
			if (menu.getNamaMakanan().equalsIgnoreCase(namaMakanan)) {
				return menu;
			}
		}
		return null;
	}

	/**
	 * Method ini digunakan untuk mencari menu dalam restoran berdasarkan index
	 * 
	 * @param index index dari menu yang dicari
	 * @return menu atau null
	 */
	public Menu getOneMenu(int index) {
		return menu.get(index);
	}

	/**
	 * Method ini digunakan untuk print semua menu yang ada di restoran
	 */
	public void printMenu() {
		System.out.println("Menu:");
		for (int i = 0; i < this.menu.size(); i++) {
			Menu menu = this.menu.get(i);
			System.out.printf("%d. %s\n", i + 1, menu.toString());
		}
	}

	// Method untuk DepeFood
	public ArrayList<Menu> getMenu() {
		return this.menu;
	}
}
