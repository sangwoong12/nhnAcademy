package org.example.pattern.singleton.example.Account;

import java.math.BigDecimal;

public interface IAccount {
    BigDecimal deposit(BigDecimal amount);
    boolean withDraw(BigDecimal amount);
    String getAccountNumber();

    String getOwnerName();

    BigDecimal getBalance();
    void printAccount();
}
