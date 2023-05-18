package vn.funix.FX09371.java.asm04.dao;

import vn.funix.FX09371.java.asm04.models.Transaction;
import vn.funix.FX09371.java.asm04.service.BinaryFileService;

import java.io.IOException;
import java.util.List;

public class TransactionDao {
    private final static String FILE_PATH = "store/transactions.dat";
    public static void save(List<Transaction> transactions) {
        try {
            BinaryFileService.writeFile(FILE_PATH, transactions);
        } catch (IOException e) {
            System.out.println("Ghi dữ liệu không thành công");
            e.printStackTrace();
        }
    }

    public static List<Transaction> list() {
        List<Transaction> transactions = null;
        try {
            transactions = BinaryFileService.readFile(FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
