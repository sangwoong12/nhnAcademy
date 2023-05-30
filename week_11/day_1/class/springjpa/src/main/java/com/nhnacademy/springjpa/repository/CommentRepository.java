package com.nhnacademy.springjpa.repository;

import com.nhnacademy.springjpa.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
