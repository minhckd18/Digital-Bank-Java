package vn.funix.FX09371.java.asm04.models;

import vn.funix.FX09371.java.asm04.common.Utils;

public class LoanAccount extends Account {
    private static final double LOAN_ACCOUNT_MAX_BALANCE = 100_000_000.00;
    private static final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    private static final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;

    public LoanAccount(String accountNumber) {
        super(accountNumber, LOAN_ACCOUNT_MAX_BALANCE);
    }

    @Override
    public boolean withdraw(double amount) {
        Transaction transaction = new Transaction(getAccountNumber(),
                (amount + getFee(amount)) * (-1),
                Utils.getDateTime(), "WITHDRAW");
//        getTransactions().add(transaction);
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
        return true;
    }

    @Override
    public double getFee(double amount) {
        double feeRate = (isAccountPremium()) ? LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE : LOAN_ACCOUNT_WITHDRAW_FEE;
        double fee = amount * feeRate;
        return fee;
    }

    @Override
    public String getTitle() {
        return "               BIEN LAI GIAO DICH LOAN                  ";
    }

    @Override
    public String getType() {
        return "LOAN";
    }
}
