package com.nhnacademy.edu.springframework.message_sender;

import com.nhnacademy.edu.springframework.item.User;
import org.springframework.stereotype.Component;

@Component
public interface MessageSender {

    boolean sendMessage(User user, String message);
}
