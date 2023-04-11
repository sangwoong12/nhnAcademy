package com.nhnacademy.edu.springframework.sender.sender;

import com.nhnacademy.edu.springframework.messagesender.User;
import org.springframework.stereotype.Component;

@Component
public class SmsMessageSender implements MessageSender {
    public SmsMessageSender() {
        System.out.println("SmsMessageSender init");
    }

    public void init() {
        System.out.println("        > SmsMessageSender init called!!");
    }

    @Override
    public void sendMessage(User user, String message) {
        System.out.println("SMS Message Sent to " + user.getPhoneNumber() + " : " + message);
    }
    @Override
    public void close() {
//        System.out.println("    ㄴ SmsMessageSender destroy called!!");
    }
}
