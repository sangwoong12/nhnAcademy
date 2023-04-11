package com.nhnacademy.edu.springframework.dependency;

import com.nhnacademy.edu.springframework.sender.MessageSendService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        try(ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml")) {
//            MessageSendService messageSendService = context.getBean("messageSendService", MessageSendService.class);
//            messageSendService.doSendMessage();
//        }
    }
}
