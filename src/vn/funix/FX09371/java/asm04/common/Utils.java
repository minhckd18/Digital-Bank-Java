package vn.funix.FX09371.java.asm04.common;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Utils {
    public static boolean validateAccount(String accountNumber) {
        if (accountNumber.length() != 6) return false;
        if (!accountNumber.matches("[0-9]+")) return false;
        return true;
    }

    public static double getInputDouble(Scanner scanner) {
        while (true) {
            if (scanner.hasNextDouble()) {
                double doubleNumber = scanner.nextDouble();
                if (doubleNumber >= 0) return doubleNumber;
            } else {
                scanner.next();
            }
            System.out.println("Số nhập vào phải là số thực dương");
            System.out.print("Vui lòng nhập lại: ");
        }
    }

    public static int getInputInt(int a, int b) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                if (choice == 0) exitSystem();
                if (choice >= a && choice <= b) {
                    return choice;
                }
            } else {
                scanner.next();
            }
            System.out.print("Vui lòng chọn từ " + a + " đến " + b + ": ");
        }
    }

    public static void exitSystem() {
        System.out.println("Hẹn gặp lại");
        System.exit(0);
    }

    public static String getDateTime() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return df.format(date);
    }

    // Format a double balance into Vietnamese currency format
    public static String currencyFormat(double amount) {
        NumberFormat vn = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return vn.format(amount);
    }


    // Return a HashMap for checking up valid ID number
    public static HashMap<String, String> setBangTraCuuMaTinh() {
        HashMap<String, String> bangTraCuuMaTinh = new HashMap<>();
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

        return bangTraCuuMaTinh;
    }
}
