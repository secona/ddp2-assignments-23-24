package assignments.assignment2;

import java.util.ArrayList;

public class User {
	private String name;
	private String nomorTelepon;
	private String email;
	private String lokasi;
	private String role;
	private ArrayList<Order> orderHistory;

	public String getName() {
		return name;
	}

	public String getNomorTelepon() {
		return nomorTelepon;
	}

	public String getRole() {
		return role;
	}

	public String getLokasi() {
		return lokasi;
	}

	public Order[] getOrderHistory() {
		return orderHistory.toArray(new Order[] {});
	}

	public void addOrder(Order order) {
		orderHistory.add(order);
	}

	public User(String nama, String nomorTelepon, String email, String lokasi, String role) {
		this.name = nama;
		this.nomorTelepon = nomorTelepon;
		this.email = email;
		this.lokasi = lokasi;
		this.role = role;
		this.orderHistory = new ArrayList<Order>();
	}
}
