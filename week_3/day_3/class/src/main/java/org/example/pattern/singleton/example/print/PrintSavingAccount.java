package org.example.pattern.singleton.example.print;

import org.example.pattern.singleton.example.Account.IAccount;
import org.example.pattern.singleton.example.print.IPrint;

public class PrintSavingAccount implements IPrint {

    public void printAccount(IAccount account) {
        System.out.println("Saving Account Number: " + account.getAccountNumber());
        System.out.println("Owner Name: " + account.getOwnerName());
        System.out.println("Balance: " + account.getBalance());
    }
}