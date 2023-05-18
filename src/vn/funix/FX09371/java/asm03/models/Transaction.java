package vn.funix.FX09371.java.asm03.models;

import java.util.UUID;

public class Transaction {
    private String transactionID;
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status = false;

    public Transaction(String accountNumber, double amount, String time) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = time;
        this.transactionID = String.valueOf(UUID.randomUUID());
    }

    public String getTransactionID() {
        return transactionID;
    }

    public double getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }
}
