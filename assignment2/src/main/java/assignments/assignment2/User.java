package assignments.assignment2;

import java.util.ArrayList;

public class User {
	private String name;
	private String nomorTelepon;
	private String email;
	private String lokasi;
	private String role;
	private ArrayList<Order> orderHistory;

	public User(String nama, String nomorTelepon, String email, String lokasi, String role) {
		this.name = nama;
		this.nomorTelepon = nomorTelepon;
		this.email = email;
		this.lokasi = lokasi;
		this.role = role;
		this.orderHistory = new ArrayList<Order>();
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return nomorTelepon
	 */
	public String getNomorTelepon() {
		return nomorTelepon;
	}

	/**
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @return lokasi
	 */
	public String getLokasi() {
		return lokasi;
	}

	/**
	 * @return orderHistory dalam bentuk array
	 */
	public Order[] getOrderHistory() {
		return orderHistory.toArray(new Order[] {});
	}

	/**
	 * Menambahkan order baru
	 * @param order order yang akan ditambah
	 */
	public void addOrder(Order order) {
		orderHistory.add(order);
	}
}
