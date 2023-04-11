package com.nhnacademy.edu.springframework.sender.sender;

import com.nhnacademy.edu.springframework.messagesender.User;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


@Component
public class EmailMessageSender implements MessageSender, InitializingBean, DisposableBean {
    public EmailMessageSender() {
        System.out.println("EmailMessageSender init");
    }

    public void init() {
        System.out.println("            ㄴ EnglishGreeter init called!!");
    }

    //protoType의 경우 종료시점이 명확하지 않기 때문에 destroy가 호출되지 않을 수 있다.
    //그럼 종료는 안되느냐 그렇지 않다 GC에서 처리할 것이다. spring은 자바 표준이 아니기 때문에 GC에서 메서드를 호출할 이유가 없다.
    public void close() {
        System.out.println("    ㄴ EmailMessageSender destroy called!!");
    }

    @Override
    public void destroy() throws Exception {
//        System.out.println("> EmailMessageSender DisposableBean called!!");
    }

    @Override
    public void sendMessage(User user, String message) {
        System.out.println("Email Message Sent to " + user.getEmail() + " : " + message);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
//        System.out.println("        > EmailMessageSender afterPropertiesSet called!!");
    }
}
