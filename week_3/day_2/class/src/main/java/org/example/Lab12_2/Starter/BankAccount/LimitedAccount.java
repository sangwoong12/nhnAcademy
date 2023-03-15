package org.example.Lab12_2.Starter.BankAccount;

import java.math.BigDecimal;
import org.example.Lab12_2.Starter.BankAccount.BankAccount;
import org.example.Lab12_2.Starter.printAccount.IPrint;
import org.example.Lab12_2.Starter.printAccount.PrintAccount;
import org.example.Lab12_2.Starter.printAccount.PrintLimitedAccount;

public class LimitedAccount extends BankAccount {
    private BigDecimal balanceLimited;
    private IPrint print = new PrintLimitedAccount();

    public LimitedAccount(String ownerName, BigDecimal balance, BigDecimal balanceLimited) {
        super(ownerName, balance);
        this.balanceLimited = balanceLimited;
        super.print = new PrintAccount();
    }

    @Override
    public void printAccount(IAccount bankAccount) {
        print.printAccount(bankAccount);
    }

    @Override
    public BigDecimal deposit(BigDecimal amount) {
        if(getBalance().add(amount).compareTo(this.balanceLimited) == 1){
            System.out.println("Balance limit exceeded");
            return getBalance();
        } else{
            return getBalance().add(amount);
        }
    }

}
