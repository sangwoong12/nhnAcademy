package com.nhnacademy.board.service.user;

import com.nhnacademy.board.domain.LoginRequest;
import com.nhnacademy.board.exception.ExistingUserIdException;
import com.nhnacademy.board.exception.NotFoundUserException;
import com.nhnacademy.board.item.Page;
import com.nhnacademy.board.item.user.User;
import com.nhnacademy.board.repository.user.UserRepository;
import org.springframework.stereotype.Service;

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
    public User deleteUser(String id) {
        if (!userRepository.existById(id)) {
            throw new NotFoundUserException(id);
        }
        return userRepository.remove(id);
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
        if (!userRepository.existById(user.getId())) {
            throw new NotFoundUserException(user.getId());
        }
        userRepository.modify(user);
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
