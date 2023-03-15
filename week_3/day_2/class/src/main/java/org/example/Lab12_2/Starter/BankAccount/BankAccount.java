package org.example.Lab12_2.Starter.BankAccount;

import java.math.BigDecimal;

import org.example.Lab12_2.Starter.CreateAccount;
import org.example.Lab12_2.Starter.printAccount.IPrint;
import org.example.Lab12_2.Starter.printAccount.PrintAccount;

public class BankAccount implements IAccount {

    static String accountNumber;
    static String ownerName;
    static BigDecimal balance;
    static IPrint print = new PrintAccount();

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

    @Override
    public void printAccount(IAccount bankAccount) {
        print.printAccount(bankAccount);
    }


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
