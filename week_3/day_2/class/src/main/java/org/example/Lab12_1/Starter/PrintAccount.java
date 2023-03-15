package org.example.Lab12_1.Starter;

public class PrintAccount {
    public static void printAccount(BankAccount bankAccount) {
        System.out.println("Account Number: " + bankAccount.getAccountNumber());
        System.out.println("Owner Name: " + bankAccount.getOwnerName());
        System.out.println("Balance: " + bankAccount.getBalance().toString());
    }

}
