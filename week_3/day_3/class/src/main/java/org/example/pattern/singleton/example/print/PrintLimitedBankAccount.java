package org.example.pattern.singleton.example.print;

import org.example.pattern.singleton.example.Account.LimitedBankAccount;
import org.example.pattern.singleton.example.Account.IAccount;

public class PrintLimitedBankAccount implements IPrint {
    public void printAccount(IAccount account) {
        LimitedBankAccount a = (LimitedBankAccount) account;
        System.out.println("Limited Account Number: " + account.getAccountNumber());
        System.out.println("Owner Name: " + account.getOwnerName());
        System.out.println("Balance: " + account.getBalance());
        System.out.println("Balance Limited: " + a.getBalanceLimit());
    }
}
