package com.nhnacademy.board.mapper.user;

import com.nhnacademy.board.item.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void add(User user);

    void modify(User user);

    void remove(String id);

    User getUser(String id);

    List<User> getUsers();

    boolean existById(String id);

    int getTotalCount();

    List<User> getPagedUsers(int size, int start);
}
