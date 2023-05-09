package com.nhnacademy.board.repository;

import com.nhnacademy.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User,Long> {
    boolean existsById(String id);

    User getUserById(String id);

    void deleteById(String id);

}
