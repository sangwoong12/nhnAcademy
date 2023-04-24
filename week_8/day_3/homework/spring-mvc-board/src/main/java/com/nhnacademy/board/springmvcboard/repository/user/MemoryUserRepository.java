package com.nhnacademy.board.springmvcboard.repository.user;

import com.nhnacademy.notice_board.controller.exception.NotFoundIdException;
import com.nhnacademy.notice_board.item.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryUserRepository implements UserRepository {
    Map<String,User> userMap = new ConcurrentHashMap<>();

    @Override
    public void add(User user) {
        userMap.put(user.getId(),user);
    }

    @Override
    public void modify(User user) {
        userMap.put(user.getId(),user);
    }

    @Override
    public User remove(String id){
        return userMap.remove(id);
    }

    @Override
    public User getUser(String id) {
        if (!userMap.containsKey(id)){
            throw new NotFoundIdException();
        }
        return userMap.get(id);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public boolean existById(String id) {
        return userMap.containsKey(id);
    }
}
