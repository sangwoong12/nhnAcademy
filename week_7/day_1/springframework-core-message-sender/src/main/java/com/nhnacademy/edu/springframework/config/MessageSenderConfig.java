package com.nhnacademy.edu.springframework.config;

import com.nhnacademy.edu.springframework.sender.sender.EmailMessageSender;
import com.nhnacademy.edu.springframework.sender.sender.MessageSender;
import com.nhnacademy.edu.springframework.sender.sender.SmsMessageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.nhnacademy.edu.springframework")
@PropertySource("classpath:name.properties")
public class MessageSenderConfig {
    @Bean
//            (initMethod = "init", destroyMethod = "close")
    public MessageSender smsMessageSender() {
        return new SmsMessageSender();
    }

    @Bean
//            (initMethod = "init", destroyMethod = "close")
    public MessageSender emailMessageSender() {
        return new EmailMessageSender();
    }
}
