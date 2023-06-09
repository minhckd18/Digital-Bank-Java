package vn.funix.FX09371.java.asm04.models;

import java.io.Serializable;

public class DigitalCustomer extends Customer implements Serializable {
    private long serialVersionUID = 1L;
//    public DigitalCustomer() {
//        super();
//    }
    public DigitalCustomer(String name, String CustomerID) {
        super(name, CustomerID);
    }

//    @Override
//    public void displayInformation() {
//        super.displayInformation();
//        for (int j = 0; j < this.getAccounts().size(); j++) {
//            Account tempAcc = this.getAccounts().get(j);
//            System.out.printf("%2d%10s | %8s | %30s%n", j + 1, tempAcc.getAccountNumber(),
//                    tempAcc.getType(), Utils.currencyFormat(tempAcc.getBalance()));
//        }
//    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account != null) {
            if (account.withdraw(amount)) {
                setCustomerRank();
                return true;
            }
        }
        return false;
    }

//    @Override
//    public void displayTransactions() {
//        for (Account account : getAccounts()) {
//            account.displayTransactions();
//        }
//    }
}
