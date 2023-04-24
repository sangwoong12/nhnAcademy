package com.nhnacademy.notice_board.service.user;

import com.nhnacademy.notice_board.domain.LoginRequest;
import com.nhnacademy.notice_board.item.Page;
import com.nhnacademy.notice_board.item.user.User;

import java.util.List;

public interface UserService {
    User getUser(String id);

    void deleteUser(String id);

    void addUser(User user);

    void modifyUser(User user);

    boolean existUser(LoginRequest request);

    Page<User> getPagedUsers(int page, int size);

}
