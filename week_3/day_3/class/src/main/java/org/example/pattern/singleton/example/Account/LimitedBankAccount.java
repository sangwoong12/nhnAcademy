package org.example.pattern.singleton.example.Account;

import java.math.BigDecimal;
import org.example.pattern.singleton.example.print.IPrint;
import org.example.pattern.singleton.example.print.PrintLimitedBankAccount;

public class LimitedBankAccount implements IAccount {
    protected String accountNumber;
    protected String ownerName;
    protected BigDecimal balance;
    private BigDecimal balanceLimit;
    private IPrint iPrint;
    public LimitedBankAccount(String ownerName, BigDecimal balance, BigDecimal balanceLimit) {
        this.accountNumber = CreateAccount.getCreateAccount().createAccountNumber(this);
        this.ownerName = ownerName;
        this.balance = balance;
        this.balanceLimit = balanceLimit;
        this.iPrint = new PrintLimitedBankAccount();
    }

    public BigDecimal deposit(BigDecimal amount) {
        if (balance.add(amount).compareTo(this.balanceLimit) == 1) {
            System.out.println("balance limit exceeded");
            return balance;
        }
        else {
            return balance = balance.add(amount);
        }
    }

    public BigDecimal getBalanceLimit() {
            return this.balanceLimit;
    }

    @Override
    public boolean withDraw(BigDecimal amount) {
        balance = balance.subtract(amount);
        return true;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void printAccount() {
        iPrint.printAccount(this);
    }
}
