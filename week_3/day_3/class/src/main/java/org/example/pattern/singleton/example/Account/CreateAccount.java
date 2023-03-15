package org.example.pattern.singleton.example.Account;

public class CreateAccount {
    // Write private, static CreateAccount here
    static CreateAccount createAccount = new CreateAccount();
    private static String nextAccountNumber = "0";
    private static String limitedNextAccountNumber = "0";
    private static String savingNextAccountNumber = "0";

    //
    // Write private constructor here
    //
    private CreateAccount(){}
    //
    // Write public getCreateAccount method here
    //

    public static CreateAccount getCreateAccount() {
        if(createAccount == null){
            createAccount = new CreateAccount();
        }
        return createAccount;
    }

    public String createAccountNumber(IAccount iAccount) {
        if(iAccount.getClass().equals(LimitedBankAccount.class)){
            int accountNumber = Integer.parseInt(nextAccountNumber);
            nextAccountNumber = Integer.toString(++accountNumber);
            return "0001-" + nextAccountNumber;
        }else if(iAccount.getClass().equals(SavingBankAccount.class)){
            int accountNumber = Integer.parseInt(savingNextAccountNumber);
            savingNextAccountNumber = Integer.toString(++accountNumber);
            return "0002-" + savingNextAccountNumber;
        }else{
            int accountNumber = Integer.parseInt(limitedNextAccountNumber);
            limitedNextAccountNumber = Integer.toString(++accountNumber);
            return "0003-" + limitedNextAccountNumber;
        }
    }
}
