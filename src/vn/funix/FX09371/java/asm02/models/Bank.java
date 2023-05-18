package vn.funix.FX09371.java.asm02.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bank {
    private String id;
    private List<Customer> customers;

    public Bank() {
        this.customers = new ArrayList<>();
        this.id = String.valueOf(UUID.randomUUID());
    }

    public String getId() {
        return id;
    }

    public void addCustomer(Customer newCustomer) {
        if(!isCustomerIDExisted(newCustomer.getCustomerId())) {
            customers.add(newCustomer);
            System.out.println("Đã thêm khách hàng " + newCustomer.getName() +
                    " có số CCCD là " + newCustomer.getCustomerId() + " vào danh sách");
        } else {
            System.out.println("CCCD đã tồn tại, không thể tạo thêm khách hàng mới");
        }
    }

    public boolean addAccount(String customerID, Account newAccount) {
        if(isCustomerIDExisted(customerID)) {
            for (Customer c : this.getCustomers()) {
                if (c.getCustomerId().equals(customerID)) {
                    c.addAccount(newAccount);
                    return true;
                }
            }
        } else {
            System.out.println("CCCD không tồn tại, hãy thêm khách hàng vào danh sách trước");
        }
        return false;
    }
    public boolean isAccNumExisted(String accountNumber) {
        for(Customer i : customers) {
            for(Account j : i.getAccounts()) {
                if(j.getAccountNumber().equals(accountNumber)) return true;
            }
        }
        return false;
    }

    public boolean validateAccount(String accountNumber) {
        if (accountNumber.length() != 6) return false;
        if (!accountNumber.matches("[0-9]+")) return false;
        return true;
    }

    public boolean isCustomerIDExisted(String customerID) {
        for(Customer i : customers) {
            if(i.getCustomerId().equals(customerID)) return true;
        }
        return false;
    }

    public List<Customer> getCustomers() {
        return this.customers;
    }

}
