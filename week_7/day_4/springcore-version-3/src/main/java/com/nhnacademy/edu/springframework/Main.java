package com.nhnacademy.edu.springframework;

import com.nhnacademy.edu.springframework.item.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework")) {
            MessageSendService messageSendService = context.getBean("messageSendService", MessageSendService.class);
            User user = new User("user");
            String message = "message";
            messageSendService.doSendMessage(user, message);
        }
    }
}