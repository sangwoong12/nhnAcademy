package org.example.Lab12_2.Starter.BankAccount;

import java.math.BigDecimal;
import org.example.Lab12_2.Starter.printAccount.IPrint;
import org.example.Lab12_2.Starter.printAccount.PrintSavingAccount;

public class SavingAccount implements IAccount {
    private IPrint print;

    public SavingAccount(String ownerName, BigDecimal balance) {
        this.print = new PrintSavingAccount();
    }

    @Override
    public BigDecimal deposit(BigDecimal amount) {
        return null;
    }

    @Override
    public boolean withDraw(BigDecimal amount) {
        return false;
    }

    @Override
    public void printAccount(IAccount bankAccount) {
        print.printAccount(bankAccount);
    }

    @Override
    public String getAccountNumber() {
        return null;
    }

    @Override
    public String getOwnerName() {
        return null;
    }

    @Override
    public BigDecimal getBalance() {
        return null;
    }
}
