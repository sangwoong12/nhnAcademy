package com.nhnacademy.springjpa.service;

import com.nhnacademy.springjpa.entity.User;
import com.nhnacademy.springjpa.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public User createUser(String id, String password) {
        User user = new User();
        user.setId(id);
        user.setPassword(password);

        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User modifyUser(User user) {
        return userRepository.save(user);
    }

}
