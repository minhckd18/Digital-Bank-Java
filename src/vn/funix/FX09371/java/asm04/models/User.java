package vn.funix.FX09371.java.asm04.models;

import vn.funix.FX09371.java.asm03.models.Utils;
import vn.funix.FX09371.java.asm04.exception.CustomerIdNotValidException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

public class User implements Serializable {
    private String name;
    private String customerId;
    private long serialVersionUID = 1L;

    private static final HashMap<String, String> bangTraCuuMaTinh = Utils.setBangTraCuuMaTinh();

    public User(String name, String customerId) {
        setName(name);
        setCustomerId(customerId);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCustomerId(String customerId) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (!validateCustomerId(customerId)) {
                displayErrorCCCD();
                customerId = sc.next();
                continue;
            }
            break;
        }
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    private void displayErrorCCCD() {
        System.out.println("Số CCCD không hợp lệ.");
        System.out.print("Vui lòng nhập lại: ");
    }

    public static boolean validateCustomerId(String customerId) {

        if (customerId.length() != 12 ||
                !customerId.matches("[0-9]+")) {
            System.out.println("Số CCCD phải gồm 12 chữ số");
            return false;
        }

        String maTinh = customerId.substring(0, 3);
        if (!bangTraCuuMaTinh.containsKey(maTinh)) {
            System.out.println("Mã tỉnh không đúng");
            return false;
        }

        return true;
    }

    public static String validateCustomerId1(String customerId) {

        if (customerId.length() != 12 ||
                !customerId.matches("[0-9]+") ||
                !bangTraCuuMaTinh.containsKey(customerId.substring(0, 3))) {
            throw new CustomerIdNotValidException("Số CCCD phải gồm 12 chữ số" +
                    " và phải có 3 chữ số đầu theo mã tỉnh");
        } else {
            return customerId;
        }
    }
}
