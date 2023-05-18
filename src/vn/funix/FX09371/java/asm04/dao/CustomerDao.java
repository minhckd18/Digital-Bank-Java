package vn.funix.FX09371.java.asm04.dao;

import vn.funix.FX09371.java.asm04.models.Customer;
import vn.funix.FX09371.java.asm04.service.BinaryFileService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private final static String FILE_PATH = "store/customers.dat";

    public static void save(List<Customer> customers) {
        try {
            BinaryFileService.writeFile(FILE_PATH, customers);
        } catch (IOException e) {
            System.out.println("Ghi dữ liệu không thành công");
            e.printStackTrace();
        }
    }

    public static List<Customer> list() {
        List<Customer> customers = null;
        try {
            customers = BinaryFileService.readFile(FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static void update(Customer editCustomer) {

        List<Customer> customers = list();
        boolean hasExist = customers.stream()
                .anyMatch(customer ->
                        customer.getCustomerId().equals(editCustomer.getCustomerId()));

        List<Customer> updatedCustomers;
        if (!hasExist) {
            updatedCustomers = new ArrayList<>(customers);
            updatedCustomers.add(editCustomer);
        } else {
            updatedCustomers = new ArrayList<>();

            for (Customer customer : customers) {
                if (customer.getCustomerId().equals(editCustomer.getCustomerId())) {
                    updatedCustomers.add(editCustomer);
                } else {
                    updatedCustomers.add(customer);
                }
            }
        }
        save(updatedCustomers);
    }
}
