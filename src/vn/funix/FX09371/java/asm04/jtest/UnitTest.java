package vn.funix.FX09371.java.asm04.jtest;

import vn.funix.FX09371.java.asm04.common.Utils;
import vn.funix.FX09371.java.asm04.models.*;

import static org.junit.Assert.*;

public class UnitTest {
    // Test withdraw(), isAccepted() and getFee of LoanAccount
    @org.junit.Test
    public void LoanAccount_withdraw() {
        LoanAccount loanAccount = new LoanAccount("111111");
        loanAccount.setBalance(10_000_000);

        loanAccount.withdraw(9_000_000);
        assertEquals(910_000.0, loanAccount.getBalance(), 0);

        loanAccount.withdraw(60_000.0);
        assertEquals(847_000.0, loanAccount.getBalance(), 0);
    }

    @org.junit.Test
    public void LoanAccount_isAccepted() {
        LoanAccount loanAccount = new LoanAccount("111111");

        loanAccount.setBalance(200_000.0);

        boolean accept = loanAccount.isAccepted(40000.0);
        assertFalse(accept);

        accept = loanAccount.isAccepted(150_000.0);
        assertFalse(accept);

        accept = loanAccount.isAccepted(59_000.0);
        assertFalse(accept);

        accept = loanAccount.isAccepted(60_000.0);
        assertTrue(accept);
    }

    @org.junit.Test
    public void LoanAccount_getFee_Premium() {
        LoanAccount loanAccount = new LoanAccount("111111");

        double fee = loanAccount.getFee(1_000_000);
        assertEquals(10_000.0, fee, 0);
    }

    @org.junit.Test
    public void LoanAccount_getFee_Normal() {
        LoanAccount loanAccount = new LoanAccount("111111");

        loanAccount.setBalance(7_000_000);
        double fee = loanAccount.getFee(1_000_000);
        assertEquals(50_000.0, fee, 0);
    }

    // Test isAccepted() and getFee() of SavingsAccount
    @org.junit.Test
    public void SavingsAccount_isAccepted_Normal() {
        SavingsAccount savingsAccount = new SavingsAccount("111111", 10_000_000);
        boolean accept = savingsAccount.isAccepted(40000.0);
        assertFalse(accept);

        savingsAccount.setBalance(7_000_000); // Set account to Normal rank
        accept = savingsAccount.isAccepted(6_000_000.0);
        assertFalse(accept);

        accept = savingsAccount.isAccepted(59_000.0);
        assertFalse(accept);

        accept = savingsAccount.isAccepted(60_000.0);
        assertTrue(accept);
    }

    @org.junit.Test
    public void SavingsAccount_isAccepted_Premium() {
        SavingsAccount savingsAccount = new SavingsAccount("111111", 10_000_000);
        boolean accept = savingsAccount.isAccepted(6_000_000);
        assertTrue(accept);
    }

    @org.junit.Test
    public void SavingsAccount_getFee() {
        SavingsAccount savingsAccount = new SavingsAccount("111111", 10_000_000);
        double fee = savingsAccount.getFee(1_000_000);
        assertEquals(0, fee, 0);
    }

    // Test validateAccount from Utils.java
    @org.junit.Test
    public void validateAccount() {

        boolean test = Utils.validateAccount("123456789");
        assertFalse(test);

        test = Utils.validateAccount("abcxyz");
        assertFalse(test);

        test = Utils.validateAccount("123#$a");
        assertFalse(test);

        test = Utils.validateAccount("123456");
        assertTrue(test);
    }

    // Test isAccountPremium from Account.java
    @org.junit.Test
    public void isAccountPremium() {
        Account testAccount = new Account("111111", 100_000);
        boolean test = testAccount.isAccountPremium();
        assertFalse(test);

        testAccount.setBalance(10_000_000);
        test = testAccount.isAccountPremium();
        assertTrue(test);
    }

    // Test getCustomerByID from DigitalBank.java
    @org.junit.Test
    public void getCustomerById() {
        DigitalBank testBank = new DigitalBank();
        testBank.addCustomer(new Customer("minh", "054095006539"));
        Customer testCustomer = testBank.getCustomerById("054095006539");
        assertNotNull(testCustomer);

        testCustomer = testBank.getCustomerById("054095006538");
        assertNull(testCustomer);
    }

    // Test getBalance() from Customer.java
    @org.junit.Test
    public void Customer_getBalance() {
        Customer testCustomer = new Customer("minh", "054095006539");
        testCustomer.addAccount(new Account("111111", 6_000_000));
        testCustomer.addAccount(new Account("222222", 10_000_000));
        double testBalance = testCustomer.getBalance();
        assertEquals(16_000_000.0, testBalance, 0);
    }

    // Test isCustomerPremium from Customer.java
    @org.junit.Test
    public void isCustomerPremium() {
        Customer testCustomer = new Customer("minh", "054095006539");
        testCustomer.addAccount(new Account("111111", 6_000_000));
        assertFalse(testCustomer.isCustomerPremium());

        testCustomer.addAccount(new Account("222222", 10_000_000));
        assertTrue(testCustomer.isCustomerPremium());
    }

    // Test getAccount from Customer.java
    @org.junit.Test
    public void getAccount() {
        Customer testCustomer = new Customer("minh", "054095006539");
        testCustomer.addAccount(new Account("111111", 6_000_000));

        assertNull(testCustomer.getAccount("123456"));

        assertNotNull(testCustomer.getAccount("111111"));
    }

    // Test validateCustomerId from User.java
    @org.junit.Test
    public void validateCustomerId() {
        // Create testCustomer to access validateCustomerId
        Customer testCustomer = new Customer("minh", "054095006539");

        // Wrong length
        boolean test = testCustomer.validateCustomerId("054095");
        assertFalse(test);

        // Not all digits
        test = testCustomer.validateCustomerId("0540950065as");
        assertFalse(test);

        // Wrong province code
        test = testCustomer.validateCustomerId("003095006539");
        assertFalse(test);
    }
}
