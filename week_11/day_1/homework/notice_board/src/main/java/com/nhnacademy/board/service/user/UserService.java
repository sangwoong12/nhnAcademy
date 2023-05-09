package com.nhnacademy.board.service.user;

import com.nhnacademy.board.domain.LoginRequest;
import com.nhnacademy.board.item.Page;
import com.nhnacademy.board.item.user.User;

public interface UserService {
    User getUser(String id);

    User deleteUser(String id);

    void addUser(User user);

    void modifyUser(User user);

    boolean existUser(LoginRequest request);

    Page<User> getPagedUsers(int page, int size);

}
