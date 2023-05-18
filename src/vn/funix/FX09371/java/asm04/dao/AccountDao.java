package vn.funix.FX09371.java.asm04.dao;

import vn.funix.FX09371.java.asm04.models.Account;
import vn.funix.FX09371.java.asm04.service.BinaryFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AccountDao {
    private final static String FILE_PATH = "store/accounts.dat";

    public static void save(List<Account> accounts) {
        try {
            BinaryFileService.writeFile(FILE_PATH, accounts);
        } catch (IOException e) {
            System.out.println("Ghi dữ liệu không thành công");
            e.printStackTrace();
        }
    }

    public static List<Account> list() {
        List<Account> accounts = null;
        try {
            accounts = BinaryFileService.readFile(FILE_PATH);
        } catch (IOException e) {
            System.out.println("Đọc dữ liệu không thành công");
            e.printStackTrace();
        }
        return accounts;
    }

    public static void update(Account editAccount) {

        List<Account> accounts = list();
        boolean hasExist = accounts.stream()
                .anyMatch(account ->
                        account.getAccountNumber().equals(editAccount.getAccountNumber()));

        List<Account> updatedAccounts;
        if (!hasExist) {
            updatedAccounts = new ArrayList<>(accounts);
            updatedAccounts.add(editAccount);
        } else {
            updatedAccounts = new Vector<>();

            ExecutorService executorService = Executors.newFixedThreadPool(2);
            for (Account account : accounts) {
                executorService.execute(() -> {
                    if (account.getAccountNumber().equals(editAccount.getAccountNumber())) {
                        updatedAccounts.add(editAccount);
                    } else {
                        updatedAccounts.add(account);
                    }
                });
            }
            executorService.shutdown();
            try {
                executorService.awaitTermination(3000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        save(updatedAccounts);
    }
}