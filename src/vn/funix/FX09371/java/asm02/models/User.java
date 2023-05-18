package vn.funix.FX09371.java.asm02.models;

import vn.funix.FX09371.java.asm03.models.Utils;

import java.util.HashMap;
import java.util.Scanner;

public class User {
    private String name;
    private String customerId;
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
        while(true) {
            if(!validateCustomerId(customerId)) {
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

    public boolean validateCustomerId(String customerId) {

            if(customerId.length() != 12 ||
                    !customerId.matches("[0-9]+")) {
                return false;
            }

            String maTinh = customerId.substring(0, 3);
            if(!bangTraCuuMaTinh.containsKey(maTinh)) {
                return false;
            }

            return true;
    }
}
