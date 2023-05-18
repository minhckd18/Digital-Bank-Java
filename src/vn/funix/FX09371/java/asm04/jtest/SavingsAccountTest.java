package vn.funix.FX09371.java.asm04.jtest;

import org.junit.Test;
import vn.funix.FX09371.java.asm04.dao.CustomerDao;
import vn.funix.FX09371.java.asm04.models.Account;
import vn.funix.FX09371.java.asm04.models.Customer;
import vn.funix.FX09371.java.asm04.models.DigitalBank;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

public class SavingsAccountTest {

    @org.junit.Test
    public void SavingsAccount_withdraw() {
        System.out.println("********************************");
        System.out.println("Start of SavingsAccount_withdraw");
        System.out.println("********************************");

        DigitalBank testBank = new DigitalBank();
        try {
            testBank.addCustomers("store/testCustomers.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Account.createAccount("123456", 10_000_000, "054095006521");

        List<Customer> customerList = CustomerDao.list();
        Customer customer = testBank.getCustomerById(customerList, "054095006521");
        List<Account> accountList = customer.getAccountsFromFile();
        Account savingsAccount = customer.getAccountByAccountNumber(accountList, "123456");

        customer.displayTransactionInformation();

        savingsAccount.withdraw(1_000_000);
        assertEquals(9_000_000.0, savingsAccount.getBalance(), 0);

        // get new data from customers.dat to fetch into customer object
        customerList = CustomerDao.list();
        customer = testBank.getCustomerById(customerList, "054095006521");
        customer.displayTransactionInformation();

        System.out.println("********************************");
        System.out.println("End of SavingsAccount_withdraw");
        System.out.println("********************************");
    }

    @org.junit.Test
    public void SavingsAccount_transfer() {
        System.out.println("********************************");
        System.out.println("Start of SavingsAccount_transfer");
        System.out.println("********************************");

        DigitalBank testBank = new DigitalBank();

        // Sharing the testCustomer.txt with the SavingsAccount_withdraw test
        try {
            testBank.addCustomers("store/testCustomers.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Use a different customer
        Account.createAccount("123457", 10_000_000, "054095006522");
        Account.createAccount("123458", 10_000_000, "054095006522");

        List<Customer> customerList = CustomerDao.list();
        Customer customer = testBank.getCustomerById(customerList, "054095006522");
        List<Account> accountList = customer.getAccountsFromFile();
        Account sendAccount = customer.getAccountByAccountNumber(accountList, "123457");
        Account receiveAccount = customer.getAccountByAccountNumber(accountList, "123458");

        customer.displayTransactionInformation();

        // Transfer between 2 accounts of the same customer
        sendAccount.transfer(receiveAccount,1_000_000);
        assertEquals(9_000_000, sendAccount.getBalance(), 0);
        assertEquals(11_000_000, receiveAccount.getBalance(), 0);

        // get new data from customers.dat to fetch into customer object
        customerList = CustomerDao.list();
        customer = testBank.getCustomerById(customerList, "054095006522");
        customer.displayTransactionInformation();

        System.out.println("********************************");
        System.out.println("End of SavingsAccount_transfer");
        System.out.println("********************************");
    }
}