package org.example.Lab12_2.Starter.printAccount;

import org.example.Lab12_2.Starter.BankAccount.IAccount;

public class PrintAccount implements IPrint {
    public void printAccount(IAccount bankAccount) {
        System.out.println("일반 계좌: Account Number: " + bankAccount.getAccountNumber());
        System.out.println("Owner Name: " + bankAccount.getOwnerName());
        System.out.println("Balance: " + bankAccount.getBalance().toString());
    }
}
