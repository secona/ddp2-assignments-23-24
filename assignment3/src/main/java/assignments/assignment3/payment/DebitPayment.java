package assignments.assignment3.payment;

public class DebitPayment implements DepeFoodPaymentSystem {
    public static final int MINIMUM_TOTAL_PRICE = 50000;

    @Override
    public long processPayment(long amount) {
        System.out.printf("Berhasil Membayar Bill sebesar Rp %d\n", amount);
        return amount;
    }

    @Override
    public boolean canPay(long saldo, long amount) {
        if (saldo < amount) {
            System.out.println("Saldo tidak mencukupi mohon menggunakan metode pembayaran yang lain");
            return false;
        }

        if (amount < DebitPayment.MINIMUM_TOTAL_PRICE) {
            System.out.printf("Jumlah pesanan < %d mohon menggunakan metode pembayaran yang lain\n",
                    DebitPayment.MINIMUM_TOTAL_PRICE);
            return false;
        }

        return true;
    }
}
