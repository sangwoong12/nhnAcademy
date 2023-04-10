package com.nhnacademy.edu.springframework.messagesender;

public class Main {
    public static void main(String[] args) {
        User user = new User("xxx.xxx.com", "010-1234-1234");
        sendSmsMessage(user,"hello world");

    }
    private static void sendSmsMessage(User user, String message){
        System.out.println("SMS Message Sent to "+user.getPhoneNumber()+" : "+message);
    }
    private static void sendEmailMessage(User user, String message){
        System.out.println("SMS Message Sent to "+user.getEmail()+" : "+message);
    }
}
