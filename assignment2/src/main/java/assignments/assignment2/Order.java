package assignments.assignment2;

import java.util.ArrayList;

import assignments.assignment1.OrderGenerator;

public class Order {
	private String orderID;
	private String tanggalPemesanan;
	private int biayaOngkosKirim;
	private Restaurant restaurant;
	private ArrayList<Menu> items;
	private boolean orderFinished;

	public String getOrderID() {
		return orderID;
	}

	public boolean isOrderFinished() {
		return orderFinished;
	}

	public void setOrderFinished(boolean orderFinished) {
		this.orderFinished = orderFinished;
	}

	public boolean isOrderID(String orderID) {
		return this.orderID.equalsIgnoreCase(orderID);
	}

	public Order(
			String orderID,
			String tanggalPemesanan,
			int biayaOngkosKirim,
			Restaurant restaurant,
			ArrayList<Menu> items) {
		this.orderID = orderID;
		this.tanggalPemesanan = tanggalPemesanan;
		this.biayaOngkosKirim = biayaOngkosKirim;
		this.restaurant = restaurant;
		this.items = items;
		this.orderFinished = false;
	}

	public String getPesananString() {
		String result = "";

		// loop sampai sebelum elemen terakhir
		for (int i = 0; i < items.size() - 1; i++) {
			result += "- " + items.get(i).toString() + "\n";
		}

		// menambahkan elemen terakhir
		result += "- " + items.getLast().toString();

		return result;
	}

	public int totalHarga() {
		int total = 0;

		// loop setiap harga dan jumlahkan ke total
		for (Menu item : items) {
			total += item.getHarga();
		}

		return total;
	}

	public String toString(String lokasiUser) {
		return "Bill:" +
				"\nOrder ID: " + this.orderID +
				"\nTanggal Pemesanan: " + this.tanggalPemesanan +
				"\nRestaurant: " + this.restaurant.getNama() +
				"\nLokasi Pengiriman: " + lokasiUser +
				"\nStatus Pengiriman: " + (this.orderFinished ? "Finished" : "Not Finished") +
				"\nPesanan:\n" + this.getPesananString() +
				"\nBiaya Ongkos Kirim: " + OrderGenerator.getOngkir(lokasiUser) +
				"\nTotal Biaya: " + "TOTAL BIAYA";
	}
}
