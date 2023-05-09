package com.nhnacademy.board.entity;

import com.nhnacademy.board.config.RootConfig;
import com.nhnacademy.board.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@Transactional
@ContextHierarchy({
        @ContextConfiguration(classes = RootConfig.class),
        @ContextConfiguration(classes = WebConfig.class)
})
class PostTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void testPostEntity() {
        Post post = new Post();
        post.setTitle("title");
        post.setContent("content");
        post.setViewCount(10);
        post.setWriterUserId(1L);
        post.setWriteTime(LocalDateTime.now());
        entityManager.persist(post);
        entityManager.flush();
        Post testPost = entityManager.find(Post.class, post.getId());

        assertThat(testPost.getContent()).isEqualTo(post.getContent());
        assertThat(testPost.getTitle()).isEqualTo(post.getTitle());
        assertThat(testPost.getWriteTime()).isEqualTo(post.getWriteTime());
        assertThat(testPost.getWriterUserId()).isEqualTo(post.getWriterUserId());
        assertThat(testPost.getId()).isEqualTo(post.getId());
    }
}