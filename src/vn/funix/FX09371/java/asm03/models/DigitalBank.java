package vn.funix.FX09371.java.asm03.models;

import vn.funix.FX09371.java.asm02.models.Account;
import vn.funix.FX09371.java.asm02.models.Bank;
import vn.funix.FX09371.java.asm02.models.Customer;

public class DigitalBank extends Bank {
    public Customer getCustomerById(String customerID) {
        for (Customer checkedCustomer : this.getCustomers()) {
            if (checkedCustomer.getCustomerId().equals(customerID)) return checkedCustomer;
        }
        return null;
    }

    public boolean withdraw(String customerID, String accountNumber, double amount) {
        Customer customer = getCustomerById(customerID);
        if (customer != null) {
            if (customer.withdraw(accountNumber, amount)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAccount(String customerID, Account newAccount) {
        Customer customer = getCustomerById(customerID);
        if (customer != null) {
            customer.addAccount(newAccount);
            return true;
        }
        return false;
    }
}
