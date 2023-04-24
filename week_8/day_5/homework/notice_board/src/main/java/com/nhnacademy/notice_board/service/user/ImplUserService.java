package com.nhnacademy.notice_board.service.user;

import com.nhnacademy.notice_board.domain.LoginRequest;
import com.nhnacademy.notice_board.exception.ExistingUserIdException;
import com.nhnacademy.notice_board.exception.NotFoundUserException;
import com.nhnacademy.notice_board.item.Page;
import com.nhnacademy.notice_board.item.user.User;
import com.nhnacademy.notice_board.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplUserService implements UserService {
    UserRepository userRepository;

    public ImplUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String id) {
        if (userRepository.existById(id)) {
            return userRepository.getUser(id);
        }
        throw new NotFoundUserException(id);
    }

    @Override
    public void deleteUser(String id) {
        if (!userRepository.existById(id)) {
            throw new NotFoundUserException(id);
        }
        userRepository.remove(id);
    }

    @Override
    public void addUser(User user) {
        if (userRepository.existById(user.getId())) {
            throw new ExistingUserIdException(user.getId());
        }
        userRepository.add(user);
    }

    @Override
    public void modifyUser(User user) {
        if (userRepository.existById(user.getId())) {
            userRepository.modify(user);
        }
        throw new NotFoundUserException(user.getId());
    }

    @Override
    public boolean existUser(LoginRequest request) {
        if (!userRepository.existById(request.getUserId())) {
            return false;
        }
        User user = userRepository.getUser(request.getUserId());
        return user.getId().equals(request.getUserId()) && user.getPassword().equals(request.getUserPassword());
    }

    @Override
    public Page<User> getPagedUsers(int page, int size) {
        return userRepository.getPagedPosts(page, size);
    }
}
