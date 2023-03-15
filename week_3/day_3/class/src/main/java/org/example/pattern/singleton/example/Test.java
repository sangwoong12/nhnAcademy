package org.example.pattern.singleton.example;

import java.math.BigDecimal;
import org.example.pattern.singleton.example.Account.BankAccount;
import org.example.pattern.singleton.example.Account.IAccount;
import org.example.pattern.singleton.example.Account.LimitedBankAccount;
import org.example.pattern.singleton.example.Account.SavingBankAccount;

class Test {
    public static void main(String[] args) {

        IAccount account = new BankAccount("Jason", new BigDecimal(100));
        account.printAccount();
        IAccount account2 = new LimitedBankAccount("James", new BigDecimal(1000), new BigDecimal(10000));
        account2.printAccount();
        IAccount account3 = new SavingBankAccount("sangwoong", new BigDecimal(100));
        account3.printAccount();
    }
}
