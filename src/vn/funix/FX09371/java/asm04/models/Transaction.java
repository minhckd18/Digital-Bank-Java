package vn.funix.FX09371.java.asm04.models;

import vn.funix.FX09371.java.asm04.common.Utils;

import java.io.Serializable;
import java.util.UUID;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;
    private String type = "DEPOSIT";
    private String transactionId;
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status = false;

    public Transaction(String accountNumber, double amount, String time, String type) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = time;
        this.type = type;
        this.transactionId = String.valueOf(UUID.randomUUID());
    }

//    public String getTransactionId() {
//        return transactionId;
//    }

    public String getAccountNumber() {
        return accountNumber;
    }

//    public double getAmount() {
//        return amount;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public String getTime() {
//        return time;
//    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return String.format("[GD]%8s | %-10s | %21s | %17s",
                accountNumber,
                type,
                Utils.currencyFormat(amount),
                time);
    }
}
