package assignments.assignment3.payment;

public interface DepeFoodPaymentSystem {
    public long processPayment(long amount);
    public boolean canPay(long saldo, long amount);
}
