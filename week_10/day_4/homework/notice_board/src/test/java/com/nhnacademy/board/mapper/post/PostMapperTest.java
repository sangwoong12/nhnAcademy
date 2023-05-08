package com.nhnacademy.board.mapper.post;

import com.nhnacademy.board.config.RootConfig;
import com.nhnacademy.board.item.post.Post;
import com.nhnacademy.board.item.post.PostImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = RootConfig.class
)
@Transactional
class PostMapperTest {

    @Autowired
    PostMapper postMapper;

    private final static int TOTAL_COUNT = 12;

    @Test
    @DisplayName("새로 추가하고 id 를 찾을시 일치하는지 확인")
    void register() {
        Post post = new PostImpl("test", "test", "test1");
        long register = postMapper.register(post);

        assertThat(register).isEqualTo(1);
    }

    @Test
    @DisplayName("postId 1을 찾은 이후 conent 수정이후 가져올시 같은지 테스트")
    void modify() {
        Post post = postMapper.getPost(1);
        post.setContent("modify");
        postMapper.modify(post);

        Post modifyPost = postMapper.getPost(post.getId());
        assertThat(modifyPost.getContent()).isEqualTo("modify");
    }

    @Test
    @DisplayName("삭제이후 조회시 null 인지 테스트")
    void remove() {
        postMapper.remove(1);

        Post post = postMapper.getPost(1);
        assertThat(post).isNull();
    }

    @Test
    void getPost() {
        Post post = postMapper.getPost(1);
        assertThat(post.getId()).isEqualTo(1);
        assertThat(post.getTitle()).isEqualTo("공지사항");
        assertThat(post.getContent()).isEqualTo("공지사항");
        assertThat(post.getWriteTime()).isEqualTo("2023-05-06T03:24:35");
    }

    @Test
    @DisplayName("찾은 포스트의 첫번째 포스트의 데이터가 일치하는지 테스트")
    void getPosts() {
        List<Post> posts = postMapper.getPosts();
        assertThat(posts.get(0).getId()).isEqualTo(1);
        assertThat(posts.get(0).getContent()).isEqualTo("공지사항");
        assertThat(posts.get(0).getTitle()).isEqualTo("공지사항");
    }

    @Test
    @DisplayName("postId 1 이 존재하는지 테스트")
    void existById() {
        boolean b = postMapper.existById(1);
        assertThat(b).isTrue();
    }

    @Test
    void getTotalCount() {
        int totalCount = postMapper.getTotalCount();
        assertThat(totalCount).isEqualTo(TOTAL_COUNT);
    }

    @Test
    void getPagedPosts() {
        List<Post> pagedPosts = postMapper.getPagedPosts(10, 1);
        assertThat(pagedPosts).hasSize(10);
    }
}