package com.nhnacademy.edu.springframework.config;

import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.edu.springframework.MessageSendService;
import com.nhnacademy.edu.springframework.message_sender.DoorayMessageSender;
import com.nhnacademy.edu.springframework.message_sender.MessageSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;


@Configuration
@ComponentScan(basePackages = {"com.nhnacademy.edu.springframework"})
@PropertySource("classpath:item.properties")
@EnableAspectJAutoProxy
public class JavaConfig {
    @Value("${hookUrl}")
    String url;

    @Bean
    public MessageSender doorayMessageSender() {
        return new DoorayMessageSender(doorayHookSender());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public DoorayHookSender doorayHookSender() {
        return new DoorayHookSender(restTemplate(), url);
    }

    @Bean
    public MessageSendService messageSendService() {
        return new MessageSendService(doorayMessageSender());
    }

}
