package org.example.Lab12_1.Starter;

import java.math.BigDecimal;

public class BankAccount {

    private String accountNumber;
    private String ownerName;
    private BigDecimal balance;

    public BankAccount(String ownerName, BigDecimal balance) {
        this.accountNumber = CreateAccount.createAccountNumber();
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public BigDecimal deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        return this.balance;
    }

    public boolean withDraw(BigDecimal amount) {
        if (amount.compareTo(this.balance) == 1 || amount.compareTo(this.balance) == 0) {
            return false;
        } else {
            balance = balance.subtract(amount);
            return true;
        }
    }

    public void printAccount(BankAccount bankAccount) {
        PrintAccount.printAccount(bankAccount);
    }

//    public void printAccount() {
//        System.out.println("Account Number: " + this.accountNumber);
//        System.out.println("Owner Name: " + this.ownerName);
//        System.out.println("Balance: " + this.balance.toString());
//    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
