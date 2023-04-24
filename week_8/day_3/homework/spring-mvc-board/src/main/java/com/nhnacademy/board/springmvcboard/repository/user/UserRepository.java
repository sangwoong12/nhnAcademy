package com.nhnacademy.board.springmvcboard.repository.user;

import com.nhnacademy.notice_board.item.user.User;

import java.util.List;

public interface UserRepository {
    void add(User user);
    void modify(User user);
    User remove(String id);
    User getUser(String id);
    List<User> getUsers();
    boolean existById(String id);
}
