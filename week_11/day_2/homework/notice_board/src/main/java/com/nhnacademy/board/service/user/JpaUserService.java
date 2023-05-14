package com.nhnacademy.board.service.user;

import com.nhnacademy.board.domain.LoginRequest;
import com.nhnacademy.board.entity.User;
import com.nhnacademy.board.exception.ExistingUserIdException;
import com.nhnacademy.board.exception.NotFoundUserException;
import com.nhnacademy.board.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserService {

    JpaUserRepository userRepository;

    @Autowired
    public JpaUserService(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String id) {
        if (userRepository.existsById(id)) {
            return userRepository.getUserById(id);
        }
        throw new NotFoundUserException(id);
    }

    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundUserException(id);
        }
        userRepository.deleteById(id);
    }

    public void addUser(User user) {
        if (userRepository.existsById(user.getId())) {
            throw new ExistingUserIdException(user.getId());
        }
        userRepository.save(user);
    }

    public void modifyUser(User user) {
        if (!userRepository.existsById(user.getId())) {
            throw new NotFoundUserException(user.getId());
        }
        userRepository.save(user);
    }

    public boolean existUser(LoginRequest request) {
        if (!userRepository.existsById(request.getUserId())) {
            return false;
        }
        User user = userRepository.getUserById(request.getUserId());
        return user.getId().equals(request.getUserId()) && user.getPassword().equals(request.getUserPassword());
    }

    public Page<User> getPagedUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
