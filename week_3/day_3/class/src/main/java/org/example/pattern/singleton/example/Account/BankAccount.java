package org.example.pattern.singleton.example.Account;

import java.math.BigDecimal;
import org.example.pattern.singleton.example.print.IPrint;
import org.example.pattern.singleton.example.print.PrintAccount;

public class BankAccount implements IAccount {

    protected String accountNumber;
    protected String ownerName;
    protected BigDecimal balance;
    private IPrint iPrint;

    public BankAccount(String ownerName, BigDecimal balance) {
        this.accountNumber = CreateAccount.getCreateAccount().createAccountNumber(this);
        this.ownerName = ownerName;
        this.balance = balance;
        this.iPrint = new PrintAccount();
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

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public String getOwnerName() {
        return this.ownerName;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void printAccount() {
        iPrint.printAccount(this);
    }
}
