package vn.funix.FX09371.java.asm04.models;

import vn.funix.FX09371.java.asm04.common.Utils;
import vn.funix.FX09371.java.asm04.exception.MethodExitException;

import java.io.Serializable;

public class SavingsAccount extends Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5_000_000.00;

    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    public SavingsAccount(String accountNumber, double balance, String customerId) {
        super(accountNumber, balance, customerId);
    }

    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            createTransaction((amount + getFee(amount)) * (-1), Utils.getDateTime(), true, "WITHDRAW");
            log(amount);
            return true;
        } else {
            System.out.println("Không thể rút số tiền đã nhập");
            createTransaction((amount + getFee(amount)) * (-1), Utils.getDateTime(), false, "WITHDRAW");
            System.out.print("Chọn 1 để nhập lại số tiền, 2 để kết thúc giao dịch: ");
            if (Utils.getInputInt(1, 2) == 2) {
                // Throws exception to get back to main
                throw new MethodExitException();
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean transfer(Account receivedAccount, double amount) {
        if (getBalance() - amount >= 50_000.0) {
            createTransaction((amount + getFee(amount)) * (-1), Utils.getDateTime(), true, "TRANSFER");
            receivedAccount.createTransaction(amount, Utils.getDateTime(), true, "DEPOSIT");
            log(amount, "TRANSFER", receivedAccount.getAccountNumber());
            return true;
        } else {
            System.out.println("Không thể chuyển số tiền đã nhập");
            createTransaction((amount + getFee(amount)) * (-1), Utils.getDateTime(), false, "TRANSFER");
            System.out.print("Chọn 1 để nhập lại số tiền, 2 để kết thúc giao dịch: ");
            if (Utils.getInputInt(1, 2) == 2) {
                // Throws exception to get back to main
                throw new MethodExitException();
            } else {
                return false;
            }
        }
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
