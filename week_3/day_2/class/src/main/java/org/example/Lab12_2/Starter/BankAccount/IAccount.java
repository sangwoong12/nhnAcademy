package org.example.Lab12_2.Starter.BankAccount;

import java.math.BigDecimal;

public interface IAccount {
    BigDecimal deposit(BigDecimal amount);
    boolean withDraw(BigDecimal amount);
    void printAccount(IAccount bankAccount);
    String getAccountNumber();
    String getOwnerName();
    BigDecimal getBalance();

}
