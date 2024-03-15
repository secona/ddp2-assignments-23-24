package assignments.assignment2;

import java.util.ArrayList;

public class Restaurant {
	private String nama;
	private ArrayList<Menu> menu;

	public String getNama() {
		return nama;
	}

	public Restaurant(String nama) {
		this.nama = nama;
		this.menu = new ArrayList<Menu>();
	}

	/**
	 * Method ini digunakan untuk menentukan kesamaan nama restoran
	 */
	public boolean isName(String nama) {
		return this.nama.equalsIgnoreCase(nama);
	}

	/**
	 * Method ini digunakan untuk menambahkan satu menu ke restoran
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
	 * Method ini digunakan untuk mendapatkan banuyaknya menu
	 */
	public int menuLength() {
		return menu.size();
	}

	/**
	 * Method ini digunakan untuk mencari menu dalam restoran berdasarkan nama
	 * makanan
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
	 */
	public Menu getOneMenu(int index) {
		return menu.get(index);
	}
}
