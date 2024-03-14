package assignments.assignment2;

public class Menu {
	private String namaMakanan;
	private double harga;

	public String getNamaMakanan() {
		return namaMakanan;
	}

	public double getHarga() {
		return harga;
	}

	public Menu(String namaMakanan, double harga) {
		this.namaMakanan = namaMakanan;
		this.harga = harga;
	}
}
