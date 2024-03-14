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
	 * Method ini digunakan untuk menambahkan satu menu ke restoran
	 */
	public void addMenu(Menu menu) {
		this.menu.add(menu);
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
