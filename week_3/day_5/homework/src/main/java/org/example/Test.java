package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input source file name :");
        String fileName = scanner.nextLine();
        System.out.println(fileName);
        List<String> testStrings = new ArrayList<>();
        for (String testString : testStrings) {
            if(testString.equals("Test")){
                System.out.println(testString);
            } else if (testString.equals("elseIf")){
                System.out.println("elseIf");
            } else {
                System.out.println("else");
            }
        }
        try {
            System.out.println("try 화면 출력");
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            throw new RuntimeException();
        }

    }
}
