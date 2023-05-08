package com.nhnacademy.board.repository.user;

import com.nhnacademy.board.item.Page;
import com.nhnacademy.board.item.user.User;

import java.util.List;

public interface UserRepository {
    void add(User user);

    void modify(User user);

    User remove(String id);

    User getUser(String id);

    List<User> getUsers();

    boolean existById(String id);

    Page<User> getPagedPosts(int page, int size);

    int getTotalCount();

}
