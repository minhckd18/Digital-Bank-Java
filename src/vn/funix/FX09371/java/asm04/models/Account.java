package vn.funix.FX09371.java.asm04.models;

import vn.funix.FX09371.java.asm04.common.Utils;
import vn.funix.FX09371.java.asm04.dao.AccountDao;
import vn.funix.FX09371.java.asm04.dao.CustomerDao;
import vn.funix.FX09371.java.asm04.dao.TransactionDao;
import vn.funix.FX09371.java.asm04.file.IReport;
import vn.funix.FX09371.java.asm04.file.ITransfer;
import vn.funix.FX09371.java.asm04.file.ReportService;
import vn.funix.FX09371.java.asm04.file.Withdraw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements ReportService, IReport, Withdraw, ITransfer, Serializable {
    private static final long serialVersionUID = 1L;
    private String customerId;
    private String accountNumber;
    private double balance;
    private List<Transaction> transactions;

//    public Account() {
//
//    }

    public Account(String accountNumber, double balance) {
        setAccountNumber(accountNumber);
        setBalance(balance);
        transactions = new ArrayList<>();
    }

    public Account(String accountNumber, double balance, String customerId) {
        this(accountNumber, balance);
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<Transaction> getTransactionsFromFile() {
        List<Transaction> transactionList = TransactionDao.list();
        List<Transaction> transactions = transactionList.stream()
                .filter(transaction ->
                        transaction.getAccountNumber().equals(accountNumber))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return transactions;
    }

    public Customer getCustomer() {
        List<Customer> customerList = CustomerDao.list();
        for (Customer customer : customerList) {
            if (customer.getCustomerId().equals(customerId)) return customer;
        }
        return null;
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
        this.balance = balance;
    }

    public boolean isAccountPremium() {
        return this.balance >= 10_000_000;
    }

    @Override
    public String toString() {
        return accountNumber + " | " + getCustomer().getName();
    }

    @Override
    public boolean withdraw(double amount) {
        return false;
    }

    @Override
    public boolean transfer(Account receivedAccount, double amount) {
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

    public void displayTransactionsList() {
        for (Transaction transaction : getTransactionsFromFile()) {
            // Only display success transaction
            if (transaction.getStatus()) {
                System.out.println(transaction);
            }
        }
    }

    public void createTransaction(double amount, String time, boolean status, String type) {
        Transaction transaction = new Transaction(accountNumber, amount, time, type);
        transaction.setStatus(status);
        if (status) {
            this.setBalance(this.getBalance() + amount - getFee(amount));

            // Update account
            AccountDao.update(this);

            // Populate the accounts ArrayList in Customer from previous assignment
            // reset rank and balance, then empty accounts ArrayList before updating customers.dat
            Customer activeCustomer = this.getCustomer();
            List<Account> accountList = activeCustomer.getAccountsFromFile();
            activeCustomer.getAccountArrayList().addAll(accountList);
            activeCustomer.setBalance();
            activeCustomer.setCustomerRank();
            activeCustomer.getAccountArrayList().clear();
            // Update customer
            CustomerDao.update(activeCustomer);
        }

        //Update transaction
        List<Transaction> transactionList = TransactionDao.list();
        transactionList.add(transaction);
        TransactionDao.save(transactionList);
    }

    public static boolean createAccount(String accountNumber, double balance, String customerId) {
        if (balance < 50_000.00) {
            return false;
        }

        Account newAccount = new SavingsAccount(accountNumber, 0, customerId);
        newAccount.createTransaction(balance, Utils.getDateTime(), true, "DEPOSIT");
        return true;
    }

    @Override
    public void log(double amount) {
        System.out.println("+----------+------------------------------+------------+");
        System.out.printf("%s%n", getTitle());
        System.out.printf("NGAY G/D: %46s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %48s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %49s%n", getAccountNumber());
        System.out.printf("SO TIEN: %47s%n", Utils.currencyFormat(amount));
        System.out.printf("SO DU: %49s%n", Utils.currencyFormat(this.getBalance()));
        System.out.printf("PHI + VAT: %45s%n", Utils.currencyFormat(this.getFee(amount)));
        System.out.println("+----------+------------------------------+------------+");
    }

    @Override
    public void log(double amount, String transactionType, String receiveAccountNumber) {
        System.out.println("+----------+------------------------------+------------+");
        System.out.printf("%s%n", getTitle());
        System.out.printf("NGAY G/D: %46s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %48s%n", "DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %49s%n", getAccountNumber());
        System.out.printf("SO TK NHAN: %44s%n", receiveAccountNumber);
        System.out.printf("SO TIEN CHUYEN: %40s%n", Utils.currencyFormat(amount));
        System.out.printf("SO DU: %49s%n", Utils.currencyFormat(this.getBalance()));
        System.out.printf("PHI + VAT: %45s%n", Utils.currencyFormat(this.getFee(amount)));
        System.out.println("+----------+------------------------------+------------+");
    }
}
