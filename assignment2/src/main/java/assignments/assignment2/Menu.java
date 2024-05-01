package assignments.assignment2;

public class Menu {
	private String namaMakanan;
	private double harga;

	/**
	 * @param inputs String array berupa input dengan format "{nama} {harga}"
	 * @return Array menu yang masing-masing berisikan nama dan array dari input
	 */
	public static Menu[] parseMakananHarga(String[] inputs) {
		Menu[] result = new Menu[inputs.length];

		for (int i = 0; i < inputs.length; i++) {
			String in = inputs[i];

			// mencari index dari spasi terakhir
			int lastSpaceIndex = in.length();
			while (--lastSpaceIndex > 0) {
				if (in.charAt(lastSpaceIndex) == ' ') {
					break;
				}
			}

			// mengambil nama makanan dan harga
			String namaMakanan = in.substring(0, lastSpaceIndex);
			String harga = in.substring(lastSpaceIndex + 1);

			// cek jika harga tidak valid
			if (!harga.matches("^[0-9]+$")) {
				return null;
			}

			// inisiasi menu dan add ke restoran
			Menu menu = new Menu(namaMakanan, Integer.parseInt(harga));
			result[i] = menu;
		}

		return result;
	}

	/**
	 * @param inputs String array berupa input dengan format "{nama}"
	 * @param restoran instance restoran yang ingin dicari nama makanannya
	 * @return Array Menu dengan nama makanan yang sesuai di restoran
	 */
	public static Menu[] parseMakananRestoran(Restaurant restoran, String[] inputs) {
		Menu[] result = new Menu[inputs.length];

		// Loop untuk setiap input
		for (int i = 0; i < inputs.length; i++) {
			// cari menu dengan nama yang sesuai
			Menu menu = restoran.getOneMenu(inputs[i]);

			if (menu != null) {
				// tambahkan ke array jika ditemukan
				result[i] = menu;
			} else {
				// return null jika ada yang tidak ditemukan
				return null;
			}
		}

		return result;
	}

	public Menu(String namaMakanan, double harga) {
		this.namaMakanan = namaMakanan;
		this.harga = harga;
	}

	/**
	 * Getter untuk instance variable namaMakanan
	 * 
	 * @return nama makanan
	 */
	public String getNamaMakanan() {
		return namaMakanan;
	}

	/**
	 * Getter untuk instance variable harga
	 * 
	 * @return harga
	 */
	public double getHarga() {
		return harga;
	}

	/**
	 * @return "{namaMakanan} {harga}"
	 */
	@Override
	public String toString() {
		return String.format("%s %d", this.namaMakanan, (int) this.harga);
	}
}
