package vn.funix.FX09371.java.asm02.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {

    @org.junit.Test
    public void validateAccount() {
        Bank testBank = new Bank();

        boolean test = testBank.validateAccount("123456789");
        assertFalse(test);

        test = testBank.validateAccount("abcxyz");
        assertFalse(test);

        test = testBank.validateAccount("123#$a");
        assertFalse(test);

        test = testBank.validateAccount("123456");
        assertTrue(test);
    }
}