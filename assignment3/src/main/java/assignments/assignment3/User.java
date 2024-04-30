package assignments.assignment3;

import java.util.ArrayList;

import assignments.assignment2.Order;
import assignments.assignment3.payment.CreditCardPayment;
import assignments.assignment3.payment.DebitPayment;
import assignments.assignment3.payment.DepeFoodPaymentSystem;

public class User {
    private String name;
    private String nomorTelepon;
    private String email;
    private String lokasi;
    private String role;
    private ArrayList<Order> orderHistory;
    private DepeFoodPaymentSystem payment;
    private long saldo;

    public User(String nama, String nomorTelepon, String email, String lokasi, String role,
            DepeFoodPaymentSystem payment, long saldo) {
        this.name = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
        this.lokasi = lokasi;
        this.role = role;
        this.orderHistory = new ArrayList<Order>();
        this.payment = payment;
        this.saldo = saldo;
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
     * @return saldo
     */
    public long getSaldo() {
        return saldo;
    }

    /**
     * Method untuk membayar sebuah order
     */
    public boolean payOrder(int metode, Order order) {
        if (!this.hasPaymentMethod(metode)) {
            System.out.println("User belum memiliki metode pembayaran ini!");
            return false;
        }

        long amount = order.totalHarga() + order.getOngkir();
        if (this.payment.canPay(this.saldo, amount)) {
            order.updateStatus(true);
            this.saldo -= this.payment.processPayment(amount);
        }

        return false;
    }

    /**
     * Mengecek apakah User memiliki metode pembayaran yang diwakilkan oleh metode
     * berupa integer
     *
     * @return User memiliki metode pembayaran
     */
    public boolean hasPaymentMethod(int metode) {
        return this.payment instanceof CreditCardPayment && metode == 1 ||
                this.payment instanceof DebitPayment && metode == 2;
    }

    /**
     * @return orderHistory dalam bentuk array
     */
    public Order[] getOrderHistory() {
        return orderHistory.toArray(new Order[] {});
    }

    /**
     * Menambahkan order baru
     * 
     * @param order order yang akan ditambah
     */
    public void addOrder(Order order) {
        orderHistory.add(order);
    }
}
