package com.nhnacademy.notice_board.repository.user;

import com.nhnacademy.notice_board.item.Page;
import com.nhnacademy.notice_board.item.PageImpl;
import com.nhnacademy.notice_board.item.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryUserRepository implements UserRepository {
    Map<String, User> userMap = new ConcurrentHashMap<>();

    @Override
    public void add(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public void modify(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public User remove(String id) {
        return userMap.remove(id);
    }

    @Override
    public User getUser(String id) {
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

    @Override
    public Page<User> getPagedPosts(int page, int size) {
        int totalPageCount = (int) Math.ceil((double) getTotalCount() / size); // 총 페이지 수 계산
        int start = (page - 1) * size; //0부터 시작해야해서 -1
        int end = Math.min(start + size, getTotalCount());//마지막 페이지의 경우 최대 갯수를 넘길수 없다.
        List<User> list = getUsers().subList(start, end); // 해당 페이지의 게시물 리스트
        return new PageImpl<>(page, size, totalPageCount, getTotalCount(), list);
    }

    @Override
    public int getTotalCount() {
        return userMap.size();
    }
}
