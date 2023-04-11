package com.nhnacademy.edu.springframework.sender;

import com.nhnacademy.edu.springframework.messagesender.User;
import com.nhnacademy.edu.springframework.sender.sender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class MessageSendService {
    @Value("${name}")
    private String name;
    private MessageSender messageSender;

    @Autowired
    public MessageSendService(@Qualifier("emailMessageSender") MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void setEmailMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }
    public void setSmsMessageSender(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void doSendMessage(){
        User user = new User("xxx.xxx.com", "010-1234-1234");
        messageSender.sendMessage(user,"hello world " + name);
    }
}
