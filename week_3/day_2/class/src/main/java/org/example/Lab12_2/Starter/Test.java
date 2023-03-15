package org.example.Lab12_2.Starter;

import java.math.BigDecimal;
import org.example.Lab12_2.Starter.BankAccount.BankAccount;
import org.example.Lab12_2.Starter.BankAccount.IAccount;
import org.example.Lab12_2.Starter.BankAccount.LimitedAccount;
import org.example.Lab12_2.Starter.BankAccount.SavingAccount;

class Test {
    public static void main(String[] args) {
        IAccount account = new BankAccount("Jason", new BigDecimal(100));
        account.printAccount(account);
        IAccount account2 = new LimitedAccount("James", new BigDecimal(1000),new BigDecimal(10000));
        account2.printAccount(account2);
        IAccount account3 = new SavingAccount("James", new BigDecimal(1000));
        account3.printAccount(account2);
    }
}
