package vn.funix.FX09371.java.asm02;

import vn.funix.FX09371.java.asm02.models.Account;
import vn.funix.FX09371.java.asm02.models.Bank;
import vn.funix.FX09371.java.asm02.models.Customer;

import java.util.HashMap;
import java.util.Scanner;

public class Asm2 {
    public static final String AUTHOR = "FX09371";

    public static final String VERSION = "2.0.0";

    private static final Bank bank = new Bank();

    // Table of Province Code
    public static final HashMap<String, String> bangTraCuuMaTinh = new HashMap<>();

    // public scanner used in main()
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize table for Province Code using HashMap
        setBangTraCuuMaTinh();

        // Program starts
        displayProgramHeader();
        while (true) {
            displayMenu();
            int inputInt = getInputInt(0, 5);
            switch (inputInt) {
                case 1:
                    mainAddCustomer();
                    break;
                case 2:
                    mainAddAccount();
                    break;
                case 3:
                    mainDisplayAll();
                    break;
                case 4:
                    mainSearchCCCD();
                    break;
                case 5:
                    mainSearchName();
                    break;
                default:
                    System.out.println("Đã có lỗi xảy ra.");
                    System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
                    getInputInt(0, 1);
            }
        }
    }

    private static void displayProgramHeader() {
        System.out.println("+--------------+------------------------+-------+");
        System.out.println("| NGÂN HÀNG SỐ | " + AUTHOR + "@v" + VERSION + "                 |");
    }

    private static void displayMenu() {
        System.out.println("+--------------+------------------------+-------+");
        System.out.println("| 1. Thêm khách hàng                            |");
        System.out.println("| 2. Thêm tài khoản cho khách hàng              |");
        System.out.println("| 3. Hiển thị danh sách khách hàng              |");
        System.out.println("| 4. Tìm theo CCCD                              |");
        System.out.println("| 5. Tìm theo tên khách hàng                    |");
        System.out.println("| 0. Thoát                                      |");
        System.out.println("+--------------+------------------------+-------+");
        System.out.print("Chức năng: ");
    }

    // Tính năng 1: Thêm khách hàng mới vào danh sách của Bank
    private static void mainAddCustomer() {
        System.out.print("Nhập tên khách hàng: ");
        String name = sc.next();
        System.out.print("Nhập số CCCD: ");
        String customerID = sc.next();
        bank.addCustomer(new Customer(name, customerID));
        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        getInputInt(0, 1);
    }

    // Tính năng 2: Thêm tài khoản cho khách hàng
    private static void mainAddAccount() {
        String customerID = getCustomerID();
        if (customerID.equals("-1")) {
            System.out.println("Không tìm được CCCD, kết thúc tính năng");
        } else {
            Account newAccount = new Account();
            System.out.print("Nhập mã STK gồm 6 chữ số: ");
            String accountNumber = sc.next();
            while (true) {
                if (accountNumber.equals("No")) {
                    exitSystem();
                }

                if (bank.isAccNumExisted(accountNumber)) {
                    System.out.println("STK đã tồn tại, hãy chọn lại số khác.");
                    System.out.print("Vui lòng nhập lại hoặc 'No' để thoát: ");
                    accountNumber = sc.next();
                    continue;
                }
                break;
            }
            newAccount.setAccountNumber(accountNumber);
            System.out.print("Nhập số dư: ");
            double balance = getInputDouble();
            newAccount.setBalance(balance);
            bank.addAccount(customerID, newAccount);
            System.out.println("Đã thêm tài khoản mới cho người dùng có " +
                    "số CCCD: " + customerID);
        }
        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        getInputInt(0, 1);
    }

    // Tính năng 3: Hiển thị danh sách khách hàng
    private static void mainDisplayAll() {
        for (Customer i : bank.getCustomers()) {
            i.displayInformation();
        }
        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        getInputInt(0, 1);
    }

    // Tính năng 4: Tìm theo CCCD khách hàng
    private static void mainSearchCCCD() {
        String customerID = getCustomerID();
        if (customerID.equals("-1")) {
            System.out.println("Không tìm được CCCD, kết thúc tính năng");
        } else {
            for (Customer c : bank.getCustomers()) {
                if (c.getCustomerId().equals(customerID)) {
                    c.displayInformation();
                    break;
                }
            }
        }
        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        getInputInt(0, 1);
    }

    // Tính năng 5: Tìm theo tên khách hàng
    private static void mainSearchName() {
        System.out.println("+--------------+------------------------+-------+");
        System.out.print("Nhập tên khách hàng: ");
        String name = sc.next();
        while (true) {
            if (name.equals("No")) {
                System.out.println("Không tìm được tên khách hàng, kết thúc tính năng");
                break;
            }

            // Find and display the first customer having matched name.
            boolean found = false;
            for (Customer i : bank.getCustomers()) {
                if (i.getName().contains(name)) {
                    i.displayInformation();
                    found = true;
                    break;
                }
            }

            // If no customer found, prompt error and get input again
            if (!found) {
                System.out.println("Không tìm thấy khách hàng với tên đã nhập");
                System.out.print("Vui lòng nhập lại tên hoặc 'No' để quay lại: ");
                name = sc.next();
                continue;
            }
            break;
        }
        System.out.print("Chọn 1 để quay lại Menu, 0 để thoát: ");
        getInputInt(0, 1);
    }

    private static int getInputInt(int a, int b) {
        while (true) {
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                if (choice >= a && choice <= b) {
                    if (choice == 0) exitSystem();
                    return choice;
                }
            } else {
                sc.next();
            }
            System.out.print("Quý khách vui lòng chọn từ " + a + " đến " + b + ": ");
        }
    }

    private static double getInputDouble() {
        while (true) {
            if (sc.hasNextDouble()) {
                double doubleNumber = sc.nextDouble();
                if (doubleNumber >= 0) return doubleNumber;
            } else {
                sc.next();
            }
            System.out.print("Vui lòng nhập vào một số thực dương.");
        }
    }

    private static String getCustomerID() {
        System.out.print("Nhập CCCD khách hàng: ");
        String customerID = sc.next();
        while (true) {
            if (customerID.equals("No")) {
                return "-1";
            }

            if (!bank.isCustomerIDExisted(customerID)) {
                System.out.println("Không tìm thấy khách hàng với CCCD đã nhập");
                System.out.print("Vui lòng nhập lại CCCD hoặc 'No' để quay lại: ");
                customerID = sc.next();
                continue;
            }
            return customerID;
        }
    }

    public static void exitSystem() {
        System.out.println("Hẹn gặp lại");
        sc.close();
        System.exit(0);
    }

    private static void setBangTraCuuMaTinh() {
        bangTraCuuMaTinh.put("001", "Hà Nội");
        bangTraCuuMaTinh.put("002", "Hà Giang");
        bangTraCuuMaTinh.put("004", "Cao Bằng");
        bangTraCuuMaTinh.put("006", "Bắc Kạn");
        bangTraCuuMaTinh.put("008", "Tuyên Quang");
        bangTraCuuMaTinh.put("010", "Lào Cai");
        bangTraCuuMaTinh.put("011", "Điện Biên");
        bangTraCuuMaTinh.put("012", "Lai Châu");
        bangTraCuuMaTinh.put("014", "Sơn La");
        bangTraCuuMaTinh.put("015", "Yên Bái");
        bangTraCuuMaTinh.put("017", "Hòa Bình");
        bangTraCuuMaTinh.put("019", "Thái Nguyên");
        bangTraCuuMaTinh.put("020", "Lạng Sơn");
        bangTraCuuMaTinh.put("022", "Quảng Ninh");
        bangTraCuuMaTinh.put("024", "Bắc Giang");
        bangTraCuuMaTinh.put("025", "Phú Thọ");
        bangTraCuuMaTinh.put("026", "Vĩnh Phúc");
        bangTraCuuMaTinh.put("027", "Bắc Ninh");
        bangTraCuuMaTinh.put("030", "Hải Dương");
        bangTraCuuMaTinh.put("031", "Hải Phòng");
        bangTraCuuMaTinh.put("033", "Hưng Yên");
        bangTraCuuMaTinh.put("034", "Thái Bình");
        bangTraCuuMaTinh.put("035", "Hà Nam");
        bangTraCuuMaTinh.put("036", "Nam Định");
        bangTraCuuMaTinh.put("037", "Ninh Bình");
        bangTraCuuMaTinh.put("038", "Thanh Hóa");
        bangTraCuuMaTinh.put("040", "Nghệ An");
        bangTraCuuMaTinh.put("042", "Hà Tĩnh");
        bangTraCuuMaTinh.put("044", "Quảng Bình");
        bangTraCuuMaTinh.put("045", "Quảng Trị");
        bangTraCuuMaTinh.put("046", "Thừa Thiên Huế");
        bangTraCuuMaTinh.put("048", "Đà Nẵng");
        bangTraCuuMaTinh.put("049", "Quảng Nam");
        bangTraCuuMaTinh.put("051", "Quảng Ngãi");
        bangTraCuuMaTinh.put("052", "Bình Định");
        bangTraCuuMaTinh.put("054", "Phú Yên");
        bangTraCuuMaTinh.put("056", "Khánh Hòa");
        bangTraCuuMaTinh.put("058", "Ninh Thuận");
        bangTraCuuMaTinh.put("060", "Bình Thuận");
        bangTraCuuMaTinh.put("062", "Kon Tum");
        bangTraCuuMaTinh.put("064", "Gia Lai");
        bangTraCuuMaTinh.put("066", "Đắk Lắk");
        bangTraCuuMaTinh.put("067", "Đắk Nông");
        bangTraCuuMaTinh.put("068", "Lâm Đồng");
        bangTraCuuMaTinh.put("070", "Bình Phước");
        bangTraCuuMaTinh.put("072", "Tây Ninh");
        bangTraCuuMaTinh.put("074", "Bình Dương");
        bangTraCuuMaTinh.put("075", "Đồng Nai");
        bangTraCuuMaTinh.put("077", "Bà Rịa - Vũng Tàu");
        bangTraCuuMaTinh.put("079", "Hồ Chí Minh");
        bangTraCuuMaTinh.put("080", "Long An");
        bangTraCuuMaTinh.put("082", "Tiền Giang");
        bangTraCuuMaTinh.put("083", "Bến Tre");
        bangTraCuuMaTinh.put("084", "Trà Vinh");
        bangTraCuuMaTinh.put("086", "Vĩnh Long");
        bangTraCuuMaTinh.put("087", "Đồng Tháp");
        bangTraCuuMaTinh.put("089", "An Giang");
        bangTraCuuMaTinh.put("091", "Kiên Giang");
        bangTraCuuMaTinh.put("092", "Cần Thơ");
        bangTraCuuMaTinh.put("093", "Hậu Giang");
        bangTraCuuMaTinh.put("094", "Sóc Trăng");
        bangTraCuuMaTinh.put("095", "Bạc Liêu");
        bangTraCuuMaTinh.put("096", "Cà Mau");
    }
}
