package com.nhnacademy.board.controller;

import com.nhnacademy.board.domain.EditPostRequest;
import com.nhnacademy.board.domain.PostRequest;
import com.nhnacademy.board.item.Page;
import com.nhnacademy.board.item.PageImpl;
import com.nhnacademy.board.item.post.Post;
import com.nhnacademy.board.item.post.PostImpl;
import com.nhnacademy.board.item.user.User;
import com.nhnacademy.board.item.user.UserImpl;
import com.nhnacademy.board.service.post.PostService;
import com.nhnacademy.board.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }


    @Test
    void home() throws Exception {
        // when
        mockMvc.perform(get("/user"))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("user/home"));
    }

    @Test
    void getPostListByPageNum() throws Exception {
        // given
        int pageNum = 2;
        int size = 10;
        Page<Post> pagedPosts = new PageImpl<>(pageNum, size, 1, 1, new ArrayList<>());
        given(postService.getPagedPosts(pageNum, size)).willReturn(pagedPosts);

        // when
        mockMvc.perform(get("/user/post-list").param("pageNum", String.valueOf(pageNum)))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("user/post-list"))
                .andExpect(model().attribute("pagedPosts", pagedPosts));
    }

    @Test
    void getPost() throws Exception {
        long postId = 1;
        Post post = new PostImpl(postId, "content", "test1", "title", LocalDateTime.now(), 0);

        given(postService.getPost(postId)).willReturn(post);

        // when
        mockMvc.perform(get("/user/view-post")
                        .queryParam("postId", String.valueOf(postId)))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("user/post-view"))
                .andExpect(model().attribute("post", post));
    }

    @Test
    void deletePost() throws Exception {
        // given
        long postId = 1;


        // when
        mockMvc.perform(get("/user/delete-post")
                        .queryParam("postId", String.valueOf(postId)))
                // then
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/post-list"));

        verify(postService).deletePost(postId);
    }

    @Test
    void getAddPostForm() throws Exception {
        mockMvc.perform(get("/user/add-post"))
                // then
                .andExpect(status().isOk())
                .andExpect(view().name("user/post-add-form"));
    }

    @Test
    void addPostTest() throws Exception {
        String userId = "test-user";
        PostRequest postRequest = new PostRequest();
        when(postService.addPost(any(Post.class))).thenReturn(1L);

        mockMvc.perform(post("/user/add-post")
                        .sessionAttr("id", userId)
                        .flashAttr("postRequest", postRequest))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/post-list"));

        verify(postService, times(1)).addPost(any(Post.class));
    }

    @Test
    void getEditPostFormTest() throws Exception {
        String userId = "test1";
        long postId = 2;
        Post post = new PostImpl(postId, "content", "title", "test1", LocalDateTime.now(), 0);

        when(postService.getPost(postId)).thenReturn(post);

        mockMvc.perform(get("/user/edit-post")
                        .sessionAttr("id", userId)
                        .param("postId", String.valueOf(postId)))
                .andExpect(status().isOk())
                .andExpect(view().name("user/post-edit-form"));

        verify(postService, times(1)).getPost(postId);
    }

    @Test
    void editPostTest() throws Exception {
        String userId = "test1";
        long postId = 2;
        Post post = new PostImpl(postId, "content", "title", "test1", LocalDateTime.now(), 0);

        EditPostRequest editPostRequest = new EditPostRequest();
        ReflectionTestUtils.setField(editPostRequest,"id",postId);
        ReflectionTestUtils.setField(editPostRequest,"content","content modify");
        ReflectionTestUtils.setField(editPostRequest,"title","title modify");

        when(postService.getPost(postId)).thenReturn(post);

        mockMvc.perform(post("/user/edit-post")
                        .sessionAttr("id", userId)
                        .flashAttr("editPostRequest", editPostRequest))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/view-post?postId=" + postId));

        verify(postService, times(1)).modifyPost(postId, "title modify", "content modify");
    }
}