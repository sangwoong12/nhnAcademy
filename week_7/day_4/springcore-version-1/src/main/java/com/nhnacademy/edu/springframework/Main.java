package com.nhnacademy.edu.springframework;

import com.nhnacademy.edu.springframework.item.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
            MessageSendService messageSendService = context.getBean("messageSendService", MessageSendService.class);
            User user = new User("user");
            String message = "message";
            messageSendService.doSendMessage(user, message);
        }
    }
}