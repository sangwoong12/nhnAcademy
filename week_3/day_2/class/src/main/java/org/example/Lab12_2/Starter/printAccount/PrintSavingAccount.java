package org.example.Lab12_2.Starter.printAccount;

import org.example.Lab12_2.Starter.BankAccount.IAccount;
import org.example.Lab12_2.Starter.printAccount.IPrint;

public class PrintSavingAccount implements IPrint {

    @Override
    public void printAccount(IAccount account) {
        System.out.println("적금 계좌 :Account Number: " + account.getAccountNumber());
        System.out.println("Owner Name: " + account.getOwnerName());
        System.out.println("Balance: " + account.getBalance().toString());
    }
}
