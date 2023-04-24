import com.nhnacademy.notice_board.exception.ExistingPostIdException;
import com.nhnacademy.notice_board.exception.NotFoundPostException;
import com.nhnacademy.notice_board.item.Page;
import com.nhnacademy.notice_board.item.post.Post;
import com.nhnacademy.notice_board.item.post.PostImpl;
import com.nhnacademy.notice_board.repository.post.JsonPostRepository;
import com.nhnacademy.notice_board.repository.post.PostRepository;
import com.nhnacademy.notice_board.service.post.ImplPostService;
import com.nhnacademy.notice_board.service.post.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class PostServiceTest {
    //jsonPostRepository 를 함께 테스트.
    private static PostRepository postRepository = new JsonPostRepository();
    private static PostService postService;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 5; i++) {
            PostImpl admin = new PostImpl("title" + i, "content" + i, "id" + i);
            postRepository.register(admin);
        }
        postService = new ImplPostService(postRepository);
    }

    @Test
    void getPostTest() {
        Post post = postService.getPost(1);
        long id = 1;
        String title = "title0";
        String content = "content0";
        int viewCount = 0;
        Assertions.assertEquals(post.getId(), id);
        Assertions.assertEquals(post.getContent(), content);
        Assertions.assertEquals(post.getViewCount(), viewCount);
        Assertions.assertEquals(post.getTitle(), title);
    }

    @Test
    @DisplayName("존재하지 않는 post 찾을시 오류발생 여부")
    void getPostTest2() {
        Assertions.assertThrows(NotFoundPostException.class, () -> postService.getPost(10000));
    }

    @Test
    @DisplayName("존재하지 않는 post 제거시 오류발생 여부")
    void deletePostTest() {
        Assertions.assertThrows(NotFoundPostException.class, () -> postService.deletePost(10000));
    }
    @Test
    void deletePostTest2() {
        Assertions.assertDoesNotThrow(() -> postService.deletePost(3));
    }
    @Test
    @DisplayName("이미 존재하는 postId 추가시 오류발생 여부")
    void addPostTest() {
        PostImpl post = new PostImpl("test", "test", "id0");
        ReflectionTestUtils.setField(post, "id", 2);
        Assertions.assertThrows(ExistingPostIdException.class, () -> postService.addPost(post));
    }

    @Test
    void modifyPost() {
        long postId = 1;
        String title = "test";
        String content = "test";

        postService.modifyPost(postId, title, content);

        Post post = postService.getPost(postId);
        Assertions.assertEquals(post.getId(), postId);
        Assertions.assertEquals(post.getContent(), content);
        Assertions.assertEquals(post.getTitle(), title);
    }

    @Test
    void increaseCountTest() {
        //json은 읽고 쓰기 때문에 쓰고 읽어와야 값 변경을 확인할 수 있다.
        Post post2 = postService.getPost(6);
        postService.increaseCount(post2);
        Post post1 = postService.getPost(6);
        Assertions.assertEquals(post1.getViewCount(), 1);
    }

    @Test
    void getPagedPostTest() {
        Page<Post> pagedPosts = postService.getPagedPosts(1, 10);
        Assertions.assertEquals(pagedPosts.getPageSize(), 10);
    }
}
