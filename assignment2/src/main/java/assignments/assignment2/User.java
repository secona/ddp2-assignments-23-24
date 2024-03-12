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

	public void setName(String name) {
		this.name = name;
	}

	public String getNomorTelepon() {
		return nomorTelepon;
	}

	public void setNomorTelepon(String nomorTelepon) {
		this.nomorTelepon = nomorTelepon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLokasi() {
		return lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ArrayList<Order> getOrderHistory() {
		return orderHistory;
	}

	public void setOrderHistory(ArrayList<Order> orderHistory) {
		this.orderHistory = orderHistory;
	}

    public User(String nama, String nomorTelepon, String email, String lokasi, String role){
        // TODO: buat constructor untuk class ini
    }

    // TODO: tambahkan methods yang diperlukan untuk class ini
}
