package com.nhnacademy.edu.springframework;

import com.nhnacademy.edu.springframework.item.User;
import com.nhnacademy.edu.springframework.message_sender.MessageSender;

public class MessageSendService {
    private MessageSender messageSender;

    public MessageSendService(MessageSender messageSender) {
        this.messageSender = messageSender;

    }

    public void doSendMessage(User user, String message) {
        messageSender.sendMessage(user, message);
    }
}
