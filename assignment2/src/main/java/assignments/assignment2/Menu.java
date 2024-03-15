package assignments.assignment2;

public class Menu {
	private String namaMakanan;
	private double harga;

	public Menu(String namaMakanan, double harga) {
		this.namaMakanan = namaMakanan;
		this.harga = harga;
	}

	/**
	 * Getter untuk instance variable namaMakanan
	 * @return nama makanan
	 */
	public String getNamaMakanan() {
		return namaMakanan;
	}

	/**
	 * Getter untuk instance variable harga
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
