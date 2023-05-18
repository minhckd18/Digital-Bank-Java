package vn.funix.FX09371.java.asm02.models;

import vn.funix.FX09371.java.asm03.models.Utils;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private double balance = 0;
    private String customerRank = "Normal";
    private List<Account> accounts;

    public Customer(String name, String CustomerID) {
        super(name, CustomerID);
        accounts = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }

    public Account getAccount(String accountNumber) {
        for(Account account : this.getAccounts()) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void addAccount(Account newAccount) {
        accounts.add(newAccount);
        if (newAccount.getBalance() >= 10_000_000) {
            setCustomerRank();
        }
    }

    public boolean isCustomerPremium() {
        for(Account i : accounts) {
            if (i.isAccountPremium()) return true;
        }
        return false;
    }

    public void setCustomerRank() {
        this.customerRank = (this.isCustomerPremium()) ? "Premium" : "Normal";
    }
    public String getCustomerRank() {
        return this.customerRank;
    }

    public void setBalance() {
        balance = 0;
        for(Account i : accounts) {
            balance += i.getBalance();
        }
    }

    public double getBalance() {
        setBalance();
        return this.balance;
    }

    public void displayInformation() {
        System.out.printf("%12s | %8s | %8s | %19s%n", this.getCustomerId(), this.getName(),
                this.getCustomerRank(), Utils.currencyFormat(this.getBalance()));
    }

    public void displayTransactions() {
    }

    public boolean withdraw(String accountNumber, double amount) {
        return false;
    }
}
