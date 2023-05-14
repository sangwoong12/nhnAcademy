package com.nhnacademy.board.service.user;

import com.nhnacademy.board.domain.LoginRequest;
import com.nhnacademy.board.domain.UserDto;
import com.nhnacademy.board.domain.UserEditDto;
import com.nhnacademy.board.entity.User;
import com.nhnacademy.board.exception.ExistingUserIdException;
import com.nhnacademy.board.exception.NotFoundUserException;
import com.nhnacademy.board.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class JpaUserService {

    UserRepository userRepository;

    @Autowired
    public JpaUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String id) {
        User user = userRepository.getUserById(id);
        if (Objects.isNull(user)) {
            throw new NotFoundUserException(id);
        }
        return user;
    }

    @Transactional
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
    @Transactional
    public void modifyUser(UserEditDto userEditDto) {
        User user = userRepository.getUserById(userEditDto.getId());
        user.setPassword(userEditDto.getPassword());
        user.setName(userEditDto.getName());
        user.setProfileFileName(userEditDto.getFileName());
        userRepository.save(user);
    }

    public boolean existUser(LoginRequest request) {
        return userRepository.existsByIdAndPassword(request.getUserId(), request.getUserPassword());
    }

    public Page<UserDto> getPagedUsers(Pageable pageable) {
        return userRepository.getAllBy(pageable);
    }
}
