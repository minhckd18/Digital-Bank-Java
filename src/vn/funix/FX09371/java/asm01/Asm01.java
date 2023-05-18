package vn.funix.FX09371.java.asm01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Asm01 {

    public static final String AUTHOR = "FX09371";
    public static final String VERSION = "1.0.0";
    public static void main(String[] args) {
        // Create Scanner Object
        Scanner sc = new Scanner(System.in);

        // Array of possible centuries
        ArrayList<Integer> mangTheKy = new ArrayList<>();
        mangTheKy.add(19); //The ky 20
        mangTheKy.add(20); //The ky 21
        mangTheKy.add(21); //The ky 22
        mangTheKy.add(22); //The ky 23
        mangTheKy.add(23); //The ky 24


        // Create a Table for Province Code using HashMap
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
        bangTraCuuMaTinh.put("093","Hậu Giang");
        bangTraCuuMaTinh.put("094", "Sóc Trăng");
        bangTraCuuMaTinh.put("095", "Bạc Liêu");
        bangTraCuuMaTinh.put("096", "Cà Mau");


        // Program starts
        System.out.println("+--------------+------------------------+-------+");
        System.out.println("| NGAN HANG SO | " + AUTHOR + "@v" + VERSION + "                 |");
        System.out.println("+--------------+------------------------+-------+");
        System.out.println("| 1. Nhap CCCD                                  |");
        System.out.println("| 0. Thoat                                      |");
        System.out.println("+--------------+------------------------+-------+");
        System.out.print("Chuc nang: ");

        // Check input 1 or 0
        String userInput = sc.next();
        while(true) {
            switch(userInput) {
                case "0":
                    System.exit(0);
                case "1":
                    break;
                default:
                    System.out.println("Quy khach vui long chon 1 hoac 0.");
                    System.out.print("Chuc nang: ");
                    userInput = sc.next();
                    continue;
            }
            break;
        }

        // Choose HARD or EASY mode
        System.out.println("|1. Ma xac thuc 3 ky tu");
        System.out.println("|2. Ma xac thuc 6 ky tu");
        System.out.print("Chon 1 hoac 2: ");
        userInput = sc.next();
        String maXacThuc = "";
        while(true) {
            switch(userInput) {
                case "1":
                    maXacThuc = generateOTP("1");
                    break;
                case "2":
                    maXacThuc = generateOTP("2");
                    break;
                default:
                    System.out.println("|1. Ma xac thuc HARD");
                    System.out.println("|2. Ma xac thuc EASY");
                    System.out.print("Quy khach vui long chon 1 hoac 2: ");
                    userInput = sc.next();
                    continue;
            }
            break;
        }

        // Check OTP
        System.out.println("Nhap ma xac thuc: " + maXacThuc);
        userInput = sc.next();
        while(!userInput.equals(maXacThuc)) {
            System.out.println("Ma xac thuc khong dung. Vui long thu lai.");
            userInput = sc.next();
            System.out.println(userInput);
        }

        // Enter CCCD code
        System.out.print("Vui long nhap so CCCD: ");
        String maSoCCCD;

        // Check CCCD validity
        userInput = sc.next();
        while(true) {
            if(userInput.equals("No")) {
                System.exit(0);
            }

            if(userInput.length() != 12) {
                displayErrorCCCD();
                userInput = sc.next();
                continue;
            }

            if(!userInput.matches("[0-9]+")) {
                displayErrorCCCD();
                userInput = sc.next();
                continue;
            }

            String maTinh = userInput.substring(0, 3);
            if(!bangTraCuuMaTinh.containsKey(maTinh)) {
                displayErrorCCCD();
                userInput = sc.next();
                continue;
            }

            maSoCCCD = userInput;
            break;
        }

        // Features
        displayFeatures();
        userInput = sc.next();

        // Generate answers
        while(true) {
            switch(userInput) {
                case "0":
                    System.exit(0);
                case "1":
                    String maTinh = maSoCCCD.substring(0, 3);
                    System.out.println("Noi sinh: " + bangTraCuuMaTinh.get(maTinh));
                    displayFeatures();
                    userInput = sc.next();
                    break;
                case "2":
                    char maGioiTinh = maSoCCCD.charAt(3);
                    String gioiTinh = "";
                    switch(maGioiTinh) {
                        case '0', '2', '4', '6', '8':
                            gioiTinh = "Nam";
                            break;
                        case '1', '3', '5', '7', '9':
                            gioiTinh = "Nu";
                            break;
                    }

                    String namSinh = "";
                    int birthCode = Character.getNumericValue(maGioiTinh);
                    namSinh = Integer.toString(mangTheKy.get(birthCode / 2)) + maSoCCCD.substring(4, 6);

                    System.out.println("Gioi tinh: " + gioiTinh + " | " + namSinh);
                    displayFeatures();
                    userInput = sc.next();
                    break;
                case "3":
                    String soNgauNhien = maSoCCCD.substring(6);
                    System.out.println("So ngau nhien: " + soNgauNhien);
                    displayFeatures();
                    userInput = sc.next();
                    break;
                default:
                    System.out.println("Quy khach vui long chon 1, 2, 3 hoac 0.");
                    displayFeatures();
                    userInput = sc.next();
            }
        }
    }

    public static void displayFeatures() {
        System.out.println("|1. Kiem tra noi sinh");
        System.out.println("|2. Kiem tra tuoi, gioi tinh");
        System.out.println("|3. Kiem tra so ngau nhien");
        System.out.println("|0. Thoat");
        System.out.print("Chuc nang: ");
    }

    public static void displayErrorCCCD() {
        System.out.println("So CCCD khong hop le.");
        System.out.print("Vui long nhap lai hoac 'No' de thoat: ");
    }

    public static String generateOTP(String choice) {

        if(choice.equals("1")) {
            // Generate EASY OTP
            int randomInteger = (int) (Math.random() * 900) + 100;
            String maXacThucEASY = Integer.toString(randomInteger);
            return maXacThucEASY;
        } else {
            // Generate HARD OTP
            String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            Random randomObject = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                int index = randomObject.nextInt(candidateChars.length());
                char c = candidateChars.charAt(index);
                sb.append(c);
            }
            String maXacThucHARD = sb.toString();
            return  maXacThucHARD;
        }
    }
}
