package vn.funix.FX09371.java.asm04.models;

import vn.funix.FX09371.java.asm04.common.Utils;
import vn.funix.FX09371.java.asm04.dao.AccountDao;
import vn.funix.FX09371.java.asm04.exception.MethodExitException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User implements Serializable {
    private double balance = 0;
    private String customerRank = "Normal";
    private static final long serialVersionUID = 1L;
    private List<Account> accounts;

    public Customer(String name, String CustomerID) {
        super(name, CustomerID);
        accounts = new ArrayList<>();
    }

    public Customer(List<String> values) {
        this(values.get(1), values.get(0));
    }

    // This is getAccounts required by asm4
    // But I name it getAccountsFromFile for better clarity
    public List<Account> getAccountsFromFile() {
        List<Account> accountList = AccountDao.list();
        List<Account> accounts = accountList.stream()
                .filter(account ->
                        account.getCustomerId().equals(this.getCustomerId()))
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        return accounts;
    }

    public List<Account> getAccountArrayList() {
        return accounts;
    }

    public Account getAccount(String accountNumber) {
        for (Account account : this.getAccountArrayList()) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public Account getAccountByAccountNumber(List<Account> accounts, String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void addAccount(Account newAccount) {
        accounts.add(newAccount);
        setBalance();
    }

    public boolean isCustomerPremium() {
        for (Account i : accounts) {
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
        for (Account i : accounts) {
            balance += i.getBalance();
        }
    }

    public void setBalance(List<Account> accountList) {
        balance = 0;
        for (Account i : accountList) {
            balance += i.getBalance();
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public void displayInformation() {
        System.out.printf("%12s | %-34s | %8s | %19s%n", this.getCustomerId(), this.getName(),
                this.getCustomerRank(), Utils.currencyFormat(this.getBalance()));
        List<Account> accountList = getAccountsFromFile();
        int i = 1;
        for (Account account : accountList) {
            if (account.getCustomerId().equals(this.getCustomerId())) {
                System.out.printf("%2d%10s | %-34s | %30s%n", i++, account.getAccountNumber(),
                        account.getType(), Utils.currencyFormat(account.getBalance()));
            }
        }
    }

    public void displayTransactionInformation() {
        displayInformation();
        List<Account> accountList = getAccountsFromFile();
        for (Account account : accountList) {
            account.displayTransactionsList();
        }
    }

    public boolean withdraw(Scanner scanner, String accountNumber) throws MethodExitException {
        // Check if accountNumber exists
        List<Account> accountList = getAccountsFromFile();
        Account account = getAccountByAccountNumber(accountList, accountNumber);

        if (account == null) {
            System.out.println("Khách hàng không có số tài khoản này");
            return false;
        }

        // Get withdraw amount
        while (true) {
            System.out.print("Nhập số tiền cần rút: ");
            double amount = Utils.getInputDouble(scanner);

            if (account.withdraw(amount)) {
                return true;
            }
        }
    }

    public boolean transfer(Scanner scanner, String accountNumber) throws MethodExitException {
        // Check if accountNumber exists
        List<Account> accountList = getAccountsFromFile();
        Account account = getAccountByAccountNumber(accountList, accountNumber);

        if (account == null) {
            System.out.println("Khách hàng không có số tài khoản này");
            return false;
        }

        while (true) {
            System.out.print("Nhập mã STK nhận (exit để thoát): ");
            String receivedAccountNumber = scanner.nextLine();

            if (receivedAccountNumber.equals("exit")) {
                throw new MethodExitException();
            }

            if (!Utils.validateAccount(receivedAccountNumber)) {
                System.out.println("STK không đúng, hãy chọn lại số khác");
                continue;
            }

            if (receivedAccountNumber.equals(accountNumber)) {
                System.out.println("STK nhận không thể trùng STK gửi");
                continue;
            }

            List<Account> allAccountList = AccountDao.list();
            Account receivedAccount = getAccountByAccountNumber(allAccountList, receivedAccountNumber);

            if (receivedAccount == null) {
                System.out.println("STK không tồn tại");
                continue;
            }

            System.out.println("Chuyển tiền đến tài khoản: " + receivedAccount);
            System.out.print("Nhập số tiền cần chuyển: ");
            double amount = Utils.getInputDouble(scanner);

            if (account.transfer(receivedAccount, amount)) {
                return true;
            }
        }
    }

    public boolean addSavingAccount(Scanner scanner, String accountNumber) {
        // Check if accountNumber exists
        List<Account> accountList = AccountDao.list();
        Account account = getAccountByAccountNumber(accountList, accountNumber);

        if (account != null) {
            System.out.println("STK đã tồn tại, hãy chọn lại số khác");
            return false;
        }

        // Get initial balance
        while (true) {
            System.out.print("Nhập số dư: ");
            double balance = Utils.getInputDouble(scanner);

            if (Account.createAccount(accountNumber, balance, getCustomerId())) {
                return true;
            } else {
                System.out.println("Số dư phải lớn hơn 50000 vnd");
            }
        }
    }

    @Override
    public String toString() {
        return getName() + " | " + getCustomerId();
    }
}
