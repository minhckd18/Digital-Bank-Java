package vn.funix.FX09371.java.asm04.file;

import vn.funix.FX09371.java.asm04.models.Account;

public interface ITransfer {
    boolean transfer(Account receivedAccount, double amount);
}
