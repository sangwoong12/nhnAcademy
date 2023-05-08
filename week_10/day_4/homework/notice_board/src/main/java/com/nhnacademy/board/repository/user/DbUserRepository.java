package com.nhnacademy.board.repository.user;

import com.nhnacademy.board.item.Page;
import com.nhnacademy.board.item.PageImpl;
import com.nhnacademy.board.item.user.User;
import com.nhnacademy.board.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class DbUserRepository implements UserRepository {
    private final UserMapper userMapper;

    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    @Override
    public void modify(User user) {
        userMapper.modify(user);
    }

    @Override
    public User remove(String id) {
        User user = userMapper.getUser(id);
        userMapper.remove(id);
        return user;
    }

    @Override
    public User getUser(String id) {
        return userMapper.getUser(id);
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public boolean existById(String id) {
        return userMapper.existById(id);
    }

    @Override
    public Page<User> getPagedPosts(int page, int size) {
        int totalPageCount = (int) Math.ceil((double) getTotalCount() / size); // 총 페이지 수 계산
        int start = (page - 1) * size; //0부터 시작해야해서 -1
        int end = Math.min(start + size, getTotalCount());//마지막 페이지의 경우 최대 갯수를 넘길수 없다.
        List<User> list = getUsers().subList(start, end); // 해당 페이지의 게시물 리스트
//        List<User> list = userMapper.getPagedUsers(size,(size-1)*page);
        return new PageImpl<>(page, size, totalPageCount, getTotalCount(), list);
    }

    @Override
    public int getTotalCount() {
        return userMapper.getTotalCount();
    }
}
