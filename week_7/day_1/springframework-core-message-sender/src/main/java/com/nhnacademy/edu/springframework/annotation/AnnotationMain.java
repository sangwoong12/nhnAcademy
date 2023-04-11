package com.nhnacademy.edu.springframework.annotation;

import com.nhnacademy.edu.springframework.sender.MessageSendService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationMain {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(
                             "com.nhnacademy.edu.springframework.config")) {
//            context.close();
            //try catch 문을 할경우 자동으로 close 호출
            MessageSendService messageSendService = context.getBean("messageSendService", MessageSendService.class);
            messageSendService.doSendMessage();
        }
    }
}
