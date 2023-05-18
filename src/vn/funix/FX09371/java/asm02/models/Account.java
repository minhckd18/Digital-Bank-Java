package vn.funix.FX09371.java.asm02.models;

import vn.funix.FX09371.java.asm03.models.ReportService;
import vn.funix.FX09371.java.asm03.models.Transaction;
import vn.funix.FX09371.java.asm03.models.Utils;
import vn.funix.FX09371.java.asm03.models.Withdraw;

import java.util.ArrayList;
import java.util.List;

public class Account implements ReportService, Withdraw {
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions;

    public Account() {

    }

    public Account(String accountNumber, double balance) {
        setAccountNumber(accountNumber);
        setBalance(balance);
        transactions = new ArrayList<>();

        // Add initial deposit as first transaction
        Transaction initialDeposit = new Transaction(getAccountNumber(), balance, Utils.getDateTime());
        getTransactions().add(initialDeposit);
        initialDeposit.setStatus(true);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        while (true) {
            if (balance < 50_000) {
                System.out.println("Số dư ban đầu phải lớn hơn 50,000đ");
                System.out.print("Vui lòng nhập lại: ");
                balance = Utils.getInputDouble();
                continue;
            }
            break;
        }
        this.balance = balance;
    }

    public boolean isAccountPremium() {
        return this.balance >= 10_000_000;
    }

    @Override
    public String toString() {
        return accountNumber + " | " + balance;
    }

    @Override
    public boolean withdraw(double amount) {
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        return false;
    }

    public String getType() {
        return "";
    }

    public String getTitle() {
        return "";
    }

    public double getFee(double amount) {
        return 0.0;
    }

    public void displayTransactions() {
        for (Transaction transaction : transactions) {
            // Only display success transaction
            if (transaction.getStatus()) {
                System.out.printf("[GD]%8s | %19s | %17s%n",
                        accountNumber,
                        Utils.currencyFormat(transaction.getAmount()),
                        transaction.getTime());
            }
        }
    }

    @Override
    public void log(double amount) {
        System.out.println("+----------+------------------------------+------------+");
        System.out.printf("%s%n", getTitle());
        System.out.printf("NGAY G/D: %46s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %48s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %49s%n", getAccountNumber());
        System.out.printf("SO TIEN: %47s%n", Utils.currencyFormat(amount));
        System.out.printf("SO DU: %49s%n", Utils.currencyFormat(this.getBalance() - amount - this.getFee(amount)));
        System.out.printf("PHI + VAT: %45s%n", Utils.currencyFormat(this.getFee(amount)));
        System.out.println("+----------+------------------------------+------------+");
    }
}
