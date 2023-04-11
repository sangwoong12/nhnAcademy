package com.nhnacademy.edu.springframework.sender;

import com.nhnacademy.edu.springframework.messagesender.User;
import com.nhnacademy.edu.springframework.sender.sender.MessageSender;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
            String message = "hello world";
            User user = new User("xxx.xxx.com", "010-1234,1234");
//            MessageSender emailMessageSender1 = context.getBean("emailMessageSender",MessageSender.class);
//            emailMessageSender1.sendMessage(user,message);
            System.out.println("---------------------------");
            MessageSender emailMessageSender2 = context.getBean("emailMessageSender", MessageSender.class);
            emailMessageSender2.sendMessage(user, message);
            System.out.println("---------------------------");
//            MessageSender smsMessageSender1 = context.getBean("smsMessageSender",MessageSender.class);
//            smsMessageSender1.sendMessage(user,message);
//            System.out.println("---------------------------");
//            MessageSender smsMessageSender2 = context.getBean("smsMessageSender",MessageSender.class);
//            smsMessageSender2.sendMessage(user,message);

        }
    }
}
