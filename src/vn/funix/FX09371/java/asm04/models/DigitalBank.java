package vn.funix.FX09371.java.asm04.models;

import vn.funix.FX09371.java.asm04.exception.CustomerIdNotValidException;
import vn.funix.FX09371.java.asm04.service.TextFileService;
import vn.funix.FX09371.java.asm04.common.Utils;
import vn.funix.FX09371.java.asm04.dao.CustomerDao;
import vn.funix.FX09371.java.asm04.exception.MethodExitException;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class DigitalBank extends Bank {

    public void showCustomers() {
        List<Customer> customerList = CustomerDao.list();
        if (!customerList.isEmpty()) {
            for (Customer customer : customerList) {
                customer.displayInformation();
            }
        } else {
            System.out.println("Chưa có khách hàng nào trong danh sách");
        }
    }

    public void addCustomers(String fileName) throws FileNotFoundException {
        List<Customer> customerList = CustomerDao.list();
        List<List<String>> readData = TextFileService.readFile(fileName);

        for (int i = 0; i < readData.size(); i++) {
            try {
                User.validateCustomerId1(readData.get(i).get(0));
            } catch(CustomerIdNotValidException e) {
                System.out.println("Mã số CCCD không đúng, bỏ qua mã " + readData.get(i).get(0));
                continue;
            }

            Customer newCustomer = new Customer(readData.get(i));

            if (!this.isCustomerExisted(customerList, newCustomer)) {
                customerList.add(newCustomer);
                System.out.println("Đã thêm khách hàng " + newCustomer.getCustomerId() + " thành công");
            } else {
                System.out.println("Khách hàng " + newCustomer.getCustomerId() +
                        " đã tồn tại, thêm khách hàng không thành công");
            }
        }

        CustomerDao.save(customerList);
    }

    public boolean addSavingAccount(Scanner scanner, String customerId) {
        // Check if customerId exists
        List<Customer> customerList = CustomerDao.list();
        Customer customer = getCustomerById(customerList, customerId);
        if (customer == null) {
            System.out.println("Không tìm thấy khách hàng có số CCCD: " + customerId);
            return false;
        }

        while (true) {
            System.out.print("Nhập mã STK gồm 6 chữ số: ");
            String accountNumber = scanner.nextLine();

            if (!Utils.validateAccount(accountNumber)) {
                System.out.println("STK không đúng, hãy chọn lại số khác");
                continue;
            }

            if (customer.addSavingAccount(scanner, accountNumber)) {
                return true;
            }
        }
    }

    public Customer getCustomerById(String customerId) {
        for (Customer checkedCustomer : this.getCustomers()) {
            if (checkedCustomer.getCustomerId().equals(customerId)) return checkedCustomer;
        }
        return null;
    }

    public boolean withdraw(Scanner scanner, String customerId) throws MethodExitException {
        // Check if customerId exists
        List<Customer> customerList = CustomerDao.list();
        Customer customer = getCustomerById(customerList, customerId);
        if (customer == null) {
            System.out.println("Không tìm thấy khách hàng có số CCCD: " + customerId);
            return false;
        }

        customer.displayInformation();

        List<Account> accountList = customer.getAccountsFromFile();
        if (!accountList.isEmpty()) {
            while (true) {
                System.out.print("Nhập mã STK gồm 6 chữ số: ");
                String accountNumber = scanner.nextLine();

                if (!Utils.validateAccount(accountNumber)) {
                    System.out.println("STK không đúng, hãy chọn lại số khác");
                    continue;
                }

                if (customer.withdraw(scanner, accountNumber)) {
                    return true;
                }
            }
        } else {
            System.out.println("Khách hàng không có tài khoản nào, thao tác không thành công");
            throw new MethodExitException();
        }
    }

    public boolean transfer(Scanner scanner, String customerId) throws MethodExitException {
        // Check if customerId exists
        List<Customer> customerList = CustomerDao.list();
        Customer customer = getCustomerById(customerList, customerId);
        if (customer == null) {
            System.out.println("Không tìm thấy khách hàng có số CCCD: " + customerId);
            return false;
        }

        customer.displayInformation();

        List<Account> accountList = customer.getAccountsFromFile();
        if (!accountList.isEmpty()) {
            while (true) {
                System.out.print("Nhập mã STK gồm 6 chữ số: ");
                String accountNumber = scanner.nextLine();

                if (!Utils.validateAccount(accountNumber)) {
                    System.out.println("STK không đúng, hãy chọn lại số khác");
                    continue;
                }

                if (customer.transfer(scanner, accountNumber)) {
                    return true;
                }
            }
        } else {
            System.out.println("Khách hàng không có tài khoản nào, thao tác không thành công");
            throw new MethodExitException();
        }
    }

    public boolean isAccountExisted(List<Account> accountList, Account newAccount) {
        boolean hasExist = accountList
                .stream()
                .anyMatch(account -> account.getAccountNumber().equals(newAccount.getAccountNumber()));
        return false;
    }

    public boolean isAccountExisted(List<Account> accountList, String accountNumber) {
        for (Account account : accountList) {
            if (account.getAccountNumber().equals(accountNumber)) return true;
        }
        return false;
    }

    public boolean isCustomerExisted(List<Customer> customers, Customer newCustomer) {
        boolean hasExist = customers
                .stream()
                .anyMatch(customer ->
                        customer.getCustomerId().equals(newCustomer.getCustomerId()));
        return hasExist;
    }

    public Customer getCustomerById(List<Customer> customers, String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) return customer;
        }
        return null;
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
