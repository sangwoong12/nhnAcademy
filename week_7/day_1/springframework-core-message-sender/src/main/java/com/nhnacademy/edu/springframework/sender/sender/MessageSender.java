package com.nhnacademy.edu.springframework.sender.sender;

import com.nhnacademy.edu.springframework.messagesender.User;

public interface MessageSender {
    void sendMessage(User user, String message);

    void init();

    void close();
}
