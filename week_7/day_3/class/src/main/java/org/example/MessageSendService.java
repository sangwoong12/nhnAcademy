package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSendService {
    @Autowired
    private MessageSender messageSender;

    public void doSendMessage() {
        User user = new User("email", "010-0000-0000");
        messageSender.sendMessage(user, "hello");
    }
}
