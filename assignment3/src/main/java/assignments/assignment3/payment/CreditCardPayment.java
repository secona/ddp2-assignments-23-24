package assignments.assignment3.payment;

public class CreditCardPayment implements DepeFoodPaymentSystem {
    public static final double TRANSACTION_FEE_PERCENTAGE = 0.02;

    public long countTransactionFee(long amount) {
        return (long) (CreditCardPayment.TRANSACTION_FEE_PERCENTAGE * amount);
    }

    @Override
    public long processPayment(long amount) {
        long biayaTransaksi = this.countTransactionFee(amount);
        System.out.printf("Berhasil Membayar Bill sebesar Rp %d dengan biaya transaksi sebesar Rp %d\n",
                amount,
                biayaTransaksi);
        return amount + this.countTransactionFee(amount);
    }

    @Override
    public boolean canPay(long saldo, long amount) {
        if (saldo < amount) {
            System.out.println("Saldo tidak mencukupi mohon menggunakan metode pembayaran yang lain");
            return false;
        }

        return true;
    }
}
