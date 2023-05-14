package com.nhnacademy.board.repository.user;

import com.nhnacademy.board.domain.UserDto;
import com.nhnacademy.board.domain.UserEditDto;
import com.nhnacademy.board.domain.UserIdOnly;
import com.nhnacademy.board.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(String id);

    User getUserById(String id);

    void deleteById(String id);

    Page<UserDto> getAllBy(Pageable pageable);

    boolean existsByIdAndPassword(String id, String password);

}
