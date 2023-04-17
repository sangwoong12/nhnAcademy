package com.nhnacademy.edu.springframework.message_sender;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import com.nhnacademy.edu.springframework.annotation.ElapsedTimeLog;
import com.nhnacademy.edu.springframework.item.User;

public class DoorayMessageSender implements MessageSender {
    DoorayHookSender doorayHookSender;


    public DoorayMessageSender(DoorayHookSender doorayHookSender) {
        this.doorayHookSender = doorayHookSender;
    }

    @Override
    @ElapsedTimeLog
    public boolean sendMessage(User user, String message) {
        if (user.getName().isEmpty() || message.isEmpty()) {
            return false;
        }
        doorayHookSender.send(DoorayHook.builder()
                .botName(user.getName())
                .text(message)
                .build());
        return true;
    }
}
