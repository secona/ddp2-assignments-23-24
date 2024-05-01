package assignments.assignment2;

public class Order {
	private String orderID;
	private String tanggalPemesanan;
	private int biayaOngkosKirim;
	private Restaurant restaurant;
	private Menu[] items;
	private boolean orderFinished;

	public Order(
			String orderID,
			String tanggalPemesanan,
			int biayaOngkosKirim,
			Restaurant restaurant,
			Menu[] items) {
		this.orderID = orderID;
		this.tanggalPemesanan = tanggalPemesanan;
		this.biayaOngkosKirim = biayaOngkosKirim;
		this.restaurant = restaurant;
		this.items = items;
		this.orderFinished = false;
	}

	/**
	 * Static method ini mengeluarkan ongkos kirim berdasarkan lokasi user
	 * 
	 * @param lokasi lokasi dari user
	 * @return ongkos kirim
	 */
	public static int calculateOngkosKirim(String lokasi) {
		switch (lokasi) {
			case "P":
			case "p":
				return 10000;
			case "U":
			case "u":
				return 20000;
			case "T":
			case "t":
				return 35000;
			case "S":
			case "s":
				return 40000;
			case "B":
			case "b":
				return 60000;
			default:
				return 0;
		}
	}

	/**
	 * Getter untuk instance variable orderID.
	 * 
	 * @return orderID
	 */
	public String getOrderID() {
		return orderID;
	}

	/**
	 * Getter untuk restaurant
	 *
	 * @return restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * Getter untuk instance variable orderFinished
	 * 
	 * @return orderFinished
	 */
	public boolean isOrderFinished() {
		return orderFinished;
	}

	/**
	 * Membandingkan orderID
	 * 
	 * @param orderID orderID yang dibandingkan
	 * @return Menyatakan apakah orderID-nya sama
	 */
	public boolean isOrderID(String orderID) {
		return this.orderID.equalsIgnoreCase(orderID);
	}

	/**
	 * Getter untuk instance variable biayaOngkosKirim
	 *
	 * @return biayaOngkosKirim
	 */
	public int getOngkir() {
		return this.biayaOngkosKirim;
	}

	/**
	 * Method ini digunakan untuk meng-update status dari order.
	 * 
	 * @param finished Status yang baru
	 * @return Menyatakan update-nya berhasil atau tidak
	 */
	public boolean updateStatus(boolean finished) {
		if (orderFinished == finished) {
			return false;
		} else {
			orderFinished = finished;
			return true;
		}
	}

	/**
	 * Method ini mengubah items yang dibeli menjadi string berupa daftar dengan
	 * prefix "- ".
	 * 
	 * @return Daftar items dengan prefix "- "
	 */
	public String getPesananString() {
		String result = "";

		// loop sampai sebelum elemen terakhir
		for (int i = 0; i < items.length - 1; i++) {
			result += "- " + items[i].toString() + "\n";
		}

		// menambahkan elemen terakhir
		result += "- " + items[items.length - 1].toString();

		return result;
	}

	/**
	 * Method ini menghitung total harga dari semua item.
	 * 
	 * @return Total harga dari semua item
	 */
	public int calculateTotalItem() {
		int total = 0;

		// loop setiap harga dan jumlahkan ke total
		for (Menu item : items) {
			total += item.getHarga();
		}

		return total;
	}

	/**
	 * Method ini menghitung keseluruhan harga yang harus dibayar
	 *
	 * @return Total harga dari item + ongkir
	 */
	public int calculateTotalHarga() {
		return this.calculateTotalItem() + this.biayaOngkosKirim;
	}

	/**
	 * Method ini mengubah order menjadi string dengan data lokasi user
	 * 
	 * @param lokasiUser lokasi dari logged in user
	 * @return Representasi string dari order dengan lokasi user
	 */
	public String toString(String lokasiUser) {
		int totalBiaya = this.calculateTotalHarga();

		return "Bill:" +
				"\nOrder ID: " + this.orderID +
				"\nTanggal Pemesanan: " + this.tanggalPemesanan +
				"\nRestaurant: " + this.restaurant.getNama() +
				"\nLokasi Pengiriman: " + lokasiUser +
				"\nStatus Pengiriman: " + (this.orderFinished ? "Finished" : "Not Finished") +
				"\nPesanan:\n" + this.getPesananString() +
				"\nBiaya Ongkos Kirim: Rp " + this.biayaOngkosKirim +
				"\nTotal Biaya: Rp " + totalBiaya;
	}
}
