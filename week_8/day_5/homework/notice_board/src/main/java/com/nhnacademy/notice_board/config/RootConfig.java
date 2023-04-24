package com.nhnacademy.notice_board.config;

import com.nhnacademy.notice_board.Base;
import com.nhnacademy.notice_board.item.post.PostImpl;
import com.nhnacademy.notice_board.item.user.UserImpl;
import com.nhnacademy.notice_board.repository.post.JsonPostRepository;
import com.nhnacademy.notice_board.repository.post.MemoryPostRepository;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import com.nhnacademy.notice_board.repository.user.JsonUserRepository;
import com.nhnacademy.notice_board.repository.user.MemoryUserRepository;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = {@ComponentScan.Filter(Controller.class)})
public class RootConfig {
    @Bean
    public UserRepository userRepository() {
        UserRepository userRepository = new MemoryUserRepository();
        UserImpl admin = new UserImpl("admin", "관리자", "12345", null);
        userRepository.add(admin);
        for (int i = 0; i < 100; i++) {
            userRepository.add(new UserImpl("id" + i, "User-" + i, "12345", null));
        }
        return userRepository;
    }

    @Bean
    public PostRepository postRepository() {
        PostRepository postRepository = new MemoryPostRepository();
        for (int i = 0; i < 20; i++) {
            PostImpl admin = new PostImpl("title" + i, "content" + i, "id" + i);
            postRepository.register(admin);
        }
        return postRepository;
    }
}
