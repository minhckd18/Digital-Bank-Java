package vn.funix.FX09371.java.asm03.models;

import vn.funix.FX09371.java.asm02.models.Account;

public class SavingsAccount extends Account {
    private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5_000_000.00;

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public boolean withdraw(double amount) {
        Transaction transaction = new Transaction(getAccountNumber(),
                (amount + getFee(amount)) * (-1),
                Utils.getDateTime());
        getTransactions().add(transaction);
        if (isAccepted(amount)) {
            log(amount);
            this.setBalance(this.getBalance() - amount - getFee(amount));
            transaction.setStatus(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        if (amount < 50_000) return false;
        if ((this.getBalance() - amount - getFee(amount)) < 50000) return false;
        if (amount % 10_000 != 0) return false;
        if (amount > SAVINGS_ACCOUNT_MAX_WITHDRAW && !isAccountPremium()) return false;
        return true;
    }

    @Override
    public double getFee(double amount) {
        double feeRate = 0;
        double fee = 0;
        return fee;
    }

    @Override
    public String getTitle() {
        return "               BIEN LAI GIAO DICH SAVINGS               ";
    }

    @Override
    public String getType() {
        return "SAVINGS";
    }
}
