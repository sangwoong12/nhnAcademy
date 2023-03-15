package org.example.pattern.singleton.example.print;

import org.example.pattern.singleton.example.Account.IAccount;

public class PrintAccount implements IPrint {

    @Override
    public void printAccount(IAccount account) {
        System.out.println("Normal Account Number: " + account.getAccountNumber());
        System.out.println("Owner Name: " + account.getOwnerName());
        System.out.println("Balance: " + account.getBalance().toString());
    }
}
