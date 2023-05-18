package vn.funix.FX09371.java.asm04.jtest;

import org.junit.Test;
import vn.funix.FX09371.java.asm04.dao.CustomerDao;
import vn.funix.FX09371.java.asm04.models.Account;
import vn.funix.FX09371.java.asm04.models.Customer;
import vn.funix.FX09371.java.asm04.service.BinaryFileService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class BinaryFileServiceTest {

    @Test (expected = FileNotFoundException.class)
    public void readFile() throws IOException {
        List<Account> readData = BinaryFileService.readFile("store/store/accountss.dat");
    }

    @Test (expected = FileNotFoundException.class)
    public void writeFile() throws IOException {
        List<Customer> customers = CustomerDao.list();
        BinaryFileService.writeFile("storage/customers.dat", customers);
    }
}