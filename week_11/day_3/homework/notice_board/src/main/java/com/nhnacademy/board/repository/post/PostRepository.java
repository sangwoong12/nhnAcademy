package com.nhnacademy.board.repository.post;

import com.nhnacademy.board.domain.PostDto;
import com.nhnacademy.board.domain.PostRequest;
import com.nhnacademy.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Entity;

public interface PostRepository extends JpaRepository<Post,Long> {

    @EntityGraph("PostWithUser")
    Post getPostById(Long id);
    Page<PostDto> getAllBy(Pageable pageable);

    @Modifying
    @Query(value = "UPDATE Post SET view_count = view_count +1 WHERE id = ?1",nativeQuery = true)
    void increaseViewCount(Long id);

}
