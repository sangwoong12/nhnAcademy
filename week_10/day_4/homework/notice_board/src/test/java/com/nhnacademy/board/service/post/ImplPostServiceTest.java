package com.nhnacademy.board.service.post;

import com.nhnacademy.board.exception.ExistingPostIdException;
import com.nhnacademy.board.exception.NotFoundPostException;
import com.nhnacademy.board.item.Page;
import com.nhnacademy.board.item.PageImpl;
import com.nhnacademy.board.item.post.Post;
import com.nhnacademy.board.item.post.PostImpl;
import com.nhnacademy.board.repository.post.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class ImplPostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService = new ImplPostService(postRepository);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("포스트가 존재할때 조회 테스트")
    void getPost() {
        // given
        long postId = 1L;
        Post post = new PostImpl(postId, "title", "content", "test", LocalDateTime.now(), 0);

        given(postRepository.existById(postId)).willReturn(true);
        given(postRepository.getPost(postId)).willReturn(post);

        // when
        Post actualPost = postService.getPost(postId);

        // then
        assertThat(actualPost).isEqualTo(post);
    }

    @Test
    @DisplayName("포스트가 존재하지 않을때 조회 예외 발생 여부 테스트")
    void getPost2() {
        // given
        long postId = 1L;
        given(postRepository.existById(postId)).willReturn(false);

        // when, then
        assertThrows(NotFoundPostException.class, () -> postService.getPost(postId));
    }

    @Test
    @DisplayName("포스트가 존재할때 삭제 테스트")
    void deletePost() {
        // given
        long postId = 1;
        Post post = new PostImpl(postId, "title", "content", "test", LocalDateTime.now(), 0);

        given(postRepository.existById(1)).willReturn(true);
        given(postRepository.remove(1)).willReturn(post);
        // when
        Post removePost = postService.deletePost(postId);
        // then
        assertThat(removePost.getId()).isEqualTo(1);
        assertThat(removePost.getTitle()).isEqualTo("title");
        assertThat(removePost.getContent()).isEqualTo("content");
        assertThat(removePost.getWriterUserId()).isEqualTo("test");
    }

    @Test
    @DisplayName("포스트가 존재하지 않을때 삭제 예외 발생 여부 테스트")
    void deletePost2() {
        // given
        long postId = 1;

        given(postRepository.existById(1)).willReturn(false);

        // then, when
        assertThrows(NotFoundPostException.class, () -> postService.deletePost(postId));
    }

    @Test
    @DisplayName("포스트가 존재하지 않을때 추가 테스트")
    void addPost() {
        // given
        long postId = 1;
        Post post = new PostImpl(postId, "title", "content", "test", LocalDateTime.now(), 0);

        given(postRepository.existById(1)).willReturn(false);
        given(postRepository.register(post)).willReturn(postId);

        // when
        long l = postService.addPost(post);

        // then
        assertThat(l).isEqualTo(1);
    }

    @Test
    @DisplayName("포스트가 이미 존재할때 추가 예외 발생 여부 테스트")
    void addPost2() {
        // given
        long postId = 1;
        Post post = new PostImpl(postId, "title", "content", "test", LocalDateTime.now(), 0);

        given(postRepository.existById(1)).willReturn(true);

        // then, when
        assertThrows(ExistingPostIdException.class, () -> postService.addPost(post));
    }

    @Test
    @DisplayName("수정 동작 테스트")
    void modifyPost() {
        long postId = 1L;
        String title = "modified title";
        String content = "modified content";
        Post post = new PostImpl(postId, "title", "content", "test", LocalDateTime.now(), 0);

        given(postRepository.getPost(postId)).willReturn(post);

        // when
        postService.modifyPost(postId, title, content);

        // then
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("increaseCount 정상 호출 테스트")
    void increaseCount() {
        // given
        long postId = 1L;
        Post post = new PostImpl(postId, "title", "content", "test", LocalDateTime.now(), 0);

        // when
        postService.increaseCount(post);

        // then
        verify(postRepository).increaseCount(post);
    }

    @Test
    @DisplayName("increaseCount 정상 호출 테스트")
    void getPagedPosts() {
        Page<Post> postPage = new PageImpl<>(1, 10, 1, 1, new ArrayList<>());

        given(postRepository.getPagedPosts(1, 10)).willReturn(postPage);

        Page<Post> pagedPosts = postService.getPagedPosts(1, 10);

        assertThat(pagedPosts.getPageNumber()).isEqualTo(1);
        assertThat(pagedPosts.getPageSize()).isEqualTo(10);
        assertThat(pagedPosts.getTotalPageCount()).isEqualTo(1);
        assertThat(pagedPosts.getTotalCount()).isEqualTo(1);
    }
}