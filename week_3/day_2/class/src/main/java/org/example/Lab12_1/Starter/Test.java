package org.example.Lab12_1.Starter;

import java.math.BigDecimal;

class Test {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("Jason", new BigDecimal(100));
        account.printAccount(account);
        BankAccount account2 = new BankAccount("James", new BigDecimal(1000));
        account2.printAccount(account2);
    }
}
