package vn.funix.FX09371.java.asm03;

import vn.funix.FX09371.java.asm02.models.Customer;
import vn.funix.FX09371.java.asm03.models.*;

import java.util.Scanner;

public class Asm3 {
    // Initialize base data
    public static final String AUTHOR = "FX09371";
    public static final String VERSION = "3.0.0";
    private static final int EXIT_COMMAND_CODE = 0;
    private static final int EXIT_ERROR_CODE = -1;
    public static Scanner scanner = new Scanner(System.in);
    private static final DigitalBank activeBank = new DigitalBank();
    private static final String CUSTOMER_ID = "054095006539";
    private static final String CUSTOMER_NAME = "MINH";


    public static void main(String[] args) {
        activeBank.addCustomer(new DigitalCustomer(CUSTOMER_NAME, CUSTOMER_ID));

        // Program starts
        displayProgramHeader();
        while (true) {
            displayMenu();
            int inputInt = Utils.getInputInt(0, 5);
            switch (inputInt) {
                case 1:
                    showCustomer();
                    break;
                case 2:
                    addSavingsAccount();
                    break;
                case 3:
                    addLoanAccount();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
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
        System.out.println("| 1. Thông tin khách hàng                              |");
        System.out.println("| 2. Thêm tài khoản ATM                                |");
        System.out.println("| 3. Thêm tài khoản tín dụng                           |");
        System.out.println("| 4. Rút tiền                                          |");
        System.out.println("| 5. Lịch sử giao dịch                                 |");
        System.out.println("| 0. Thoát                                             |");
        System.out.println("+----------+------------------------------+------------+");
        System.out.print("Chọn chức năng: ");
    }

    // Chức năng 1: Liệt kê thông tin khách hàng
    private static void showCustomer() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer != null) {
            customer.displayInformation();
        } else {
            System.out.println("Không tìm thấy khách hàng");
        }

        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        Utils.getInputInt(0, 1);
    }

    // Chức năng 2: Thêm tài khoản Savings
    private static void addSavingsAccount() {
        System.out.print("Nhập mã STK gồm 6 chữ số: ");
        String accountNumber = Utils.getNewAccNum(activeBank);
        System.out.print("Nhập số dư: ");
        double balance = Utils.getInputDouble();
        activeBank.addAccount(CUSTOMER_ID, new SavingsAccount(accountNumber, balance));
        System.out.println("Đã thêm tài khoản ATM mới cho người dùng có " +
                "số CCCD: " + CUSTOMER_ID);

        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        Utils.getInputInt(0, 1);
    }

    // Chức năng 3: Thêm tài khoản Loan
    private static void addLoanAccount() {
        System.out.print("Nhập mã STK gồm 6 chữ số: ");
        String accountNumber = Utils.getNewAccNum(activeBank);
        activeBank.addAccount(CUSTOMER_ID, new LoanAccount(accountNumber));
        System.out.println("Đã thêm tài khoản tín dụng cho người dùng có " +
                "số CCCD: " + CUSTOMER_ID);

        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        Utils.getInputInt(0, 1);
    }

    // Chức năng 4: Rút tiền
    private static void withdraw() {
        // Get account number
        String accountNumber;
        while (true) {
            System.out.print("Nhập STK để thực hiện giao dịch: ");
            accountNumber = scanner.next();

            if (!activeBank.isAccNumExisted(accountNumber)) {
                System.out.println("STK không tồn tại. Vui lòng nhập lại.");
                continue;
            }

            break;
        }

        // Get amount of withdraw money and withdraw
        while (true) {
            System.out.print("Nhập số tiền cần rút: ");
            double amount = Utils.getInputDouble();
            if (activeBank.withdraw(CUSTOMER_ID, accountNumber, amount)) {
                System.out.println("Rút tiền thành công.");
                break;
            }
            System.out.println("Số tiền nhập không hợp lệ.");

            System.out.print("Chọn 1 để nhập lại số tiền, 2 để kết thúc giao dịch: ");
            if (Utils.getInputInt(1, 2) == 2) break;
        }

        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        Utils.getInputInt(0, 1);
    }

    // Chức năng 5: Lịch sử giao dịch
    private static void showHistory() {
        Customer customer = activeBank.getCustomerById(CUSTOMER_ID);
        if (customer != null) {
            customer.displayInformation();
            customer.displayTransactions();
        } else {
            System.out.println("Không tìm thấy khách hàng");
        }

        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        Utils.getInputInt(0, 1);
    }
}
