package org.example;

import java.util.Arrays;
import java.util.Base64;

public class Test64 {
    public static void main(String[] args) {
        String message = "hello";
        String codeTable = "ABCDEFG /";
        byte [] value = Base64.getEncoder().encode(message.getBytes());
        System.out.println(Arrays.toString(message.getBytes()));

        byte[] intValue = {0};
        System.out.println(Arrays.toString(intValue));
        System.out.println(Arrays.toString(Base64.getEncoder().encode(intValue)));

        byte [] source = message.getBytes();
        byte [] result = new byte[(message.getBytes().length+2/3*4)];

        System.out.println(Arrays.toString(source));
        System.out.println(Arrays.toString(result));
    }
}
