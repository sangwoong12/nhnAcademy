package org.example.Lab12_1.Starter;

public class CreateAccount {
    private static String nextAccountNumber = "0";

    public static String createAccountNumber(){
        int accountNumber = Integer.parseInt(nextAccountNumber);
        nextAccountNumber = Integer.toString(++accountNumber);
        return nextAccountNumber;}
}
