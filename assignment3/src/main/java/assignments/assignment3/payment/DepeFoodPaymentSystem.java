package assignments.assignment3.payment;

public interface DepeFoodPaymentSystem {
    public long processPayment(long saldo, long amount) throws Exception;
    public boolean canPay(long saldo, long amount);
}
