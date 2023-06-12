package vn.funix.FX09371.java.asm04;

import vn.funix.FX09371.java.asm04.common.Utils;
import vn.funix.FX09371.java.asm04.dao.CustomerDao;
import vn.funix.FX09371.java.asm04.exception.CustomerIdNotValidException;
import vn.funix.FX09371.java.asm04.exception.MethodExitException;
import vn.funix.FX09371.java.asm04.models.Customer;
import vn.funix.FX09371.java.asm04.models.DigitalBank;
import vn.funix.FX09371.java.asm04.models.User;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class DigitalBankConsoleLauncher {
    public static final String AUTHOR = "FX09371";
    public static final String VERSION = "4.0.0";
    public static Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();

    public static void main(String[] args) {

        displayProgramHeader();

        while (true) {
            displayMenu();

            // get user input as an integer
            List<Customer> customerList = CustomerDao.list();
            int inputInt;

            // Block feature 3, 4, 5 and 6 until a customer list is created
            if (customerList.isEmpty()) {
                System.out.println("Chưa có khách hàng nào trong danh sách, chỉ có thể dùng chức năng 0, 1 và 2");
                System.out.print("Chọn chức năng: ");
                inputInt = Utils.getInputInt(0, 2);
            } else {
                System.out.print("Chọn chức năng: ");
                inputInt = Utils.getInputInt(0, 6);
            }

            switch (inputInt) {
                case 1:
                    showCustomer();
                    break;
                case 2:
                    addCustomersFromFile();
                    break;
                case 3:
                    addSavingAccount();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    withdraw();
                    break;
                case 6:
                    showHistory();
                    break;
                default:
                    System.out.println("Đã có lỗi xảy ra.");
                    System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
                    Utils.getInputInt(0, 1);
            }
        }
    }

    private static void displayProgramHeader() {
        System.out.println("+----------+------------------------------+------------+");
        System.out.println("| NGÂN HÀNG SỐ | " + AUTHOR + "@v" + VERSION + "                        |");
    }

    private static void displayMenu() {
        System.out.println("+----------+------------------------------+------------+");
        System.out.println("| 1. Xem danh sách khách hàng                          |");
        System.out.println("| 2. Nhập danh sách khách hàng                         |");
        System.out.println("| 3. Thêm tài khoản ATM                                |");
        System.out.println("| 4. Chuyển tiền                                       |");
        System.out.println("| 5. Rút tiền                                          |");
        System.out.println("| 6. Tra cứu lịch sử giao dịch                         |");
        System.out.println("| 0. Thoát                                             |");
        System.out.println("+----------+------------------------------+------------+");
    }

    // Chức năng 1: Xem danh sách khách hàng
    private static void showCustomer() {
        activeBank.showCustomers();

        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        Utils.getInputInt(0, 1);
    }

    // Chức năng 2: Nhập danh sách khách hàng
    private static void addCustomersFromFile() {
        while (true) {
            System.out.println("Nhập đường dẫn đến tệp:");
            String pathInput = scanner.nextLine();
            try {
                activeBank.addCustomers(pathInput);
            } catch (FileNotFoundException e) {
                System.out.println("Tệp không tồn tại");
                continue;
            }
            break;
        }

        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        Utils.getInputInt(0, 1);
    }

    // Chức năng 3: Thêm tài khoản Savings
    private static void addSavingAccount() {
        while (true) {
            System.out.println("Nhập số CCCD của khách hàng:");
            String customerId;

            try {
                customerId = User.validateCustomerId1(scanner.nextLine());
            } catch (CustomerIdNotValidException e) {
                System.out.println(e.getMessage());
                continue;
            }

            // Try to add new saving account. If false, restart while loop to get new customerId
            if (activeBank.addSavingAccount(scanner, customerId)) {
                System.out.println("Đã thêm tài khoản ATM mới cho người dùng có " +
                        "số CCCD: " + customerId);
                break;
            }
        }

        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        Utils.getInputInt(0, 1);
    }

    // Chức năng 4: Chuyển tiền
    private static void transfer() {
        while (true) {
            System.out.println("Nhập số CCCD của khách hàng:");
            String customerId;
            try {
                customerId = User.validateCustomerId1(scanner.nextLine());
            } catch (CustomerIdNotValidException e) {
                System.out.println(e.getMessage());
                continue;
            }

            try {
                if (activeBank.transfer(scanner, customerId)) {
                    System.out.println("Giao dịch chuyển tiền thành công");
                    break;
                }
            } catch (MethodExitException e) {
                break;
            }
        }

        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        Utils.getInputInt(0, 1);
    }

    // Chức năng 5: Rút tiền
    private static void withdraw() {
        // Get customerId
        while (true) {
            System.out.println("Nhập số CCCD của khách hàng:");
            String customerId;
            try {
                customerId = User.validateCustomerId1(scanner.nextLine());
            } catch (CustomerIdNotValidException e) {
                System.out.println(e.getMessage());
                continue;
            }

            try {
                if (activeBank.withdraw(scanner, customerId)) {
                    System.out.println("Giao dịch rút tiền thành công");
                    break;
                }
            } catch (MethodExitException e) {
                break;
            }
        }

        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        Utils.getInputInt(0, 1);
    }

    // Chức năng 6: Lịch sử giao dịch
    private static void showHistory() {
        List<Customer> customerList = CustomerDao.list();
        while (true) {
            System.out.println("Nhập số CCCD của khách hàng:");
            String customerId;
            try {
                customerId = User.validateCustomerId1(scanner.nextLine());
            } catch (CustomerIdNotValidException e) {
                System.out.println(e.getMessage());
                continue;
            }

            Customer customer = activeBank.getCustomerById(customerList, customerId);

            if (customer != null) {
                customer.displayTransactionInformation();
                break;
            } else {
                System.out.println("Không tìm thấy khách hàng có số CCCD: " + customerId);

            }
        }

        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        Utils.getInputInt(0, 1);
    }
}
